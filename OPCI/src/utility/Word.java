/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.Arrays;

/**
 *
 * @author Kosmic
 */
public class Word {

    private byte[] word;

    public Word() {

        this((short) 0);
    }

    public Word(short val) {

        word = new byte[2];
        word[0] = (byte) (val & 0xff);
        word[1] = (byte) ((val >> 8) & 0xff);
    }

    public Word(byte first, byte second) {

        word = new byte[2];
        word[0] = second;
        word[1] = first;
    }
    
    public byte[] getBytes(){
        
        return Arrays.copyOf(word, 2);
    }
    
    public int getUnsignedValue(){
        
        return Short.toUnsignedInt((short) (((short) word[1] * 256) + ((short) word[0])));
    }
    
    public int getIntValue(){
        
        return ((int) word[1] * 256) + ((int) word[0]);
    }

    @Override
    public String toString() {

        return bytesToHex(word).toUpperCase();
    }

    public static String bytesToHex(byte[] in) {

        final StringBuilder builder = new StringBuilder();
        for (byte b : in) {

            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }
}
