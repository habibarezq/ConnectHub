
package Interfaces;

import java.util.*;

public interface FileManager<T> {

    // Methods to be implemented to save and read from JSON Files
    public void readFromFile();
    public void saveToFile(ArrayList<T> data);

}
