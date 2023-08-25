/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import Components.ErrorL;

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    ArrayList<ErrorL> errors = new ArrayList<>();
    void addError(int line, int column, String character) {
        errors.add(new ErrorL(line, column, character));
    }
    public ArrayList<ErrorL> getErrors() {
        return errors;
    }
%}

//Directivas
%class ScannerJson
%public
%cupsym TOKJSON
%cup
%char
%column
%full
%line
%unicode
%ignorecase

//Constructor
%init{
    yyline = 1;
    yychar = 1;
%init}

//Expresiones regulares
UNUSED=[ \r\t]+
CONTENT = ([^\n\"\\]|\\.)
STRING = \"({CONTENT}*)\"
DOUBLE = [0-9]+\.[0-9]+
COMMENTS = "//"([^\r\n]*)?
COMMENTM = [/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]

%%

/* 3. Reglas Semanticas */
// Valores
{STRING}                {return new Symbol(TOKJSON.TK_string,    yyline, yychar, yytext());}
{DOUBLE}                {return new Symbol(TOKJSON.TK_double,    yyline, yychar, yytext());}
// Símbolos de Agrupación
"{"                     {return new Symbol(TOKJSON.TK_lbrc,      yyline, yychar, yytext());}
"}"                     {return new Symbol(TOKJSON.TK_rbrc,      yyline, yychar, yytext());}
// Fin de Instrucciones
","                     {return new Symbol(TOKJSON.TK_comma,     yyline, yychar, yytext());}
":"                     {return new Symbol(TOKJSON.TK_colon,     yyline, yychar, yytext());}
\n                      {yychar = 1;}
{UNUSED}                {}
{COMMENTS}              {}
{COMMENTM}              {}
.                       {addError(yyline, yychar, yytext());}