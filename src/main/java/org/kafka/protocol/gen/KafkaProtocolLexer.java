// Generated from /Users/marco.helmich/personal/playground/projects/kafka-protocol/src/main/resources/KafkaProtocol.g4 by ANTLR 4.7
package org.kafka.protocol.gen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KafkaProtocolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VERSION=1, DIGIT=2, TYPE_NAME=3, ENTITY_NAME=4, PRIMITIVE_TYPE=5, ARROW=6, 
		PRE_START=7, PRE_END=8, NEW_LINE=9, OPEN_PARANTHESIS=10, CLOSING_PARANTHESIS=11, 
		OPEN_BRACKET=12, CLOSING_BRACKET=13, INDENT=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"VERSION", "DIGIT", "TYPE_NAME", "ENTITY_NAME", "PRIMITIVE_TYPE", "ARROW", 
		"PRE_START", "PRE_END", "NEW_LINE", "OPEN_PARANTHESIS", "CLOSING_PARANTHESIS", 
		"OPEN_BRACKET", "CLOSING_BRACKET", "INDENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Version:'", null, null, null, null, "'=>'", "'<pre>'", "'</pre>'", 
		"'\n'", "'('", "')'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VERSION", "DIGIT", "TYPE_NAME", "ENTITY_NAME", "PRIMITIVE_TYPE", 
		"ARROW", "PRE_START", "PRE_END", "NEW_LINE", "OPEN_PARANTHESIS", "CLOSING_PARANTHESIS", 
		"OPEN_BRACKET", "CLOSING_BRACKET", "INDENT", "WS"
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


	public KafkaProtocolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "KafkaProtocol.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\u0096\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\6\4/\n\4\r\4\16\4\60\3\5\3\5\6\5"+
		"\65\n\5\r\5\16\5\66\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6t\n\6\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\2\2\21\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"\3\2\6\4\2aac|\4\2C\\c|\5\2//aac|\5\2\13\f\17\17\"\"\2\u009f\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5*\3\2\2\2\7,\3\2"+
		"\2\2\t\62\3\2\2\2\13s\3\2\2\2\ru\3\2\2\2\17x\3\2\2\2\21~\3\2\2\2\23\u0085"+
		"\3\2\2\2\25\u0087\3\2\2\2\27\u0089\3\2\2\2\31\u008b\3\2\2\2\33\u008d\3"+
		"\2\2\2\35\u008f\3\2\2\2\37\u0092\3\2\2\2!\"\7X\2\2\"#\7g\2\2#$\7t\2\2"+
		"$%\7u\2\2%&\7k\2\2&\'\7q\2\2\'(\7p\2\2()\7<\2\2)\4\3\2\2\2*+\4\62;\2+"+
		"\6\3\2\2\2,.\4c|\2-/\t\2\2\2.-\3\2\2\2/\60\3\2\2\2\60.\3\2\2\2\60\61\3"+
		"\2\2\2\61\b\3\2\2\2\62\64\t\3\2\2\63\65\t\4\2\2\64\63\3\2\2\2\65\66\3"+
		"\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67\n\3\2\2\289\7K\2\29:\7P\2\2:;\7"+
		"V\2\2;<\78\2\2<t\7\66\2\2=>\7K\2\2>?\7P\2\2?@\7V\2\2@A\7\65\2\2At\7\64"+
		"\2\2BC\7K\2\2CD\7P\2\2DE\7V\2\2EF\7\63\2\2Ft\78\2\2GH\7K\2\2HI\7P\2\2"+
		"IJ\7V\2\2Jt\7:\2\2KL\7U\2\2LM\7V\2\2MN\7T\2\2NO\7K\2\2OP\7P\2\2Pt\7I\2"+
		"\2QR\7T\2\2RS\7G\2\2ST\7E\2\2TU\7Q\2\2UV\7T\2\2VW\7F\2\2Wt\7U\2\2XY\7"+
		"P\2\2YZ\7W\2\2Z[\7N\2\2[\\\7N\2\2\\]\7C\2\2]^\7D\2\2^_\7N\2\2_`\7G\2\2"+
		"`a\7a\2\2ab\7U\2\2bc\7V\2\2cd\7T\2\2de\7K\2\2ef\7P\2\2ft\7I\2\2gh\7D\2"+
		"\2hi\7Q\2\2ij\7Q\2\2jk\7N\2\2kl\7G\2\2lm\7C\2\2mt\7P\2\2no\7D\2\2op\7"+
		"[\2\2pq\7V\2\2qr\7G\2\2rt\7U\2\2s8\3\2\2\2s=\3\2\2\2sB\3\2\2\2sG\3\2\2"+
		"\2sK\3\2\2\2sQ\3\2\2\2sX\3\2\2\2sg\3\2\2\2sn\3\2\2\2t\f\3\2\2\2uv\7?\2"+
		"\2vw\7@\2\2w\16\3\2\2\2xy\7>\2\2yz\7r\2\2z{\7t\2\2{|\7g\2\2|}\7@\2\2}"+
		"\20\3\2\2\2~\177\7>\2\2\177\u0080\7\61\2\2\u0080\u0081\7r\2\2\u0081\u0082"+
		"\7t\2\2\u0082\u0083\7g\2\2\u0083\u0084\7@\2\2\u0084\22\3\2\2\2\u0085\u0086"+
		"\7\f\2\2\u0086\24\3\2\2\2\u0087\u0088\7*\2\2\u0088\26\3\2\2\2\u0089\u008a"+
		"\7+\2\2\u008a\30\3\2\2\2\u008b\u008c\7]\2\2\u008c\32\3\2\2\2\u008d\u008e"+
		"\7_\2\2\u008e\34\3\2\2\2\u008f\u0090\7\"\2\2\u0090\u0091\7\"\2\2\u0091"+
		"\36\3\2\2\2\u0092\u0093\t\5\2\2\u0093\u0094\3\2\2\2\u0094\u0095\b\20\2"+
		"\2\u0095 \3\2\2\2\6\2\60\66s\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}