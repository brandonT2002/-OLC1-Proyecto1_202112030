/* 1. Package e importaciones */
package Painter;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import Components.ErrorL;

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    WordPainter painter;
    public ScannerF(java.io.Reader in,WordPainter painter) {
        yyline = 0;
        yychar = 0;
        this.zzReader = in;
        this.painter = painter;
    }
%}

//Directivas
%class ScannerF
%public
%cupsym TOK
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
"main"                  {return new Symbol(TOK.RW_main,      yychar, yylength(), yytext());}
"void"                  {return new Symbol(TOK.RW_void,      yychar, yylength(), yytext());}
"int"                   {return new Symbol(TOK.RW_int,       yychar, yylength(), yytext());}
"double"                {return new Symbol(TOK.RW_double,    yychar, yylength(), yytext());}
"char"                  {return new Symbol(TOK.RW_char,      yychar, yylength(), yytext());}
"bool"                  {return new Symbol(TOK.RW_bool,      yychar, yylength(), yytext());}
"string"                {return new Symbol(TOK.RW_string,    yychar, yylength(), yytext());}
"if"                    {return new Symbol(TOK.RW_if,        yychar, yylength(), yytext());}
"else"                  {return new Symbol(TOK.RW_else,      yychar, yylength(), yytext());}
"switch"                {return new Symbol(TOK.RW_switch,    yychar, yylength(), yytext());}
"case"                  {return new Symbol(TOK.RW_case,      yychar, yylength(), yytext());}
"default"               {return new Symbol(TOK.RW_default,   yychar, yylength(), yytext());}
"break"                 {return new Symbol(TOK.RW_break,     yychar, yylength(), yytext());}
"for"                   {return new Symbol(TOK.RW_for,       yychar, yylength(), yytext());}
"while"                 {return new Symbol(TOK.RW_while,     yychar, yylength(), yytext());}
"do"                    {return new Symbol(TOK.RW_do,        yychar, yylength(), yytext());}
"true"                  {return new Symbol(TOK.RW_true,      yychar, yylength(), yytext());}
"false"                 {return new Symbol(TOK.RW_false,     yychar, yylength(), yytext());}
"Console.Write"         {return new Symbol(TOK.RW_print,     yychar, yylength(), yytext());}
"Titulo"                {return new Symbol(TOK.RW_title,     yychar, yylength(), yytext());}
"Ejex"                  {return new Symbol(TOK.RW_xAxis,     yychar, yylength(), yytext());}
"Valores"               {return new Symbol(TOK.RW_values,    yychar, yylength(), yytext());}
"TituloX"               {return new Symbol(TOK.RW_titleX,    yychar, yylength(), yytext());}
"TituloY"               {return new Symbol(TOK.RW_titleY,    yychar, yylength(), yytext());}
// Funciones Nativas
"DefinirGlobales"       {return new Symbol(TOK.RW_defG,      yychar, yylength(), yytext());}
"GraficaBarras"         {return new Symbol(TOK.RW_barG,      yychar, yylength(), yytext());}
"GraficaPie"            {return new Symbol(TOK.RW_pieG,      yychar, yylength(), yytext());}
"NewValor"              {return new Symbol(TOK.RW_newVal,    yychar, yylength(), yytext());}
// Valores
{STRING}                {return new Symbol(TOK.TK_string,    yychar, yylength(), yytext());}
{CHAR}                  {return new Symbol(TOK.TK_char,      yychar, yylength(), yytext());}
{INTEGER}               {return new Symbol(TOK.TK_int,       yychar, yylength(), yytext());}
{DOUBLE}                {return new Symbol(TOK.TK_double,    yychar, yylength(), yytext());}
// Variables
{ID}                    {return new Symbol(TOK.TK_id,        yychar, yylength(), yytext());}
// Incremento
"++"                    {return new Symbol(TOK.TK_inc,       yychar, yylength(), yytext());}
// Operadores Aritméticos
"+"                     {return new Symbol(TOK.TK_plus,      yychar, yylength(), yytext());}
"-"                     {return new Symbol(TOK.TK_minus,     yychar, yylength(), yytext());}
"*"                     {return new Symbol(TOK.TK_mult,      yychar, yylength(), yytext());}
"/"                     {return new Symbol(TOK.TK_div,       yychar, yylength(), yytext());}
// Operadores Relacionales
"=="                    {return new Symbol(TOK.TK_equequ,    yychar, yylength(), yytext());}
"!="                    {return new Symbol(TOK.TK_notequ,    yychar, yylength(), yytext());}
"<="                    {return new Symbol(TOK.TK_lessequ,   yychar, yylength(), yytext());}
">="                    {return new Symbol(TOK.TK_moreequ,   yychar, yylength(), yytext());}
"="                     {return new Symbol(TOK.TK_equal,     yychar, yylength(), yytext());}
"<"                     {return new Symbol(TOK.TK_less,      yychar, yylength(), yytext());}
">"                     {return new Symbol(TOK.TK_more,      yychar, yylength(), yytext());}
// Operadores Lógicos
"&&"                    {return new Symbol(TOK.TK_and,       yychar, yylength(), yytext());}
"||"                    {return new Symbol(TOK.TK_or,        yychar, yylength(), yytext());}
"!"                     {return new Symbol(TOK.TK_not,       yychar, yylength(), yytext());}
// Símbolos de Agrupación
"$"                     {return new Symbol(TOK.TK_dollar,    yychar, yylength(), yytext());}
"("                     {return new Symbol(TOK.TK_lpar,      yychar, yylength(), yytext());}
")"                     {return new Symbol(TOK.TK_rpar,      yychar, yylength(), yytext());}
"{"                     {return new Symbol(TOK.TK_lbrc,      yychar, yylength(), yytext());}
"}"                     {return new Symbol(TOK.TK_rbrc,      yychar, yylength(), yytext());}
"["                     {return new Symbol(TOK.TK_lbrk,      yychar, yylength(), yytext());}
"]"                     {return new Symbol(TOK.TK_rbrk,      yychar, yylength(), yytext());}
// Fin de Instrucciones
","                     {return new Symbol(TOK.TK_comma,     yychar, yylength(), yytext());}
":"                     {return new Symbol(TOK.TK_colon,     yychar, yylength(), yytext());}
";"                     {return new Symbol(TOK.TK_semicolon, yychar, yylength(), yytext());}
\n                      {}
{UNUSED}                {}
{COMMENTS}              {painter.COMMENT(yychar,yylength());}
{COMMENTM}              {painter.COMMENT(yychar,yylength());}
.                       {painter.ERROR(yychar,yylength());}