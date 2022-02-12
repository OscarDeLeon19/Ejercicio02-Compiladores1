package main;

%%

%class Analizador
%line
%column
%public
%type Object
%ignorecase

espacio = [ ,\t,\r,\n]+
vocal = [a,e,i,o,u]
numero = [0-9]

%{
    private String dato;
    private int fila;
    private int columna;
    private int tipo;
    private int cantidadVocales = 0;

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

{espacio} {System.out.println("Vocales: " + cantidadVocales); cantidadVocales =0 ;}
{vocal} {cantidadVocales++;}
