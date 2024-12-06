<<<<<<< HEAD
//
//package Backend;
//
//import Interfaces.FileManager;
//import java.util.ArrayList;
//
//
//public class ProfileFileManager implements FileManager<Profile>{
//
//    @Override
//    public void readFromFile(String FILE_PATH) {
//
//    }
//
//    @Override
//    public void saveTOFile(ArrayList<Profile> data, String FILE_PATH) {
//        
//    }
//    
//}
=======

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
>>>>>>> fd4622ed107b161071b05ff5c7b994e33a3ac1c2
