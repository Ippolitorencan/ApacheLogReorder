/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package apachelogreorder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

/**
 *
 * @author simone
 */
public class ApacheLogFile {
    public static String LOG_FILE_FORMAT = "(www\\.)?(([A-z,-]*\\.){1,}[A-z,-]{2,4})-access\\.log.gz";
    public static String LOG_RECORD_FORMAT = "";
    public static Pattern filePattern = null;
    public static Pattern logPattern = null;
    public static SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat logDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
    
    static {
        filePattern = Pattern.compile(LOG_FILE_FORMAT);
        logPattern = Pattern.compile(LOG_RECORD_FORMAT);
    }

    private File origin = null;
    private String site = null;
    private Date logDate = null;
    private Hashtable<String, String> metadata = new Hashtable();

    public ApacheLogFile() {

    }

    public File getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(File origin) {
        this.origin = origin;
    }

    /**
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site the site to set
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @return the logDate
     */
    public Date getLogDate() {
        return logDate;
    }

    /**
     * @param logDate the logDate to set
     */
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public void setMetadataValue(String name, String value) {
        metadata.put(name, value);
    }

    public String getMetadataValue(String name) {
        return metadata.get(name);
    }

    static public ApacheLogFile parseFile(File file) {
        Matcher m = null;
        ApacheLogFile f = null;
        String site = null;
        String dateString = null;
        String fileName = file.getName();

        if (file.isFile()) {
            m = filePattern.matcher(fileName);
            if (m.matches()) {
                try {
                    f = new ApacheLogFile();
                    site = m.group(2);
                    dateString = m.group(4);

                    f.setSite(site);
                    f.setOrigin(file);
                    f.setLogDate(fileDateFormat.parse(dateString));

                    // Verify interna data


                } catch(ParseException ex) {
                    f = null;
                }
            }
        }

        return f;
    }


    static private Date getInternalLogDate(File file) {
        BufferedReader br = null;
        FileReader fr = null;
        int randomSeek = (int)(Math.random() * 1000);
        int lineNumber = 0;
        Date result = null;
        String line = null;
        String prevLine = null;
        Matcher m = null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            while (lineNumber++ < randomSeek && ((line = br.readLine()) != null)) {
                prevLine = line;
            }
            if (prevLine != null) {
                m = logPattern.matcher(prevLine);
                if (m.matches()) {
                    try {
                        result = logDateFormat.parse(m.group(1));
                    } catch (Exception ex) {
                        
                    }
                }
            }

        } catch(java.io.FileNotFoundException ex) {

        } catch(java.io.IOException ex) {

        }

        return result;
    }
}
