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
%class Scanner
%public
%cupsym TOK
%cup
%char
%column
%full
%line
%unicode

//Constructor
%init{
    yyline = 1;
    yychar = 1;
%init}

//Expresiones regulares
UNUSED=[ \r\t]+
CONTENT = ([^\n\"\\]|\\.)
ID = (\_)*[a-zA-Z][a-zA-Z0-9\_]*
STRING = \"({CONTENT}*)\"
CHAR = \'({CONTENT})\'
INTEGER = [0-9]+
DOUBLE = [0-9]+\.[0-9]+
COMMENTS = "//"([^\r\n]*)?
COMMENTM = [/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]

%%

/* 3. Reglas Semanticas */
// Reservadas
"main"                  {return new Symbol(TOK.RW_main,      yyline, yychar, yytext());}
"void"                  {return new Symbol(TOK.RW_void,      yyline, yychar, yytext());}
"int"                   {return new Symbol(TOK.RW_int,       yyline, yychar, yytext());}
"double"                {return new Symbol(TOK.RW_double,    yyline, yychar, yytext());}
"char"                  {return new Symbol(TOK.RW_char,      yyline, yychar, yytext());}
"bool"                  {return new Symbol(TOK.RW_bool,      yyline, yychar, yytext());}
"string"                {return new Symbol(TOK.RW_string,    yyline, yychar, yytext());}
"if"                    {return new Symbol(TOK.RW_if,        yyline, yychar, yytext());}
"else"                  {return new Symbol(TOK.RW_else,      yyline, yychar, yytext());}
"switch"                {return new Symbol(TOK.RW_switch,    yyline, yychar, yytext());}
"case"                  {return new Symbol(TOK.RW_case,      yyline, yychar, yytext());}
"default"               {return new Symbol(TOK.RW_default,   yyline, yychar, yytext());}
"break"                 {return new Symbol(TOK.RW_break,     yyline, yychar, yytext());}
"for"                   {return new Symbol(TOK.RW_for,       yyline, yychar, yytext());}
"while"                 {return new Symbol(TOK.RW_while,     yyline, yychar, yytext());}
"do"                    {return new Symbol(TOK.RW_do,        yyline, yychar, yytext());}
"true"                  {return new Symbol(TOK.RW_true,      yyline, yychar, yytext());}
"false"                 {return new Symbol(TOK.RW_false,     yyline, yychar, yytext());}
"Console.Write"         {return new Symbol(TOK.RW_print,     yyline, yychar, yytext());}
"Titulo"                {return new Symbol(TOK.RW_title,     yyline, yychar, yytext());}
"Ejex"                  {return new Symbol(TOK.RW_xAxis,     yyline, yychar, yytext());}
"Valores"               {return new Symbol(TOK.RW_values,    yyline, yychar, yytext());}
"TituloX"               {return new Symbol(TOK.RW_titleX,    yyline, yychar, yytext());}
"TituloY"               {return new Symbol(TOK.RW_titleY,    yyline, yychar, yytext());}
// Funciones Nativas
"DefinirGlobales"       {return new Symbol(TOK.RW_defG,      yyline, yychar, yytext());}
"GraficaBarras"         {return new Symbol(TOK.RW_barG,      yyline, yychar, yytext());}
"GraficaPie"            {return new Symbol(TOK.RW_pieG,      yyline, yychar, yytext());}
"NewValor"              {return new Symbol(TOK.RW_newVal,    yyline, yychar, yytext());}
// Valores
{STRING}                {return new Symbol(TOK.TK_string,    yyline, yychar, yytext());}
{CHAR}                  {return new Symbol(TOK.TK_char,      yyline, yychar, yytext());}
{INTEGER}               {return new Symbol(TOK.TK_int,       yyline, yychar, yytext());}
{DOUBLE}                {return new Symbol(TOK.TK_double,    yyline, yychar, yytext());}
// Variables
{ID}                    {return new Symbol(TOK.TK_id,        yyline, yychar, yytext());}
// Incremento
"++"                    {return new Symbol(TOK.TK_inc,       yyline, yychar, yytext());}
// Operadores Aritméticos
"+"                     {return new Symbol(TOK.TK_plus,      yyline, yychar, yytext());}
"-"                     {return new Symbol(TOK.TK_minus,     yyline, yychar, yytext());}
"*"                     {return new Symbol(TOK.TK_mult,      yyline, yychar, yytext());}
"/"                     {return new Symbol(TOK.TK_div,       yyline, yychar, yytext());}
// Operadores Relacionales
"=="                    {return new Symbol(TOK.TK_equequ,    yyline, yychar, yytext());}
"!="                    {return new Symbol(TOK.TK_notequ,    yyline, yychar, yytext());}
"<="                    {return new Symbol(TOK.TK_lessequ,   yyline, yychar, yytext());}
">="                    {return new Symbol(TOK.TK_moreequ,   yyline, yychar, yytext());}
"="                     {return new Symbol(TOK.TK_equal,     yyline, yychar, yytext());}
"<"                     {return new Symbol(TOK.TK_less,      yyline, yychar, yytext());}
">"                     {return new Symbol(TOK.TK_more,      yyline, yychar, yytext());}
// Operadores Lógicos
"&&"                    {return new Symbol(TOK.TK_and,       yyline, yychar, yytext());}
"||"                    {return new Symbol(TOK.TK_or,        yyline, yychar, yytext());}
"!"                     {return new Symbol(TOK.TK_not,       yyline, yychar, yytext());}
// Símbolos de Agrupación
"$"                     {return new Symbol(TOK.TK_dollar,    yyline, yychar, yytext());}
"("                     {return new Symbol(TOK.TK_lpar,      yyline, yychar, yytext());}
")"                     {return new Symbol(TOK.TK_rpar,      yyline, yychar, yytext());}
"{"                     {return new Symbol(TOK.TK_lbrc,      yyline, yychar, yytext());}
"}"                     {return new Symbol(TOK.TK_rbrc,      yyline, yychar, yytext());}
"["                     {return new Symbol(TOK.TK_lbrk,      yyline, yychar, yytext());}
"]"                     {return new Symbol(TOK.TK_rbrk,      yyline, yychar, yytext());}
// Fin de Instrucciones
","                     {return new Symbol(TOK.TK_comma,     yyline, yychar, yytext());}
":"                     {return new Symbol(TOK.TK_colon,     yyline, yychar, yytext());}
";"                     {return new Symbol(TOK.TK_semicolon, yyline, yychar, yytext());}
\n                      {yychar = 1;}
{UNUSED}                {}
{COMMENTS}              {}
{COMMENTM}              {}
.                       {addError(yyline, yychar, yytext());}