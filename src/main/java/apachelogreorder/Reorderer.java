/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package apachelogreorder;
import java.io.*;
import org.apache.commons.cli.*;

/**
 *
 * @author simone
 */
public class Reorderer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String logNameTemplete = "(www\\.)?(([A-z,-]*\\.){1,}[A-z,-]{2,4})-access\\.log.gz";
        String sourceDir = "/mnt/local/babbage";
        String archiveDir = "/mnt/local/archive";
        Reorderer rd = new Reorderer();
        File inputDir = null;
        
                Options options = new Options();
        Option tmpOption = null;
        
        tmpOption = new Option("a", "archiveDir", true, "Archive Directory");
        options.addOption(tmpOption);
        
        tmpOption = new Option("s", "sourceDir", true, "Source Directory");
        options.addOption(tmpOption);
        
        tmpOption = new Option("c", "forSite", false, "Create for site directory hierarcy");
        options.addOption(tmpOption);
        
        tmpOption = new Option("d", "debug", false, "Debug mode");
        options.addOption(tmpOption);
        
        tmpOption = new Option("v", "verbose", true, "Verbose function");
        options.addOption(tmpOption);
        
        
        CommandLineParser parser = new GnuParser();
        CommandLine cmd = null;
        
        try {
            cmd = parser.parse(options, args);
            
            inputDir = new File(sourceDir);
        

            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }


    }

    public void reorderDir(File dir) {
        File origin = null;
        ApacheLogFile alf = null;

        File[] filesList = dir.listFiles();
        for(File i : filesList) {
            if (i.isFile()) {
                alf = ApacheLogFile.parseFile(i);

            }
        }


    }

    

}