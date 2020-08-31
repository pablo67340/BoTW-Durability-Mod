/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeldaunxtractor;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Bryce
 */
public class ZeldaUnxtractor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File folder = new File("C:\\Users\\Bryce\\Desktop\\botw");

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                String path = file.getPath();
                String name = file.getName();
                System.out.println("path: " + path);
                System.out.println("Converting: " + file.getName());
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "py -m aamp " + path + "/Actor/GeneralParamList/" + name + ".yml " + path + "/Actor/GeneralParamList/" + name + ".bgparamlist");
                try {

                    builder.start().waitFor();
                    File oldBG = new File(path + "/Actor/GeneralParamList/" + name + ".yml");
                    FileUtils.deleteQuietly(oldBG);
                    
                    
                    
                    
                    System.out.println("C:\\Users\\Bryce\\Desktop\\botw\\botw.exe" + " /b " + path + " " + path + ".bactorpack");
                    ProcessBuilder builder2 = new ProcessBuilder("C:\\Users\\Bryce\\Desktop\\botw\\botw.exe", "/b", path, path + ".bactorpack");
                    try {
                        
                        

                        builder2.start().waitFor();
                        ProcessBuilder builder3 = new ProcessBuilder("C:\\Users\\Bryce\\Desktop\\botw\\botw.exe", "/e", path + ".bactorpack");
                        try {

                            builder3.start().waitFor();
                            File bac = new File(path + ".bactorpack");
                            FileUtils.deleteQuietly(bac);

                        } catch (IOException ex) {
                            Logger.getLogger("Error: " + ex.getMessage());
                        }
                    } catch (IOException ex) {
                        Logger.getLogger("Error: " + ex.getMessage());
                    }

                } catch (IOException ex) {
                    Logger.getLogger("Error: " + ex.getMessage());
                } catch (InterruptedException ex) {
                    Logger.getLogger(ZeldaUnxtractor.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        // TODO code application logic here
    }

}
