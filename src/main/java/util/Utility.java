package util;

import com.github.javafaker.Faker;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Utility {

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    static Random random = new Random();
    static LocalDate startDate = generateStartDate(random);
    public static String formattedDate = startDate.format(dateFormatter);



    public static LocalDate generateStartDate(Random random) {
        // Set the range of possible start dates
        LocalDate minDate = LocalDate.of(1989, 1, 1);
        LocalDate maxDate = LocalDate.now();
        // Generate a random number of days between the minimum and maximum dates
        int randomDays = random.nextInt((int) minDate.until(maxDate).getDays());
        // Add the random number of days to the minimum date
        return minDate.plusDays(randomDays);
    }

    public static LocalDate generateEndDate(Random random) {
        // Set the range of possible start dates
        LocalDate minDate = LocalDate.of(2000, 1, 1);
        LocalDate maxDate = LocalDate.now();
        // Generate a random number of days between the minimum and maximum dates
        int randomDays = random.nextInt((int) minDate.until(maxDate).getDays());
        // Add the random number of days to the minimum date
        return minDate.plusDays(randomDays);
    }

    public static LocalDate generateEndDate() {
        return LocalDate.of(2023, 1, 1);
    }

    public static String generateRandomEmailAddress() {
        int usernameLength = 8;
        Random random = new Random();

        StringBuilder email = new StringBuilder();

        // Generate random username
        for (int i = 0; i < usernameLength; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            email.append(randomChar);
        }

        // Append the domain
        email.append("@");
        email.append(generateRandomDomain());

        return email.toString();
    }

    public static String generateRandomSecondEmailAddress() {
        int usernameLength = 8;
        Random random = new Random();

        StringBuilder email = new StringBuilder();

        // Generate random username
        for (int i = 0; i < usernameLength; i++) {
            char randomChar = (char) (random.nextInt(26) + 'b');
            email.append(randomChar);
        }

        // Append the domain
        email.append("@");
        email.append(generateRandomDomain());

        return email.toString();
    }

    private static String generateRandomDomain() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com"};
        Random random = new Random();
        int randomIndex = random.nextInt(domains.length);
        return domains[randomIndex];
    }

    public static String generateRandomFirstName() {
        // Predefined list (replace or extend as needed)
        ArrayList<String> firstNames = new ArrayList<String>();
        firstNames.add("Alice");
        firstNames.add("Zeinab");
        firstNames.add("Amira");
        firstNames.add("Esra");
        firstNames.add("Ahmed");
        firstNames.add("Mohamed");
        // Generate random index
        Random random = new Random();
        int firstNameIndex = random.nextInt(firstNames.size());
        // Get random first name from list
        return firstNames.get(firstNameIndex);
    }

    public static String getRandomName() {
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace", "Henry", "Isabella", "Jack", "Soka", "Boka"};
        Random random = new Random();
        int index = random.nextInt(firstNames.length);
        return firstNames[index];

    }

    public static String getRandomFirstName() {
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace", "Henry", "Isabella", "Jack"};
        Random random = new Random();
        int index = random.nextInt(firstNames.length);
        return firstNames[index];

    }

    public static String generateRandomLastName() {
        String[] lastNames = {"Alice", "Bob", "Charlie", "David", "Emily", "Frank", "Grace", "Henry", "Isabella", "Jack"};
        Random random = new Random();
        int index = random.nextInt(lastNames.length);
        return lastNames[index];

    }

    //generate complex password with 8 characters
    public static String generatePassword() {
        final String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lowerLetters = capitalLetters.toLowerCase();
        final String specialCharacters = "!@#$%^&*()-+";
        final String allCharacters = capitalLetters + lowerLetters + specialCharacters;
        final int passwordLength = 8;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        int charCategories = 3; // Consider at least one character from each category

        // Ensure at least one character from each category
        for (int i = 0; i < charCategories; i++) {
            int charGroupIndex = random.nextInt(charCategories);
            String charGroup;
            switch (charGroupIndex) {
                case 0:
                    charGroup = capitalLetters;
                    break;
                case 1:
                    charGroup = lowerLetters;
                    break;
                case 2:
                    charGroup = specialCharacters;
                    break;
                default:
                    throw new IllegalStateException("Unexpected character group index");
            }
            int charIndex = random.nextInt(charGroup.length());
            password.append(charGroup.charAt(charIndex));
        }

        // Fill remaining characters with any character
        for (int i = password.length(); i < passwordLength; i++) {
            int charIndex = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(charIndex));
        }

        // Shuffle the characters for better randomness
        for (int i = 0; i < password.length(); i++) {
            int targetIndex = random.nextInt(password.length());
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(targetIndex));
            password.setCharAt(targetIndex, temp);
        }

        return password.toString();
    }

    // TODO: start html report
    public static void startHtmlReport(String reportDirName, String reportFileName) throws IOException {
        String path = System.getProperty("user.dir") + "/testReport.html";
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd " + reportDirName + " && " + reportFileName);
        builder.redirectErrorStream(true);
        Process p = builder.start();
    }

    // generate random email with gmail domain only
    public static String generateRandomGmailEmail() {
        int usernameLength = 8; // Adjust username length as needed
        StringBuilder usernameBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < usernameLength; i++) {
            int charType = random.nextInt(3);
            if (charType == 0) {
                usernameBuilder.append((char) (random.nextInt(26) + 'a')); // Lowercase letter
            } else if (charType == 1) {
                usernameBuilder.append((char) (random.nextInt(26) + 'A')); // Uppercase letter
            } else {
                usernameBuilder.append((char) (random.nextInt(10) + '0')); // Number
            }
        }

        String username = usernameBuilder.toString();
        String domain = "gmail.com"; // Guaranteed Gmail domain
        return username + "@" + domain;
    }

    //generate uniqe number
    public static List<Integer> generateUniqueRandomNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    public static int getRandomNumberBetween1andN(int randomRange) {
        Random random = new Random();
        // Generate a random integer between 0 (inclusive) and 30 (exclusive)
        int randomNumber = random.nextInt(randomRange);
        // Add 1 to make the range inclusive of 1 and 30
        return randomNumber + 1;
    }

    //generate random integer
    public static Integer removeRandomInteger(List<Integer> list) {
        // Check if the list is empty
        if (list.isEmpty()) {
            throw new IllegalArgumentException("The list is empty.");
        }

        // Generate a random index within the bounds of the list
        Random random = new Random();
        int index = random.nextInt(list.size());

        // Remove the element at the random index
        return list.remove(index);
    }


    // parse float values
    public static float parsePriceFromString(String priceString) {
        if (priceString == null || priceString.isEmpty()) {
            throw new IllegalArgumentException("Price string cannot be null or empty");
        }

        // Remove leading and trailing whitespaces (optional)
        priceString = priceString.trim();

        // Check if the string starts with a dollar sign ($)
        if (!priceString.startsWith("$")) {
            throw new IllegalArgumentException("Price string must start with a dollar sign ($)");
        }

        // Extract the number part (everything after the dollar sign)
        String numberString = priceString.substring(1);

        // Parse the number string to a float
        try {
            return Float.parseFloat(numberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format. Please provide a valid number after the dollar sign ($)", e);
        }
    }

    // read from json
    public static String getSingleJsonData(String jsonFilePath, String jsonField) throws IOException, ParseException, IOException {
        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(jsonFilePath);
        Object obj = jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject.get(jsonField).toString();
    }

    // read from excel
    public static String getExcelData(int RowNum, int ColNum, String SheetName) {
        XSSFWorkbook workBook;
        XSSFSheet sheet;
        String projectPath = System.getProperty("user.dir");
        String cellData = null;
        try {
            workBook = new XSSFWorkbook(projectPath + "/src/test/resources/test_data/data.xlsx");
            sheet = workBook.getSheet(SheetName);
            cellData = sheet.getRow(RowNum).getCell(ColNum).getStringCellValue();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return cellData;
    }

    //read from json multiple items
    public static String[] readJson(String jsonFilePath, String jsonFieldArray, String field) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(jsonFilePath);
        Object obj = jsonParser.parse(fileReader);

        JSONObject jsonObject = (JSONObject) obj;
        JSONArray array = (JSONArray) jsonObject.get(jsonFieldArray);

        String[] arr = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject users = (JSONObject) array.get(i);
            String fieldData = (String) users.get(field);

            arr[i] = fieldData;
        }
        return arr;

    }

    public static String generateRandomBirthDate(Faker faker, int minAge, int maxAge) {
        LocalDate currentDate = LocalDate.now();
        int age = faker.number().numberBetween(minAge, maxAge);
        LocalDate birthDate = currentDate.minusYears(age);
        int dayOfYear = faker.number().numberBetween(1, birthDate.lengthOfYear());
        birthDate = birthDate.withDayOfYear(dayOfYear);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return birthDate.format(formatter);
    }


    // TODO: delete screenshots
    public static void deleteFilesInFolder(String folderPath) {
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        boolean isDeleted = file.delete();
                        if (isDeleted) {
                            System.out.println("Deleted: " + file.getName());
                        } else {
                            System.out.println("Failed to delete: " + file.getName());
                        }
                    }
                }
            } else {
                System.out.println("The specified folder is empty or an error occurred.");
            }
        } else {
            System.out.println("The specified path is not a folder.");
        }
    }
}
