/*
 * Copyright 2018 Marco Helmich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Generated from /Users/marco.helmich/personal/playground/projects/kafka-protocol/src/main/resources/KafkaProtocol.g4 by ANTLR 4.7
package org.mhelmich.kafka.protocol.generator.gen;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KafkaProtocolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VERSION=1, DIGIT=2, TYPE_NAME=3, ENTITY_NAME=4, PRIMITIVE_TYPE=5, ARROW=6, 
		PRE_START=7, PRE_END=8, NEW_LINE=9, ARRAY=10, OPEN_PARANTHESIS=11, CLOSING_PARANTHESIS=12, 
		OPEN_BRACKET=13, CLOSING_BRACKET=14, INDENT=15, WS=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"VERSION", "DIGIT", "TYPE_NAME", "ENTITY_NAME", "PRIMITIVE_TYPE", "ARROW", 
		"PRE_START", "PRE_END", "NEW_LINE", "ARRAY", "OPEN_PARANTHESIS", "CLOSING_PARANTHESIS", 
		"OPEN_BRACKET", "CLOSING_BRACKET", "INDENT", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\u009e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\6\4\61\n\4\r\4\16\4\62"+
		"\3\5\3\5\6\5\67\n\5\r\5\16\58\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6v\n\6\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\2\2\22\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22\3\2\6\4\2aac|\4\2C\\c|"+
		"\5\2//aac|\5\2\13\f\17\17\"\"\2\u00a7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\3#\3\2\2\2\5,\3\2\2\2\7.\3\2\2\2\t\64\3"+
		"\2\2\2\13u\3\2\2\2\rw\3\2\2\2\17z\3\2\2\2\21\u0080\3\2\2\2\23\u0087\3"+
		"\2\2\2\25\u0089\3\2\2\2\27\u008f\3\2\2\2\31\u0091\3\2\2\2\33\u0093\3\2"+
		"\2\2\35\u0095\3\2\2\2\37\u0097\3\2\2\2!\u009a\3\2\2\2#$\7X\2\2$%\7g\2"+
		"\2%&\7t\2\2&\'\7u\2\2\'(\7k\2\2()\7q\2\2)*\7p\2\2*+\7<\2\2+\4\3\2\2\2"+
		",-\4\62;\2-\6\3\2\2\2.\60\4c|\2/\61\t\2\2\2\60/\3\2\2\2\61\62\3\2\2\2"+
		"\62\60\3\2\2\2\62\63\3\2\2\2\63\b\3\2\2\2\64\66\t\3\2\2\65\67\t\4\2\2"+
		"\66\65\3\2\2\2\678\3\2\2\28\66\3\2\2\289\3\2\2\29\n\3\2\2\2:;\7K\2\2;"+
		"<\7P\2\2<=\7V\2\2=>\78\2\2>v\7\66\2\2?@\7K\2\2@A\7P\2\2AB\7V\2\2BC\7\65"+
		"\2\2Cv\7\64\2\2DE\7K\2\2EF\7P\2\2FG\7V\2\2GH\7\63\2\2Hv\78\2\2IJ\7K\2"+
		"\2JK\7P\2\2KL\7V\2\2Lv\7:\2\2MN\7U\2\2NO\7V\2\2OP\7T\2\2PQ\7K\2\2QR\7"+
		"P\2\2Rv\7I\2\2ST\7T\2\2TU\7G\2\2UV\7E\2\2VW\7Q\2\2WX\7T\2\2XY\7F\2\2Y"+
		"v\7U\2\2Z[\7P\2\2[\\\7W\2\2\\]\7N\2\2]^\7N\2\2^_\7C\2\2_`\7D\2\2`a\7N"+
		"\2\2ab\7G\2\2bc\7a\2\2cd\7U\2\2de\7V\2\2ef\7T\2\2fg\7K\2\2gh\7P\2\2hv"+
		"\7I\2\2ij\7D\2\2jk\7Q\2\2kl\7Q\2\2lm\7N\2\2mn\7G\2\2no\7C\2\2ov\7P\2\2"+
		"pq\7D\2\2qr\7[\2\2rs\7V\2\2st\7G\2\2tv\7U\2\2u:\3\2\2\2u?\3\2\2\2uD\3"+
		"\2\2\2uI\3\2\2\2uM\3\2\2\2uS\3\2\2\2uZ\3\2\2\2ui\3\2\2\2up\3\2\2\2v\f"+
		"\3\2\2\2wx\7?\2\2xy\7@\2\2y\16\3\2\2\2z{\7>\2\2{|\7r\2\2|}\7t\2\2}~\7"+
		"g\2\2~\177\7@\2\2\177\20\3\2\2\2\u0080\u0081\7>\2\2\u0081\u0082\7\61\2"+
		"\2\u0082\u0083\7r\2\2\u0083\u0084\7t\2\2\u0084\u0085\7g\2\2\u0085\u0086"+
		"\7@\2\2\u0086\22\3\2\2\2\u0087\u0088\7\f\2\2\u0088\24\3\2\2\2\u0089\u008a"+
		"\7C\2\2\u008a\u008b\7T\2\2\u008b\u008c\7T\2\2\u008c\u008d\7C\2\2\u008d"+
		"\u008e\7[\2\2\u008e\26\3\2\2\2\u008f\u0090\7*\2\2\u0090\30\3\2\2\2\u0091"+
		"\u0092\7+\2\2\u0092\32\3\2\2\2\u0093\u0094\7]\2\2\u0094\34\3\2\2\2\u0095"+
		"\u0096\7_\2\2\u0096\36\3\2\2\2\u0097\u0098\7\"\2\2\u0098\u0099\7\"\2\2"+
		"\u0099 \3\2\2\2\u009a\u009b\t\5\2\2\u009b\u009c\3\2\2\2\u009c\u009d\b"+
		"\21\2\2\u009d\"\3\2\2\2\6\2\628u\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}