package pt.ipp.isep.dei.esoft.project.serialization;

import java.io.*;

public class ReadAndWriteBinaryFile {

    /**
     * Writes a list of objects into a binary file
     * @param fileName
     * @param listObj
     */
    public static void writeBinaryFile(String fileName, Object listObj) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutputStream);

            objOutput.writeObject(listObj);
            objOutput.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Reads the binary file
     * @param fileName
     * @return object
     */
    public static Object readBinaryFile(String fileName) {
        Object obj = null;

        try {
            File file = new File(fileName);

            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objInput = new ObjectInputStream(fileInputStream);

                obj = objInput.readObject();
                objInput.close();
                fileInputStream.close();

            }
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
        return (obj);
    }
}
