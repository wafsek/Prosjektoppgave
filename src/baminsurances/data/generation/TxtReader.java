package baminsurances.data.generation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that provides methods for reading .txt-files.
 * <p>
 * Because this class only exists as a utility for the generator classes, its
 * visibility is set to default, preventing classes outside the
 * {@link baminsurances.data.generation} package from accessing it.
 * 
 * @author Martin Jackson
 */
class TxtReader {
    private File txtFile;
    
    public TxtReader(File txtFile) {
        this.txtFile = txtFile;
    }
    
    public void setFile(File txtFile) {
        this.txtFile = txtFile;
    }
    
    public List<String> getLines() {
        try (BufferedReader in = new BufferedReader(new FileReader(txtFile))) {
            List<String> lines = new ArrayList<>();
            String line = null;
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
