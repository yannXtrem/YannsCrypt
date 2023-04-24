/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yannscrypt;

/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class TableConversion {
    private char decimales[];
    private final String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 .,;[]";
    public final int MAX = 63;
    private int decalage = 0;
    public final void genDec(){
        decimales = new char[64];
        for(int i = 0, j = MAX-decalage; i <= caracteres.length()-1; i++, j--){
            decimales[j] = caracteres.charAt(i);
            if(j == 0) j = MAX;
            System.out.print("["+j+":"+caracteres.charAt(i)+"]");
        }
    }
    public int getDecalage(){
        return decalage;
    }
    public char getChar(int c){
        return decimales[c];
    }
    public void setDecalage(int d){
        decalage = d;
    }
    public String getCaractere(int index){
        return String.valueOf(caracteres.charAt(index));
    }
    public TableConversion(int d) {
        decalage = d;
        genDec();
    }
    public TableConversion(){
        genDec();
    }
    public int toDec(char c){
        String _c = ".";
        _c = _c.replace('.', c);
        if(!caracteres.contains(_c)){
            System.out.println("Caractere ["+c+"] non pris en charge par l'algorithme");
        } else {
            boolean found = false;
            int i = MAX;
            while(found == false){
                if(decimales[i] == c){
                    System.out.println("\ndec('"+c+"') = "+i);
                    return i;
                }
                --i;
            }
        }
        return 0;
    }
    private int num(char hex){
        switch(hex){
            case '0' -> {
                return 0;
            }
            case '1' -> {
                return 1;
            }
            case '2' -> {
                return 2;
            }
            case '3' -> {
                return 3;
            }
            case '4' -> {
                return 4;
            }
            case '5' -> {
                return 5;
            }
            case '6' -> {
                return 6;
            }
            case '7' -> {
                return 7;
            }
            case '8' -> {
                return 8;
            }
            case '9' -> {
                return 9;
            }
            case 'a' -> {
                return 10;
            }
            case 'b' -> {
                return 11;
            }
            case 'c' -> {
                return 12;
            }
            case 'd' -> {
                return 13;
            }
            case 'e' -> {
                return 14;
            }
            case 'f' -> {
                return 15;
            }
        }
        return -1;
    }
    public int fromHex(String c){
        return num(c.charAt(0));
    }
    public String toHex(int c){
        String th = Integer.toHexString(c);
        System.out.println("toHex('"+c+"') = "+th.toUpperCase());
        //if(th.length() < 2) th = "0"+th;
        return th;
    }
}
