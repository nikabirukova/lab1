package externalizable.FileOperations;

import java.io.*;

public class FileExternalizationWriter {

    public static void objectToFile(Externalizable obj, String filePath) {
        try (FileOutputStream file = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(file)) {
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
