package baminsurances.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that provides methods for reading files with the .cvs
 * extension. The methods are ones we implement as we find the need for them,
 * and so they might seem somewhat sparse.
 * 
 * @author martin
 */
public class CvsReader {
    private File cvsFile;
    private String splitter;
    
    /**
     * Creates a CvsReader that will operate on given file, and use the
     * given splitter to split values on a line.
     * 
     * @param cvsFile the .cvs file
     * @param splitter used to split words on a line in the file
     */
    public CvsReader(File cvsFile, String splitter) {
        this.cvsFile = cvsFile;
        this.splitter = splitter;
    }
    
    /**
     * Returns the file currently set to this CvsReader.
     * 
     * @return the file currently set to this CvsReader
     */
    public File getFile() {
        return cvsFile;
    }
    
    /**
     * Sets the file that the CvsReader will operate on to the given file.
     * 
     * @param cvsFile the new .cvs file
     */
    public void setFile(File cvsFile) {
        this.cvsFile = cvsFile;
    }
    
    /**
     * Returns the splitter used by this CvsReader.
     * 
     * @return the splitter used by this CvsReader
     */
    public String getSplitter() {
        return splitter;
    }
    
    /**
     * Sets this CvsReader's splitter to the given string.
     * 
     * @param splitter the new splitter
     */
    public void setSplitter(String splitter) {
        this.splitter = splitter;
    }
    
    /**
     * Returns a list of the strings that populate the column at the given
     * index.
     * 
     * @param col the index of the column
     * @return a list of the strings that populate the column at the given
     * index
     */
    public List<String> getValuesInColumn(int col) {
        try (BufferedReader in = new BufferedReader(new FileReader(cvsFile))) {
            List<String> list = new ArrayList<>();
            
            // The first line is always skipped, because
            // it should contain table headers.
            in.readLine();
            
            String line = null;
            while ((line = in.readLine()) != null) {
                list.add(line.split(";")[col]);
            }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
