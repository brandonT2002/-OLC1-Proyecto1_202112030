```java
INIT ::= 
    'void' 'main' '(' ')' '{' INSTRUCTIONS '}' |
    'void' 'main' '(' ')' '{' '}'

INSTRUCTIONS ::= 
    INSTRUCTIONS INSTRUCTION |
    INSTRUCTION

INSTRUCTION ::= 
    DECLID       ';' |
    REASIGN      ';' |
    IFSTRUCT         |
    SWITCHSTRUCT     |
    LOOPFOR          |
    LOOPWHILE        |
    LOOPDOWHILE      |
    PRINT            |
    'break'      ';' |
    DECFUNC          |
    error

DECLID ::= 
    TYPE TK_id '=' EXP |
    TYPE TK_id

REASIGN ::= 
    TK_id '=' EXP

IFSTRUCT ::= 
    'if' '(' EXP ')' ENV 'else' IFSTRUCT |
    'if' '(' EXP ')' ENV 'else' ENV      |
    'if' '(' EXP ')' ENV

SWITCHSTRUCT ::= 
    'switch' '(' EXP ')' ENVS

ENVS ::= 
    '{' CASESDEFAULT '}' |
    '{' '}'

CASESDEFAULT ::= 
    CASES DEFAULT |
    CASES         |
    DEFAULT

CASES ::= 
    CASES CASE |
    CASE

CASE ::= 
    'case' EXP ':' INSTRUCTIONS |
    'case' EXP ':'

DEFAULT ::= 
    'default' ':' INSTRUCTIONS |
    'default' ':'

LOOPFOR ::= 
    'for' '(' ARGSFOR ')' ENV

ARGSFOR ::= 
    TYPE TK_id '=' EXP ';' EXPF ';' TK_id '++'

EXPF ::= 
    TK_id '<=' EXPFOR |
    TK_id '<'  EXPFOR

EXPFOR ::= 
    EXPFOR '+' EXPFOR |
    EXPFOR '-' EXPFOR |
    EXPFOR '*' EXPFOR |
    EXPFOR '/' EXPFOR |
    '(' EXPFOR ')'    |
    TK_id             |
    'int'             |
    'double'

LOOPWHILE ::= 
    'while' '(' EXP ')' ENV

LOOPDOWHILE ::= 
    'do' ENV 'while' '(' EXP ')' ';'

PRINT ::= 
    'Console.Write' '(' EXP ')' ';' |
    'Console.Write' '(' ')' ';'

ENV ::= 
    '{' INSTRUCTIONS '}' |
    '{' '}'

TYPE ::= 
    'string' |
    'int'    |
    'bool'   |
    'char'   |
    'double'

EXP ::= 
    ARITHMETICS |
    RELATIONALS |
    LOGICS      |
    TK_id       |
    'string'    |
    'char'      |
    'int'       |
    'double'    |
    'true'      |
    'false'     |
    '(' EXP ')'

ARITHMETICS ::= 
    EXP '+' EXP |
    EXP '-' EXP |
    EXP '*' EXP |
    EXP '/' EXP |
    '-' EXP %prec TK_uminus

RELATIONALS ::= 
    EXP '==' EXP |
    EXP '!=' EXP |
    EXP '<=' EXP |
    EXP '>=' EXP |
    EXP '<'  EXP |
    EXP '>'  EXP

LOGICS ::= 
    EXP '&&' EXP |
    EXP '||' EXP |
    '!' EXP

INSTRUCTIONSEST ::= 
    INSTRUCTIONSEST INSTRUCTIONEST |
    INSTRUCTIONEST

INSTRUCTIONEST ::= 
    DECLIDEST ';' |
    ARRAYSDEF

DECFUNC ::= 
    'void' 'DefinirGlobales' '(' ')' ENVEST |
    'void' 'GraficaBarras'   '(' ')' ENVEST |
    'void' 'GraficaPie'      '(' ')' ENVEST

DECLIDEST ::= 
    'string' 'Titulo'  '=' EXPEST |
    'string' 'TituloX' '=' EXPEST |
    'string' 'TituloY' '=' EXPEST |
    TYPE TK_id '=' EXPEST         |
    TYPE TK_id

ARRAYSDEF ::= 
    'string' '[' ']' 'Ejex'    '=' '{' VALUES '}' ';' |
    'double' '[' ']' 'Valores' '=' '{' VALUES '}' ';'

VALUES ::= 
    VALUES ',' EXPEST |
    EXPEST

ENVEST ::= 
    '{' INSTRUCTIONSEST '}' |
    '{' '}'

TYPEEST ::= 
    'string' |
    'double' ;

EXPEST ::= 
    SYMBSTAT |
    TK_id    |
    'string' |
    'double' |
    '(' EXPEST ')'

SYMBSTAT ::= 
    '$' '{' 'NewValor' ',' 'string' ',' 'string' '}'

```