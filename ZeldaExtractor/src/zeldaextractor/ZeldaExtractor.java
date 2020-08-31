/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zeldaextractor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Bryce
 */
public class ZeldaExtractor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (args.length == 0){
            System.out.println("Please specify a default folder containing botw.exe and files to be extracted");
            return;
        }
        
        List<String> argss = Arrays.asList(args);
        String defaultFolder = "";
        if (argss.contains("-d")){
            defaultFolder = argss.get(argss.indexOf("-d")+1);
        }

        File folder = new File(defaultFolder);

        for (File file : folder.listFiles()) {
            
            
            if (file.getName().contains(".sbactorpack")) {
                System.out.println("Converting: " + file.getName());
                ProcessBuilder builder = new ProcessBuilder(defaultFolder+"\\botw.exe", "/d", file.getPath());

                try {

                    builder.start().waitFor();
                    ProcessBuilder builder2 = new ProcessBuilder(defaultFolder+"\\botw.exe", "/u", file.getPath().replace(".sbactorpack", ".bactorpack"));
                    try {

                        builder2.start().waitFor();
                        
                        ProcessBuilder builder3 = new ProcessBuilder("cmd.exe", "/c", "py -m aamp "+file.getPath().replace(".sbactorpack", "/")+"Actor/GeneralParamList/"+file.getName().replace(".sbactorpack", ".bgparamlist")+" "+file.getPath().replace(".sbactorpack", "/")+"Actor/GeneralParamList/"+file.getName().replace(".sbactorpack", ".yml"));


                        builder3.start().waitFor();
                        File oldBG = new File(file.getPath().replace(".sbactorpack", "/")+"Actor/GeneralParamList/"+file.getName().replace(".sbactorpack", ".bgparamlist"));
                        FileUtils.deleteQuietly(oldBG);
                        
                        File bac = new File(file.getPath().replace(".sbactorpack", ".bactorpack"));
                        FileUtils.deleteQuietly(bac);
                        FileUtils.deleteQuietly(file);
                     

                    } catch (IOException ex) {
                        Logger.getLogger("Error: " + ex.getMessage());
                    }
                    
                } catch (IOException ex) {
                    Logger.getLogger("Error: " + ex.getMessage());
                } catch (InterruptedException ex) {
                    Logger.getLogger(ZeldaExtractor.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        // TODO code application logic here
    }

}
