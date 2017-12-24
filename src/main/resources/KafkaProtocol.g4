grammar KafkaProtocol;

bnf_element: PRE_START bnf_lines PRE_END;

bnf_lines: (bnf_line)+;

bnf_line: INDENT* left_side ARROW right_side;

left_side: ( entity_name | complex_type ) ( version )? ;

entity_name: ENTITY_NAME+;

version: OPEN_PARANTHESIS VERSION version_number CLOSING_PARANTHESIS;

version_number: DIGIT;

right_side: ( complex_type | primitive_type | array )+;

array: OPEN_BRACKET ( complex_type | primitive_type ) CLOSING_BRACKET;

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
