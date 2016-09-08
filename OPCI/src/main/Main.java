/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        if (!loadedFiles.isEmpty()) {

            loadedFiles.add(fSelec);
            fSelec = (String) JOptionPane.showInputDialog(null, null, "File Selection", 0, null, loadedFiles.toArray(), "Input new file");

        }

        if (fSelec.equals("Input New File")) {

            fSelec = (String) JOptionPane.showInputDialog(null, "Please input the file path", "File Input", 0);
        }
        
        System.out.println(fSelec);
    }
}
