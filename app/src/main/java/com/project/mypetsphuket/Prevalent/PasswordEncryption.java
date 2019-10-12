package com.project.mypetsphuket.Prevalent;

import android.util.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryption {

    private String input ;


    public PasswordEncryption(String input) {
        this.input = input;
    }


    public String getEncryption(){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1 ,messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length()<32){

                hashtext = "0" + hashtext;
            }
            return hashtext;

        }catch (Exception e){
            //To do
            input = "$"+input;
        }


        return input;
    }
}
