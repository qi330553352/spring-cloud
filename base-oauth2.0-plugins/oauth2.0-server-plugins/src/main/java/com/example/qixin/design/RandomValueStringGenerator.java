package com.example.qixin.design;

import lombok.Setter;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 创  建   时  间： 2018/8/22 22:19
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class RandomValueStringGenerator {

    @Setter
    private Random random;
    @Setter
    private int length;
    private static final char[] DEFAULT_CODEC = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public RandomValueStringGenerator() {
        this(10);
    }

    public RandomValueStringGenerator(int length) {
        this.random = new SecureRandom();
        this.length = length;
    }

    public String generate() {
        byte[] verifierBytes = new byte[this.length];
        this.random.nextBytes(verifierBytes);
        return this.getAuthorizationCodeString(verifierBytes);
    }

    protected String getAuthorizationCodeString(byte[] verifierBytes) {
        char[] chars = new char[verifierBytes.length];

        for(int i = 0; i < verifierBytes.length; ++i) {
            chars[i] = DEFAULT_CODEC[(verifierBytes[i] & 255) % DEFAULT_CODEC.length];
        }

        return new String(chars);
    }

}
