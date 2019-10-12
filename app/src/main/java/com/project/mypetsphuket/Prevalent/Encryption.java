package com.project.mypetsphuket.Prevalent;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

    private String input ;
    private String input_password ;
    private String AES = "AES" ;
    private String password = "My Pets PK" ;

    public Encryption(String input) {
        this.input = input;
    }

    public String getEncryption( ) throws Exception {

        SecretKeySpec key = generateKey(this.password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encVal = c.doFinal(input.getBytes());
        String encryptedValue = Base64.encodeToString(encVal ,Base64.DEFAULT);

        return encryptedValue;
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
