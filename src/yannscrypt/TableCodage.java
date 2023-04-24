/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yannscrypt;

/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class TableCodage {
    private String privateKey = "0123456789abcdef";
    private char tableCodage[][];
    private TableConversion tc = new TableConversion();
    public void setTableConversion(TableConversion _tc){
        this.tc = _tc;
    }
    public void setPrivateKey(String pk){
        this.privateKey = normalizedPrivateKey(pk);
    }
    private String normalizedPrivateKey(String pk){
        if(pk.length() < 16){
            int l = 16 - pk.length();
            for(int i = l; i >= 1; i--){
                pk += tc.getCaractere(i);
            }
        }
        System.out.println("\nnormalized pk = {"+pk+"}");
        return pk.toUpperCase();
    }
    public TableCodage(String pk, TableConversion _tc){
        
        this.privateKey = normalizedPrivateKey(pk);
        this.tc = _tc;
        genTable();
    }
    public final void genTable(){
        tableCodage = new char[16][16];
        //fonction pour genere automatiquement la table de codage selon certains parametres de la table de conversion
        String controller = new String();
        for(int i = 0; i <= 15; i++){
            for(int j = 0; j <= 15; j++){
                int _c = (i+privateKey.charAt(j));
                char c;
                if(controller.contains(String.valueOf((char)_c)))
                {
                    while(controller.contains(String.valueOf((char)_c))){
                        if(_c < 32) _c = 33;
                        else if(_c > 126 && _c < 161) _c = 162;
                        _c++;
                    }
                }
                c = (char)_c;
                controller += String.valueOf(c);
                tableCodage[i][j] = c;
                System.out.print(c+"("+_c+")["+i+","+j+"]|");
            }
            System.out.println();
        }
    }
    public char encode(int i, int j){
        return tableCodage[i][j];
    }
    public int[] decode(char c){
        int _c[] = new int[2];
        for(int i = 0; i <= 15; i++){
            for(int j = 0; j <= 15; j++){
                if(tableCodage[i][j] == c){
                    _c[0] = i;
                    _c[1] = j;
                    return _c;
                }
            }
        }
        _c[0] = '0';
        _c[1] = '0';
        return _c;
    }
}
