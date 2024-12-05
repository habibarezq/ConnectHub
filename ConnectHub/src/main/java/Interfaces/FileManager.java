
package Interfaces;

import java.util.*;

public interface FileManager<T> {

    // Methods to be implemented to save and read from JSON Files
    public void readFromFile(String FILE_PATH);
    public void saveTOFile(ArrayList<T> data , String FILE_PATH);

}
