package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {

    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";

    private static final int PASSWORD_LENGTH = 7;
    private static final int MIN_NUM_UPPER_CASE = 3;
    private static final int MIN_NUM_DIGITS = 2;

    private static final String FILE_PATH = "employeeCredentials.txt";


    private static Character getRandomCharacter(String domainSpace){
        SecureRandom random = new SecureRandom();
        int index = random.nextInt(domainSpace.length());
        return domainSpace.charAt(index);
    }

    public static String generatePassword() {
        List<Character> password_chars = new ArrayList<>();

        for (int i = 0; i < MIN_NUM_UPPER_CASE; i++){
            password_chars.add(getRandomCharacter(UPPER_CASE));
        }

        for (int i = 0; i < MIN_NUM_DIGITS; i++){
            password_chars.add(getRandomCharacter(DIGITS));
        }

        int NUM_REMAINING = PASSWORD_LENGTH - MIN_NUM_DIGITS - MIN_NUM_UPPER_CASE;

        for (int i = 0; i < NUM_REMAINING; i++){
            password_chars.add(getRandomCharacter(LOWER_CASE + UPPER_CASE + DIGITS));
        }

        Collections.shuffle(password_chars);

        StringBuilder password = new StringBuilder();

        for (Character ch : password_chars) {
            password.append(ch);
        }
        return password.toString();
    }

    public static void savePasswordTxt(String name,String emailAddress, List<String> chosenRoles) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        if (isFileExists(FILE_PATH)) {
            bufferedWriter.newLine();
        }

        String password = generatePassword();
        addEmployeeAsUser(name,emailAddress,chosenRoles,password);

        bufferedWriter.write(name + ", " + emailAddress + ", " + password);
        bufferedWriter.close();
    }

    public static void cleanTxtFile() {
        File file = new File(FILE_PATH);
        if (isFileExists(FILE_PATH)) {
            file.delete();
        }
    }

    private static void addEmployeeAsUser(String name,String emailAddress, List<String> chosenRoles, String password) {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        String[] roles = new String[5];
        int i = 0;

        for (String role : chosenRoles) {
            switch (role) {

                case "Agent":
                    roles[i] = "Agent";
                    i++;
                    break;

                case "Administrator":
                    roles[i] = "Administrator";
                    i++;
                    break;

                case "Store Manager":
                    roles[i] = "Store Manager";
                    i++;
                    break;

                case "Store Network Manager":
                    roles[i] = "Store Network Manager";
                    i++;
                    break;
            }
        }

        authenticationRepository.addUserWithRoles(name,emailAddress,password,roles);
    }

    private static boolean isFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
