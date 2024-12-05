
package Backend;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.*;

//importing the FilePaths and FileManager interfaces
import Interfaces.*;

public class ProfileFileManager implements FileManager<Profile>{

    @Override
    public void readFromFile(String FILE_PATH) {

    }

    @Override
    public void saveToFile(ArrayList<Profile> data, String FILE_PATH) {
        
    }
    
}
