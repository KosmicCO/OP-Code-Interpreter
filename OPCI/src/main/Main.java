/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import main.applications.Interpreter;

/**
 *
 * @author Kosmic
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File loads = new File("loads.txt");
        if (!loads.exists()) {

            try {

                loads.createNewFile();
            } catch (IOException ex) {

                System.out.println("Failed to create loads.txt");
            }
        }

        List<String> loadedFiles = new ArrayList();
        if (loads.exists()) {

            try {

                Scanner reader = new Scanner(loads);
                reader.useDelimiter("\\n");
                for (int i = 0; i < 3; i++) {

                    if (!reader.hasNext()) {

                        break;
                    }

                    loadedFiles.add(reader.next());
                }
            } catch (FileNotFoundException ex) {

                System.out.println("Failed to read from loads.txt");
            }
        }

        String fSelec = "Input New File";
        loadedFiles.add(fSelec);
        if (!loadedFiles.isEmpty()) {

            fSelec = (String) JOptionPane.showInputDialog(null, null, "File Selection", 0, null, loadedFiles.toArray(), "Input new file");
        }

        if (fSelec.equals("Input New File")) {

            fSelec = (String) JOptionPane.showInputDialog(null, "Please input the file path", "File Input", 0);
        }

        loadedFiles.remove("Input New File");
        loadedFiles.add(fSelec);
        File use = new File(fSelec);
        if (use.exists()) {

            System.out.println(loadedFiles);
            try {

                FileWriter writeLoads = new FileWriter(loads);
                for (String dir : loadedFiles) {

                    writeLoads.write(dir + "\n");
                }

                writeLoads.close();
            } catch (IOException ex) {

                System.out.println("Failed to write to loads.txt");
            }

            switch (use.toString().substring(use.toString().lastIndexOf(".") < 0 ? 0 : use.toString().lastIndexOf("."))) {

                case ".data":
                    Interpreter.interpret(use);
                    break;
                case ".btxt":
                    //binary text to data file
                    break;
                default:
                    System.out.println("File not recognized");
            }
        }
    }
}
