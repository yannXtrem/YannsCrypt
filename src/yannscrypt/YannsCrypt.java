/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package yannscrypt;

/**
 *
 * @author Yannick MVOGO BEKONO
 */
public class YannsCrypt {

    /**
     * @param texteClaire le texte a encrypter
     * @param clePrivee la cle privee utilisee pendant le cryptage
     * @return le texte crypte
     */
    public static String crypterUnTexte(String texteClaire, String clePrivee)
    {
        int decalage = 3;
        TableConversion tc = new TableConversion();
        tc.setDecalage(decalage);
        tc.genDec();
        TableCodage tC = new TableCodage(clePrivee.toUpperCase(), tc);
        int t = texteClaire.length();
        char claire[] = texteClaire.toUpperCase().toCharArray();
        String crypt = new String();
        for(int i = 0, j = 0; i <= t-1; i++, j++){
            /*if(decalage > tc.MAX) decalage = 1;
            tc.setDecalage(decalage);
            tc.genDec();*/
            if(j > clePrivee.length()-1) j = 0;
            tC.setPrivateKey(clePrivee.substring(j, clePrivee.length()-1));
            tC.genTable();
            crypt += String.valueOf(encryptChar(claire[i], tc, tC));
        }
        System.out.println(texteClaire+" :> "+crypt);
        return crypt;
    }
    public static String decrypterUnTexte(String texteCrypte, String clePrivee){
        int decalage = 3;
        TableConversion tc = new TableConversion();
        tc.setDecalage(decalage);
        tc.genDec();
        TableCodage tC = new TableCodage(clePrivee.toUpperCase(), tc);
        int t = texteCrypte.length();
        char crypt[] = texteCrypte.toCharArray();
        String claire = new String();
        for(int i = 0, j = 0; i <= t-1; i++, j++){
            /*if(decalage > tc.MAX) decalage = 1;
            tc.setDecalage(decalage);
            tc.genDec();*/
            if(j > clePrivee.length()-1) j = 0;
            tC.setPrivateKey(clePrivee.substring(j, clePrivee.length()-1));
            tC.genTable();
            claire += String.valueOf(decryptChar(crypt[i], tc, tC));
        }
        System.out.println(texteCrypte+" :> "+claire);
        return claire;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        String texteClaire = "[1, 2, 3, 4, 5]";
        String clePrivee = "sapd";
        int decalage = 3;
        String texteCrypte = String.valueOf(crypter(texteClaire, clePrivee, decalage));
        String texteDecrypte = String.valueOf(decrypter(texteCrypte, clePrivee, decalage));
        if(texteClaire.equals(texteDecrypte)){
            System.out.println("Erreur de decryptage");
        }
    }
    private static char encryptChar(char c, TableConversion tc, TableCodage tC){
        int d = tc.toDec(c);
        String h = tc.toHex(d),
            _i = String.valueOf(h.charAt(0)),
            _j = String.valueOf(h.charAt(1));
        int i = tc.fromHex(_i),
            j = tc.fromHex(_j);
        char _c = tC.encode(i, j);
        return _c;
    }
    private static char decryptChar(char c, TableConversion tc, TableCodage tC){
        int _c[] = tC.decode(c);
        String _h = tc.toHex(_c[0])+tc.toHex(_c[1]);
        int _d = Integer.parseInt(_h, 16);
        char __c = tc.getChar(_d);
        return __c;
    }
    private static char[] crypter(String texteClaire, String clePrivee, int decalage){
        TableConversion tc = new TableConversion();
        tc.setDecalage(decalage);
        tc.genDec();
        TableCodage tC = new TableCodage(clePrivee.toUpperCase(), tc);
        int t = texteClaire.length();
        char claire[] = texteClaire.toUpperCase().toCharArray();
        String crypt = new String();
        for(int i = 0, j = 0; i <= t-1; i++, j++){
            /*if(decalage > tc.MAX) decalage = 1;
            tc.setDecalage(decalage);
            tc.genDec();*/
            if(j > clePrivee.length()-1) j = 0;
            tC.setPrivateKey(clePrivee.substring(j, clePrivee.length()-1));
            tC.genTable();
            crypt += String.valueOf(encryptChar(claire[i], tc, tC));
        }
        System.out.println(texteClaire+" :> "+crypt);
        return crypt.toCharArray();
    }
    private static char[] decrypter(String texteCrypte, String clePrivee, int decalage){
        TableConversion tc = new TableConversion();
        tc.setDecalage(decalage);
        tc.genDec();
        TableCodage tC = new TableCodage(clePrivee.toUpperCase(), tc);
        int t = texteCrypte.length();
        char crypt[] = texteCrypte.toCharArray();
        String claire = new String();
        for(int i = 0, j = 0; i <= t-1; i++, j++){
            /*if(decalage > tc.MAX) decalage = 1;
            tc.setDecalage(decalage);
            tc.genDec();*/
            if(j > clePrivee.length()-1) j = 0;
            tC.setPrivateKey(clePrivee.substring(j, clePrivee.length()-1));
            tC.genTable();
            claire += String.valueOf(decryptChar(crypt[i], tc, tC));
        }
        System.out.println(texteCrypte+" :> "+claire);
        return claire.toCharArray();
    }
}
