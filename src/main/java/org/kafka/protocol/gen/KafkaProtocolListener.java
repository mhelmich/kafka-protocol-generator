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
package org.kafka.protocol.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KafkaProtocolParser}.
 */
public interface KafkaProtocolListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#bnf_element}.
	 * @param ctx the parse tree
	 */
	void enterBnf_element(KafkaProtocolParser.Bnf_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#bnf_element}.
	 * @param ctx the parse tree
	 */
	void exitBnf_element(KafkaProtocolParser.Bnf_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#bnf_lines}.
	 * @param ctx the parse tree
	 */
	void enterBnf_lines(KafkaProtocolParser.Bnf_linesContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#bnf_lines}.
	 * @param ctx the parse tree
	 */
	void exitBnf_lines(KafkaProtocolParser.Bnf_linesContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#bnf_line}.
	 * @param ctx the parse tree
	 */
	void enterBnf_line(KafkaProtocolParser.Bnf_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#bnf_line}.
	 * @param ctx the parse tree
	 */
	void exitBnf_line(KafkaProtocolParser.Bnf_lineContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#left_side}.
	 * @param ctx the parse tree
	 */
	void enterLeft_side(KafkaProtocolParser.Left_sideContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#left_side}.
	 * @param ctx the parse tree
	 */
	void exitLeft_side(KafkaProtocolParser.Left_sideContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#entity_name}.
	 * @param ctx the parse tree
	 */
	void enterEntity_name(KafkaProtocolParser.Entity_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#entity_name}.
	 * @param ctx the parse tree
	 */
	void exitEntity_name(KafkaProtocolParser.Entity_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#version}.
	 * @param ctx the parse tree
	 */
	void enterVersion(KafkaProtocolParser.VersionContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#version}.
	 * @param ctx the parse tree
	 */
	void exitVersion(KafkaProtocolParser.VersionContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#version_number}.
	 * @param ctx the parse tree
	 */
	void enterVersion_number(KafkaProtocolParser.Version_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#version_number}.
	 * @param ctx the parse tree
	 */
	void exitVersion_number(KafkaProtocolParser.Version_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#right_side}.
	 * @param ctx the parse tree
	 */
	void enterRight_side(KafkaProtocolParser.Right_sideContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#right_side}.
	 * @param ctx the parse tree
	 */
	void exitRight_side(KafkaProtocolParser.Right_sideContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#complex_array}.
	 * @param ctx the parse tree
	 */
	void enterComplex_array(KafkaProtocolParser.Complex_arrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#complex_array}.
	 * @param ctx the parse tree
	 */
	void exitComplex_array(KafkaProtocolParser.Complex_arrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#primitive_array}.
	 * @param ctx the parse tree
	 */
	void enterPrimitive_array(KafkaProtocolParser.Primitive_arrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#primitive_array}.
	 * @param ctx the parse tree
	 */
	void exitPrimitive_array(KafkaProtocolParser.Primitive_arrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#primitive_type}.
	 * @param ctx the parse tree
	 */
	void enterPrimitive_type(KafkaProtocolParser.Primitive_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#primitive_type}.
	 * @param ctx the parse tree
	 */
	void exitPrimitive_type(KafkaProtocolParser.Primitive_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link KafkaProtocolParser#complex_type}.
	 * @param ctx the parse tree
	 */
	void enterComplex_type(KafkaProtocolParser.Complex_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KafkaProtocolParser#complex_type}.
	 * @param ctx the parse tree
	 */
	void exitComplex_type(KafkaProtocolParser.Complex_typeContext ctx);
}