package com.cursos.app.util;

public class CodeProvider {

    public static String generateCode(String prefix, long current, int maxLengt){
        StringBuilder sCode = new StringBuilder();
        sCode.append(completeZeros(prefix, maxLengt - (prefix.length() + String.valueOf(current).length())));
        sCode.append(current);
        return sCode.toString();
    }

    public static String completeZeros(String text, int quantity){
        StringBuilder sText = new StringBuilder();
        sText.append(text);

        for(int i=0; i<quantity; i++){
            sText.append("0");
        }
        return sText.toString();
    }

}
