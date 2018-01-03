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

grammar KafkaProtocol;

bnf_element: PRE_START bnf_lines PRE_END;

bnf_lines: (bnf_line)+;

bnf_line: INDENT* left_side ARROW ( right_side )?;

left_side: ( entity_name | complex_type ) ( version )? ;

entity_name: ENTITY_NAME+;

version: OPEN_PARANTHESIS VERSION version_number CLOSING_PARANTHESIS;

version_number: DIGIT;

right_side: ( complex_type | primitive_type | complex_array | primitive_array )+;

complex_array: OPEN_BRACKET complex_type CLOSING_BRACKET;

primitive_array: ARRAY OPEN_PARANTHESIS primitive_type CLOSING_PARANTHESIS;

primitive_type: PRIMITIVE_TYPE;

complex_type: TYPE_NAME;

VERSION
   : 'Version:'
   ;

DIGIT
   : ('0'..'9')
   ;

TYPE_NAME
   : ( 'a'..'z' ) ( 'a'..'z' | '_' )+
   ;

ENTITY_NAME
   : ( 'A'..'Z' | 'a'..'z' ) ( 'a'..'z' | '-' | '_' )+
   ;

PRIMITIVE_TYPE
   : 'INT64' | 'INT32' | 'INT16' | 'INT8' | 'STRING' | 'RECORDS' | 'NULLABLE_STRING' | 'BOOLEAN' | 'BYTES'
   ;

ARROW
   : '=>'
   ;

PRE_START
   : '<pre>'
   ;

PRE_END
   : '</pre>'
   ;

NEW_LINE
   : '\n'
   ;

ARRAY
   : 'ARRAY'
   ;

OPEN_PARANTHESIS
   : '('
   ;

CLOSING_PARANTHESIS
   : ')'
   ;

OPEN_BRACKET
   : '['
   ;

CLOSING_BRACKET
   : ']'
   ;

INDENT
   : ' ' ' '
   ;

WS
   : ( ' ' | '\t' | '\r' | '\n' ) -> channel ( HIDDEN )
   ;
