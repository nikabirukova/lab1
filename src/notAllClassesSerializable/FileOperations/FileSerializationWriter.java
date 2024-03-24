package notAllClassesSerializable.FileOperations;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileSerializationWriter {

    public static void objectToFile(Serializable obj, String filePath) {
        try (FileOutputStream file = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(file)) {
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
