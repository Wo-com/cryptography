package com.example.allensun.testingapp;

import java.math.BigInteger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by allensun on 10/5/17.
 * on Tubitv.com, allengotstuff@gmail.com
 */
public class Encryptor {

    // 加密
    public static byte[] encrypt(String key, String initVector, String value) {

        try {
            key = hexToString(key);
            initVector = hexToString(initVector);

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: "
                    + toHex(new String(encrypted)));

            return encrypted;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

      // 解密
    public static String decrypt(String key, String initVector, byte[] encrypted) {
        try {

            key = hexToString(key);
            initVector = hexToString(initVector);

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(encrypted);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        String key = "thisismykey11111"; // 128 bit key
        String initVector = "thisismyinitvect"; // 16 bytes IV

        String key_hex = toHex(key);
        String initVector_hex = toHex(initVector);

        System.out.println("key: " + key_hex);
        System.out.println("iniVector: " + initVector_hex);

        System.out.println("decrypted string:" + decrypt(key_hex, initVector_hex,
                encrypt(key_hex, initVector_hex, "Xueyan zheng, student id: 9918382234")));

    }

    public static String toHex(String arg) {
        return new BigInteger(arg.getBytes()).toString(16);
    }

    public static String hexToString(String txtInHex)
    {
        byte [] txtInByte = new byte [txtInHex.length() / 2];
        int j = 0;
        for (int i = 0; i < txtInHex.length(); i += 2)
        {
            txtInByte[j++] = Byte.parseByte(txtInHex.substring(i, i + 2), 16);
        }

        return new String(txtInByte);
    }
}