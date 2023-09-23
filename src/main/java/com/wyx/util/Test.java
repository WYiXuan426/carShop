package com.wyx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {

    public static String encrypt(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = messageDigest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                hexString.append(String.format("%02x", hashedByte));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String input = "password";
        String input1 =  "password";
        String encrypt = encrypt(input);
        String encrypted = encrypt(input);
        System.out.println(encrypt);
        System.out.println("Encrypted value: " + encrypted);
    }
}