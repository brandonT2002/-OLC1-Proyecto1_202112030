```java
INIT ::=
    '{' PARAMS '}' |
    error

PARAMS ::= 
    PARAMS ',' PARAM |
    PARAM

PARAM ::=
    TK_string ':' TK_string |
    TK_string ':' TK_double
```