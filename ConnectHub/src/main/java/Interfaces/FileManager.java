
package Interfaces;

import java.util.*;

public interface FileManager<T> {

    // Methods to be implemented to save and read from JSON Files
    public void readFromFile(String FILE_PATH);
    public void saveToFile(ArrayList<T> data , String FILE_PATH);

    //ADD GET INSTANCE GET USERS "SINGLETON"
}
