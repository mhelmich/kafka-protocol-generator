package org.kafka.protocol;

import com.google.common.collect.ImmutableMap;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.kafka.protocol.gen.KafkaProtocolBaseListener;
import org.kafka.protocol.gen.KafkaProtocolParser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class KafkaProtocolListener extends KafkaProtocolBaseListener {
    private String entityName;
    private String versionNumber;

    private final ParseTreeProperty<String> leftSideName = new ParseTreeProperty<>();
    private final Map<String, List<TypeDefinition>> complexTypeToDefinitions = new HashMap<>();
    private final Map<String, List<TypeDefinition>> primitiveTypeToDefinitions = new HashMap<>();

    @Override
    public void exitEntity_name(KafkaProtocolParser.Entity_nameContext ctx) {
        entityName = ctx.getText().replace(" ", "_");
    }

    @Override
    public void exitVersion_number(KafkaProtocolParser.Version_numberContext ctx) {
        versionNumber = ctx.getText();
    }

    @Override
    public void enterLeft_side(KafkaProtocolParser.Left_sideContext ctx) {
        complexTypeToDefinitions.putIfAbsent(ctx.getText(), new LinkedList<>());
        primitiveTypeToDefinitions.putIfAbsent(ctx.getText(), new LinkedList<>());
    }

    @Override
    public void exitLeft_side(KafkaProtocolParser.Left_sideContext ctx) {
        // this will end up being the struct name
        String futureStructName;
        if (ctx.getChildCount() > 1) {
            // this has an entity name and a version number
            // in this case we want to filter out the string "Version:" and just use the version number
            KafkaProtocolParser.Entity_nameContext entityNameCtx = ctx.getChild(KafkaProtocolParser.Entity_nameContext.class, 0);
            KafkaProtocolParser.VersionContext versionCtx = ctx.getChild(KafkaProtocolParser.VersionContext.class, 0);
            KafkaProtocolParser.Version_numberContext verionNumberCtx = versionCtx.getChild(KafkaProtocolParser.Version_numberContext.class, 0);
            futureStructName = entityNameCtx.getText() + verionNumberCtx.getText();
        } else {
            futureStructName = ctx.getText();
        }

        leftSideName.put(ctx.getParent(), futureStructName);
        complexTypeToDefinitions.putIfAbsent(futureStructName, new LinkedList<>());
        primitiveTypeToDefinitions.putIfAbsent(futureStructName, new LinkedList<>());
    }

    @Override
    public void exitComplex_type(KafkaProtocolParser.Complex_typeContext ctx) {
        if (isRightSide(ctx)) {
            String name = getLeftSideName(ctx);
            if (name != null) {
                List<TypeDefinition> types = complexTypeToDefinitions.get(name);
                if (types != null) {
                    String text = ctx.getText();
                    boolean isArray = isArray(ctx);
                    types.add(new TypeDefinition(text, isArray, true));
                }
            }
        }
    }

    @Override
    public void exitPrimitive_type(KafkaProtocolParser.Primitive_typeContext ctx) {
        if (isRightSide(ctx)) {
            String name = getLeftSideName(ctx);
            if (name != null) {
                List<TypeDefinition> names = primitiveTypeToDefinitions.get(name);
                if (names != null) {
                    String text = ctx.getText();
                    boolean isArray = isArray(ctx);
                    names.add(new TypeDefinition(text, isArray, false));
                }
            }
        }
    }

    private boolean isRightSide(ParserRuleContext ctx) {
        return KafkaProtocolParser.Right_sideContext.class.equals(ctx.getParent().getClass()) || KafkaProtocolParser.Right_sideContext.class.equals(ctx.getParent().getParent().getClass());
    }

    private String getLeftSideName(ParserRuleContext srcCtx) {
        return leftSideName.get(getBnfLineCtx(srcCtx));
    }

    private boolean isArray(ParserRuleContext ctx) {
        return KafkaProtocolParser.Complex_arrayContext.class.equals(ctx.getParent().getClass()) || KafkaProtocolParser.Primitive_arrayContext.class.equals(ctx.getParent().getClass());
    }

    private KafkaProtocolParser.Bnf_lineContext getBnfLineCtx(ParserRuleContext srcCtx) {
        ParserRuleContext x = srcCtx;
        while (!KafkaProtocolParser.Bnf_lineContext.class.equals(x.getClass())) {
            x = x.getParent();
        }
        return (KafkaProtocolParser.Bnf_lineContext)x;
    }

    String getFileName() {
        return (versionNumber == null) ? entityName : entityName + "_" + versionNumber;
    }

    String getVersionNumber() {
        return versionNumber;
    }

    Map<String, List<TypeDefinition>> getComplexTypeToDefinitions() {
        ImmutableMap.Builder<String, List<TypeDefinition>> builder = ImmutableMap.builderWithExpectedSize(complexTypeToDefinitions.size());
        complexTypeToDefinitions.entrySet().stream()
                .filter(e -> e.getValue().size() > 0)
                .forEach(e -> builder.put(e.getKey(), e.getValue()));
        return builder.build();
    }

    Map<String, List<TypeDefinition>> getPrimitiveTypeToDefinitions() {
        ImmutableMap.Builder<String, List<TypeDefinition>> builder = ImmutableMap.builderWithExpectedSize(primitiveTypeToDefinitions.size());
        primitiveTypeToDefinitions.entrySet().stream()
                .filter(e -> e.getValue().size() > 0)
                .forEach(e -> builder.put(e.getKey(), e.getValue()));
        return builder.build();
    }

    // interesting for exception handling
    void dump() {
        System.err.println(getFileName());
        System.err.println(getComplexTypeToDefinitions());
        System.err.println(getPrimitiveTypeToDefinitions());
    }
}
