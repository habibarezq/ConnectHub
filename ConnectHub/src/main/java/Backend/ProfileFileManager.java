
package Backend;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.*;

//importing the FilePaths and FileManager interfaces
import Interfaces.*;

public class ProfileFileManager implements FileManager<UserProfile>{


    @Override
    public void saveToFile(ArrayList<UserProfile> data) {
        
    }

    @Override
    public void readFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
