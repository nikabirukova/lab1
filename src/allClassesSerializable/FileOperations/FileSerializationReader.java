package allClassesSerializable.FileOperations;

import java.io.*;

public class FileSerializationReader {

    public static Object objectFromFile(String filePath){
        Object res = null;
        try (FileInputStream file = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(file)) {

            res = ois.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return res;
    }

}
