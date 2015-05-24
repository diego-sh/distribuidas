/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.util;

import java.security.MessageDigest;

/**
 *
 * @author hendrix
 */
public class EncripcionUtil {
    
    public static String encriptarMD5(String cadena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hashedBytes = digest.digest(cadena.getBytes("UTF-8"));

            return EncripcionUtil.convertByteArrayToHexString(hashedBytes);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Could not generate hash from String", ex);
        }
    }
    
    public static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
    
}
