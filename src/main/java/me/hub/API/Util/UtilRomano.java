package me.hub.API.Util;

import javax.swing.JOptionPane;


public class UtilRomano {


    public static String decimalParaRomano( int decimal ) 
            throws ArithmeticException {

        return rearranjarNumero( gerarRepresentacaoBase( decimal ) );

    }


    public static String gerarRepresentacaoBase( int decimal )
            throws ArithmeticException {

        if ( decimal > 0 && decimal < 4000 ) {

            StringBuilder sb = new StringBuilder();

            // M
            sb.append( gerarNumeroBase( decimal, 1000, 'M' ) );
            decimal %= 1000;

            // D
            sb.append( gerarNumeroBase( decimal, 500, 'D' ) );
            decimal %= 500;

            // C
            sb.append( gerarNumeroBase( decimal, 100, 'C' ) );
            decimal %= 100;

            // L
            sb.append( gerarNumeroBase( decimal, 50, 'L' ) );
            decimal %= 50;

            // X
            sb.append( gerarNumeroBase( decimal, 10, 'X' ) );
            decimal %= 10;

            // V
            sb.append( gerarNumeroBase( decimal, 5, 'V' ) );
            decimal %= 5;

            // I
            sb.append( gerarNumeroBase( decimal, 1, 'I' ) );

            return sb.toString();

        } else {

            throw new ArithmeticException( "O intervalo para conversão é [1;3999]" );

        }

    }


    private static String rearranjarNumero( String numero ) {

        StringBuilder sb = new StringBuilder();

        int qM = 0;
        int qD = 0;
        int qC = 0;
        int qL = 0;
        int qX = 0;
        int qV = 0;
        int qI = 0;

        // conta a quantidade de cada algarismo
        for ( char n : numero.toCharArray() ) {

            switch ( n ) {
                case 'M': qM++; break;
                case 'D': qD++; break;
                case 'C': qC++; break;
                case 'L': qL++; break;
                case 'X': qX++; break;
                case 'V': qV++; break;
                case 'I': qI++; break;
            }

        }

        // reconstrói o número invertido, começando pela unidade
        boolean pularProximo = false;

        // trata I, V e X
        if ( qI == 4 ) {
            if ( qV == 1 ) {
                sb.append( "XI" );
                pularProximo = true;
            } else {
                sb.append( "VI" );
                pularProximo = true;
            }
        } else {
            sb.append( replicarSimbolo( qI, 'I' ) );
            pularProximo = false;
        }

        if ( !pularProximo ) {
            sb.append( replicarSimbolo( qV, 'V' ) );
        }

        // trata X, L e C
        if ( qX == 4 ) {
            if ( qL == 1 ) {
                sb.append( "CX" );
                pularProximo = true;
            } else {
                sb.append( "LX" );
                pularProximo = true;
            }
        } else {
            sb.append( replicarSimbolo( qX, 'X' ) );
            pularProximo = false;
        }

        if ( !pularProximo ) {
            sb.append( replicarSimbolo( qL, 'L' ) );
        }

        // trata C, D e M
        if ( qC == 4 ) {
            if ( qD == 1 ) {
                sb.append( "MC" );
                pularProximo = true;
            } else {
                sb.append( "DC" );
                pularProximo = true;
            }
        } else {
            sb.append( replicarSimbolo( qC, 'C' ) );
            pularProximo = false;
        }

        if ( !pularProximo ) {
            sb.append( replicarSimbolo( qD, 'D' ) );
        }

        // M
        sb.append( replicarSimbolo( qM, 'M' ) );

        // inverte
        return sb.reverse().toString();

    }

    private static String gerarNumeroBase( int valor, int dividirPor, char simbolo ) {
        return replicarSimbolo( valor / dividirPor, simbolo );
    }


    private static String replicarSimbolo( int quantidade, char simbolo ) {

        StringBuilder sb = new StringBuilder();

        for ( int i = 0;  i < quantidade; i++ ) {
            sb.append( simbolo );
        }

        return sb.toString();

    }

}