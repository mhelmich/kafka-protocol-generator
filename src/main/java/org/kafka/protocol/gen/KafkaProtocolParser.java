// Generated from /Users/marco.helmich/personal/playground/projects/kafka-protocol/src/main/resources/KafkaProtocol.g4 by ANTLR 4.7
package org.kafka.protocol.gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KafkaProtocolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VERSION=1, DIGIT=2, TYPE_NAME=3, ENTITY_NAME=4, PRIMITIVE_TYPE=5, ARROW=6, 
		PRE_START=7, PRE_END=8, NEW_LINE=9, ARRAY=10, OPEN_PARANTHESIS=11, CLOSING_PARANTHESIS=12, 
		OPEN_BRACKET=13, CLOSING_BRACKET=14, INDENT=15, WS=16;
	public static final int
		RULE_bnf_element = 0, RULE_bnf_lines = 1, RULE_bnf_line = 2, RULE_left_side = 3, 
		RULE_entity_name = 4, RULE_version = 5, RULE_version_number = 6, RULE_right_side = 7, 
		RULE_complex_array = 8, RULE_primitive_array = 9, RULE_primitive_type = 10, 
		RULE_complex_type = 11;
	public static final String[] ruleNames = {
		"bnf_element", "bnf_lines", "bnf_line", "left_side", "entity_name", "version", 
		"version_number", "right_side", "complex_array", "primitive_array", "primitive_type", 
		"complex_type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Version:'", null, null, null, null, "'=>'", "'<pre>'", "'</pre>'", 
		"'\n'", "'ARRAY'", "'('", "')'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VERSION", "DIGIT", "TYPE_NAME", "ENTITY_NAME", "PRIMITIVE_TYPE", 
		"ARROW", "PRE_START", "PRE_END", "NEW_LINE", "ARRAY", "OPEN_PARANTHESIS", 
		"CLOSING_PARANTHESIS", "OPEN_BRACKET", "CLOSING_BRACKET", "INDENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "KafkaProtocol.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public KafkaProtocolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Bnf_elementContext extends ParserRuleContext {
		public TerminalNode PRE_START() { return getToken(KafkaProtocolParser.PRE_START, 0); }
		public Bnf_linesContext bnf_lines() {
			return getRuleContext(Bnf_linesContext.class,0);
		}
		public TerminalNode PRE_END() { return getToken(KafkaProtocolParser.PRE_END, 0); }
		public Bnf_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bnf_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterBnf_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitBnf_element(this);
		}
	}

	public final Bnf_elementContext bnf_element() throws RecognitionException {
		Bnf_elementContext _localctx = new Bnf_elementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_bnf_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(PRE_START);
			setState(25);
			bnf_lines();
			setState(26);
			match(PRE_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bnf_linesContext extends ParserRuleContext {
		public List<Bnf_lineContext> bnf_line() {
			return getRuleContexts(Bnf_lineContext.class);
		}
		public Bnf_lineContext bnf_line(int i) {
			return getRuleContext(Bnf_lineContext.class,i);
		}
		public Bnf_linesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bnf_lines; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterBnf_lines(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitBnf_lines(this);
		}
	}

	public final Bnf_linesContext bnf_lines() throws RecognitionException {
		Bnf_linesContext _localctx = new Bnf_linesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_bnf_lines);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(28);
				bnf_line();
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TYPE_NAME) | (1L << ENTITY_NAME) | (1L << INDENT))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bnf_lineContext extends ParserRuleContext {
		public Left_sideContext left_side() {
			return getRuleContext(Left_sideContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(KafkaProtocolParser.ARROW, 0); }
		public Right_sideContext right_side() {
			return getRuleContext(Right_sideContext.class,0);
		}
		public List<TerminalNode> INDENT() { return getTokens(KafkaProtocolParser.INDENT); }
		public TerminalNode INDENT(int i) {
			return getToken(KafkaProtocolParser.INDENT, i);
		}
		public Bnf_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bnf_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterBnf_line(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitBnf_line(this);
		}
	}

	public final Bnf_lineContext bnf_line() throws RecognitionException {
		Bnf_lineContext _localctx = new Bnf_lineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bnf_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INDENT) {
				{
				{
				setState(33);
				match(INDENT);
				}
				}
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39);
			left_side();
			setState(40);
			match(ARROW);
			setState(41);
			right_side();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Left_sideContext extends ParserRuleContext {
		public Entity_nameContext entity_name() {
			return getRuleContext(Entity_nameContext.class,0);
		}
		public Complex_typeContext complex_type() {
			return getRuleContext(Complex_typeContext.class,0);
		}
		public VersionContext version() {
			return getRuleContext(VersionContext.class,0);
		}
		public Left_sideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left_side; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterLeft_side(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitLeft_side(this);
		}
	}

	public final Left_sideContext left_side() throws RecognitionException {
		Left_sideContext _localctx = new Left_sideContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_left_side);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ENTITY_NAME:
				{
				setState(43);
				entity_name();
				}
				break;
			case TYPE_NAME:
				{
				setState(44);
				complex_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_PARANTHESIS) {
				{
				setState(47);
				version();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Entity_nameContext extends ParserRuleContext {
		public List<TerminalNode> ENTITY_NAME() { return getTokens(KafkaProtocolParser.ENTITY_NAME); }
		public TerminalNode ENTITY_NAME(int i) {
			return getToken(KafkaProtocolParser.ENTITY_NAME, i);
		}
		public Entity_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entity_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterEntity_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitEntity_name(this);
		}
	}

	public final Entity_nameContext entity_name() throws RecognitionException {
		Entity_nameContext _localctx = new Entity_nameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_entity_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				match(ENTITY_NAME);
				}
				}
				setState(53); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ENTITY_NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VersionContext extends ParserRuleContext {
		public TerminalNode OPEN_PARANTHESIS() { return getToken(KafkaProtocolParser.OPEN_PARANTHESIS, 0); }
		public TerminalNode VERSION() { return getToken(KafkaProtocolParser.VERSION, 0); }
		public Version_numberContext version_number() {
			return getRuleContext(Version_numberContext.class,0);
		}
		public TerminalNode CLOSING_PARANTHESIS() { return getToken(KafkaProtocolParser.CLOSING_PARANTHESIS, 0); }
		public VersionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_version; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterVersion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitVersion(this);
		}
	}

	public final VersionContext version() throws RecognitionException {
		VersionContext _localctx = new VersionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_version);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(OPEN_PARANTHESIS);
			setState(56);
			match(VERSION);
			setState(57);
			version_number();
			setState(58);
			match(CLOSING_PARANTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Version_numberContext extends ParserRuleContext {
		public TerminalNode DIGIT() { return getToken(KafkaProtocolParser.DIGIT, 0); }
		public Version_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_version_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterVersion_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitVersion_number(this);
		}
	}

	public final Version_numberContext version_number() throws RecognitionException {
		Version_numberContext _localctx = new Version_numberContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_version_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(DIGIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Right_sideContext extends ParserRuleContext {
		public List<Complex_typeContext> complex_type() {
			return getRuleContexts(Complex_typeContext.class);
		}
		public Complex_typeContext complex_type(int i) {
			return getRuleContext(Complex_typeContext.class,i);
		}
		public List<Primitive_typeContext> primitive_type() {
			return getRuleContexts(Primitive_typeContext.class);
		}
		public Primitive_typeContext primitive_type(int i) {
			return getRuleContext(Primitive_typeContext.class,i);
		}
		public List<Complex_arrayContext> complex_array() {
			return getRuleContexts(Complex_arrayContext.class);
		}
		public Complex_arrayContext complex_array(int i) {
			return getRuleContext(Complex_arrayContext.class,i);
		}
		public List<Primitive_arrayContext> primitive_array() {
			return getRuleContexts(Primitive_arrayContext.class);
		}
		public Primitive_arrayContext primitive_array(int i) {
			return getRuleContext(Primitive_arrayContext.class,i);
		}
		public Right_sideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_side; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterRight_side(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitRight_side(this);
		}
	}

	public final Right_sideContext right_side() throws RecognitionException {
		Right_sideContext _localctx = new Right_sideContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_right_side);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(66); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(66);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case TYPE_NAME:
						{
						setState(62);
						complex_type();
						}
						break;
					case PRIMITIVE_TYPE:
						{
						setState(63);
						primitive_type();
						}
						break;
					case OPEN_BRACKET:
						{
						setState(64);
						complex_array();
						}
						break;
					case ARRAY:
						{
						setState(65);
						primitive_array();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(68); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Complex_arrayContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(KafkaProtocolParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSING_BRACKET() { return getToken(KafkaProtocolParser.CLOSING_BRACKET, 0); }
		public Complex_typeContext complex_type() {
			return getRuleContext(Complex_typeContext.class,0);
		}
		public Primitive_typeContext primitive_type() {
			return getRuleContext(Primitive_typeContext.class,0);
		}
		public Complex_arrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complex_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterComplex_array(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitComplex_array(this);
		}
	}

	public final Complex_arrayContext complex_array() throws RecognitionException {
		Complex_arrayContext _localctx = new Complex_arrayContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_complex_array);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(OPEN_BRACKET);
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_NAME:
				{
				setState(71);
				complex_type();
				}
				break;
			case PRIMITIVE_TYPE:
				{
				setState(72);
				primitive_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(75);
			match(CLOSING_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Primitive_arrayContext extends ParserRuleContext {
		public TerminalNode ARRAY() { return getToken(KafkaProtocolParser.ARRAY, 0); }
		public TerminalNode OPEN_PARANTHESIS() { return getToken(KafkaProtocolParser.OPEN_PARANTHESIS, 0); }
		public Primitive_typeContext primitive_type() {
			return getRuleContext(Primitive_typeContext.class,0);
		}
		public TerminalNode CLOSING_PARANTHESIS() { return getToken(KafkaProtocolParser.CLOSING_PARANTHESIS, 0); }
		public Primitive_arrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitive_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterPrimitive_array(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitPrimitive_array(this);
		}
	}

	public final Primitive_arrayContext primitive_array() throws RecognitionException {
		Primitive_arrayContext _localctx = new Primitive_arrayContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_primitive_array);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(ARRAY);
			setState(78);
			match(OPEN_PARANTHESIS);
			setState(79);
			primitive_type();
			setState(80);
			match(CLOSING_PARANTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Primitive_typeContext extends ParserRuleContext {
		public TerminalNode PRIMITIVE_TYPE() { return getToken(KafkaProtocolParser.PRIMITIVE_TYPE, 0); }
		public Primitive_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitive_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterPrimitive_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitPrimitive_type(this);
		}
	}

	public final Primitive_typeContext primitive_type() throws RecognitionException {
		Primitive_typeContext _localctx = new Primitive_typeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_primitive_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(PRIMITIVE_TYPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Complex_typeContext extends ParserRuleContext {
		public TerminalNode TYPE_NAME() { return getToken(KafkaProtocolParser.TYPE_NAME, 0); }
		public Complex_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complex_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).enterComplex_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaProtocolListener ) ((KafkaProtocolListener)listener).exitComplex_type(this);
		}
	}

	public final Complex_typeContext complex_type() throws RecognitionException {
		Complex_typeContext _localctx = new Complex_typeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_complex_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(TYPE_NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22Y\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\3\6\3 \n\3\r\3\16\3!\3\4\7\4%\n\4\f\4"+
		"\16\4(\13\4\3\4\3\4\3\4\3\4\3\5\3\5\5\5\60\n\5\3\5\5\5\63\n\5\3\6\6\6"+
		"\66\n\6\r\6\16\6\67\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\6\tE\n"+
		"\t\r\t\16\tF\3\n\3\n\3\n\5\nL\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\2\2V\2\32\3\2"+
		"\2\2\4\37\3\2\2\2\6&\3\2\2\2\b/\3\2\2\2\n\65\3\2\2\2\f9\3\2\2\2\16>\3"+
		"\2\2\2\20D\3\2\2\2\22H\3\2\2\2\24O\3\2\2\2\26T\3\2\2\2\30V\3\2\2\2\32"+
		"\33\7\t\2\2\33\34\5\4\3\2\34\35\7\n\2\2\35\3\3\2\2\2\36 \5\6\4\2\37\36"+
		"\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\5\3\2\2\2#%\7\21\2\2$#\3\2"+
		"\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\')\3\2\2\2(&\3\2\2\2)*\5\b\5\2*+\7"+
		"\b\2\2+,\5\20\t\2,\7\3\2\2\2-\60\5\n\6\2.\60\5\30\r\2/-\3\2\2\2/.\3\2"+
		"\2\2\60\62\3\2\2\2\61\63\5\f\7\2\62\61\3\2\2\2\62\63\3\2\2\2\63\t\3\2"+
		"\2\2\64\66\7\6\2\2\65\64\3\2\2\2\66\67\3\2\2\2\67\65\3\2\2\2\678\3\2\2"+
		"\28\13\3\2\2\29:\7\r\2\2:;\7\3\2\2;<\5\16\b\2<=\7\16\2\2=\r\3\2\2\2>?"+
		"\7\4\2\2?\17\3\2\2\2@E\5\30\r\2AE\5\26\f\2BE\5\22\n\2CE\5\24\13\2D@\3"+
		"\2\2\2DA\3\2\2\2DB\3\2\2\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\21"+
		"\3\2\2\2HK\7\17\2\2IL\5\30\r\2JL\5\26\f\2KI\3\2\2\2KJ\3\2\2\2LM\3\2\2"+
		"\2MN\7\20\2\2N\23\3\2\2\2OP\7\f\2\2PQ\7\r\2\2QR\5\26\f\2RS\7\16\2\2S\25"+
		"\3\2\2\2TU\7\7\2\2U\27\3\2\2\2VW\7\5\2\2W\31\3\2\2\2\n!&/\62\67DFK";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}