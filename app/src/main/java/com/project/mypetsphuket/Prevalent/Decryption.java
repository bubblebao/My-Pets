package com.project.mypetsphuket.Prevalent;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Decryption {

    private String input ;
    private String input_password ;
    private String AES = "AES" ;
    private String password = "My Pets PK" ;

    public Decryption(String input) {
        this.input = input;
    }

    public String getDecryption () throws Exception {
        SecretKeySpec key = generateKey(this.input);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE,key);
        byte[] decodedValue = Base64.decode(password ,Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decodedValue);
        return decryptedValue;

    }

    private SecretKeySpec generateKey(String password) throws Exception {


        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = this.password.getBytes("UTF-8");
        digest.update(bytes ,0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");

        return secretKeySpec;
    }
}
