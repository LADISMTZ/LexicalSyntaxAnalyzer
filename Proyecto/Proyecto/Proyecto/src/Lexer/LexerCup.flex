// Equipo:
// Fernando Ahuatzin Gallardo 173427
// Jesus Fernando Armendáriz Zárate 174117
// Manuel Arturo Pérez Alpuche 173723
// Ricardo Ladislao Martínez Cabrera 174920

package Lexer;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
LETTER   = [a-zA-Z]
DIGIT = [0-9]
SPACE = [ ,\t,\n,\r]+

%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

// Reglas léxicas

if        {return new Symbol(sym.If, yychar, yyline, yytext());}
else      {return new Symbol(sym.Else, yychar, yyline, yytext());}
while     {return new Symbol(sym.While, yychar, yyline, yytext());}
do        {return new Symbol(sym.Do, yychar, yyline, yytext());}
endwhile  {return new Symbol(sym.EndWhile, yychar, yyline, yytext());}
then        {return new Symbol(sym.Then, yychar, yyline, yytext());}
endif       {return new Symbol(sym.EndIf, yychar, yyline, yytext());}
"<"         {return new Symbol(sym.Left, yychar, yyline, yytext());}
"<="        {return new Symbol(sym.LeftEqual, yychar, yyline, yytext());}
">"         {return new Symbol(sym.Right, yychar, yyline, yytext());}
">="        {return new Symbol(sym.RightEqual, yychar, yyline, yytext());}
"<>"        {return new Symbol(sym.Difference, yychar, yyline, yytext());}
"=="        {return new Symbol(sym.Equal, yychar, yyline, yytext());}
"="         {return new Symbol(sym.Assign, yychar, yyline, yytext());}
"+"         {return new Symbol(sym.Plus, yychar, yyline, yytext());}
"-"         {return new Symbol(sym.Minus, yychar, yyline, yytext());}
"*"         {return new Symbol(sym.Mul, yychar, yyline, yytext());}
"/"         {return new Symbol(sym.Div, yychar, yyline, yytext());}
;         {return new Symbol(sym.SemiColon, yychar, yyline, yytext());}
"("         {return new Symbol(sym.LeftParenthesis, yychar, yyline, yytext());}
")"         {return new Symbol(sym.RightParenthesis, yychar, yyline, yytext());}
{SPACE}+    {/*ignore*/}
({LETTER}|"_")({LETTER}|"_"|{DIGIT})* {return new Symbol(sym.Var, yychar, yyline, yytext());}
{DIGIT}+ {return new Symbol(sym.Num, yychar, yyline, yytext());}
("-"{DIGIT}+) {return new Symbol(sym.NegNum, yychar, yyline, yytext());}
("-"?{DIGIT}+)|{DIGIT}+ {return new Symbol(sym.Num, yychar, yyline, yytext());}
. {return new Symbol(sym.UNRECOGNIZEDCHAR, yychar, yyline, yytext());}
