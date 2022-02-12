package analizador_lexico;

%%

%class Analizador
%line
%column
%public
%type Object

espacio = [ ,\t,\r,\n]+
vocal = [aA,eE,iI,oO,uU]
numero = [0-9]+

%{
    private String dato;
    private int fila;
    private int columna;
    private int tipo;
    private int vocales = 0;
    private int cantidadVocales;

    public int getTipo(){
        return tipo;
    }

    public int getFila(){
        return fila;
    }

    public int getColumna(){
        return columna;
    }

    public int getCantidadVocales(){
        return cantidadVocales;
    }
%}

%%

{espacio} {tipo = 1; cantidadVocales = vocales; vocales = 0; return new Object();}
{vocal} {vocales++;}
{numero} {tipo = 2; fila = yyline; columna = yycolumn; cantidadVocales = vocales; vocales = 0; return new Object();}
[^] {}
