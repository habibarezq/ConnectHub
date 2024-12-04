
package Interfaces;

import java.util.*;

public interface FileManager<T> {

    // methods to be implemented to save and reaf from JSON Files
    public ArrayList<T> readFromFile(String str);
    public void saveTOFile(ArrayList<T> data , String FILE_PATH);

}
