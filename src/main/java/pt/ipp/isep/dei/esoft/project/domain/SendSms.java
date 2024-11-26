package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface SendSms extends TodayDate {

    public default void sendSms(String agentName,String agentPhone, String ownerEmail,Address address) {


        String todayDate = todayDate();

        // Specify the file path and name
        String filePath = "sms.txt";

        try {
            // Create a File object
            File file = new File(filePath);

            // Create the file if it doesn't exist
            boolean created = file.createNewFile();

            // Open a FileWriter in append mode to write to the file
            FileWriter writer = new FileWriter(file, true);

            if (created) {
                System.out.println("File '"+file+"' created successfully.");
            } else {
                writer.write("\n------------------------------------------------------------------------------------------------------------------------\n");
                System.out.println("File '"+file+"' already exists.");
            }

            // Write content to the file
            writer.write("This is a SMS (" + todayDate + ") !");
            writer.write("\nFrom the responsible agent '" + agentName + "' with the following phone number: " + agentPhone);
            writer.write("\nTo: " + ownerEmail);
            writer.write("\nThe listing of your property became available!");
            writer.write("\nProperty located at: " + address);

            // Close the FileWriter
            writer.close();

            System.out.println("Content written to the file '"+file+"' successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }



}
