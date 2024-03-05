//Equipo:
//Fernando Ahuatzin Gallardo 173427
//Jesus Fernando Armendáriz Zárate 174117
//Manuel Arturo Pérez Alpuche 173723
//Ricardo Ladislao Martínez Cabrera 174920

package Lexer;
import static Lexer.Tokens.*;
%%
%class Lexer
%type Tokens
LETTER   = [a-zA-Z]
DIGIT = [0-9]
SPACE = [ ,\t,\n,\r]+

%{
    public String lexeme;
%}

%%
// Reglas léxicas

"if"        { return IF;}
"else"      { return ELSE;}
"while"     { return WHILE;}
"do"	    { return DO;}
"endwhile"  { return ENDWHILE;}
"then"	    { return THEN;}
"endif"	    { return ENDIF;}
"<"	        {return LEFT;}
"<="	    {return LEFTEQUAL; }
">"	        {return RIGHT;}
">="	    {return RIGHTEQUAL;}
"<>"        {return DIFFERENCE;}
"=="        {return EQUAL;}
"="         {return ASSIGN;}
"+"         {return PLUS;}
"-"         {return MINUS;}
"*"         {return MUL;}
"/"         {return DIV;}
";"         {return SEMICOLON;}
"("         {return LEFTPARENTHESIS;}
")"         {return RIGHTPARENTHESIS;}
{SPACE}+       {/*ignore*/}
({LETTER}|"_")({LETTER}|"_"|{DIGIT})* {lexeme = yytext(); return VAR;}
("-"{DIGIT}+) {lexeme = yytext(); return NEGNUM;}
{DIGIT}+ {lexeme = yytext(); return NUM;}
. {lexeme = yytext();return NOT_RECOGNIZED;}




