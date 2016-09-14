/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.applications;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import utility.Word;

/**
 *
 * @author Kosmic
 */
public class Interpreter {

    private static final Word[] RAM = new Word[65536];

    public static void interpret(File data) {

        try (Scanner transfer = new Scanner(data)) {

            transfer.useDelimiter("\\Z");
            String all = transfer.next();
            byte[] translated = all.getBytes();
            for (int i = 0; i < 65536 && i * 2 + 1 < translated.length; i++) {

                RAM[i] = new Word(translated[i * 2 + 1], translated[i * 2]);
            }
        } catch (FileNotFoundException ex) {

            System.out.println("Failed to read file");
        }
    }

    private static void opcode(Word code, Word in1, Word in2, Word in3) {

        boolean v1 = ((code.getBytes()[1] >> 6) & 0x01) == 1;
        boolean v2 = ((code.getBytes()[1] >> 7) & 0x01) == 1;
        in1 = v1 ? in1 : RAM[in1.getUnsignedValue()];
        in2 = v2 ? in2 : RAM[in2.getUnsignedValue()];
        switch((int) (code.getUnsignedValue() & 0x3FFF)){
            
            case 0x0001:
                RAM[in3.getUnsignedValue()] = new Word((short) (in1.getIntValue() + in2.getIntValue()));
                break;
            case 0x0010:
                //RAM[in3.getUnsignedValue()] = new Word -With carry flag (add flags);
        }
    }
}
