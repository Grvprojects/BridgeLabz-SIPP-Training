import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Docuvault {

    private static final String DATA_DIR = "DocuVaultData";
    private static final String TRASH_DIR = "DocuVaultData/Trash";
    private static final String LOG_FILE = DATA_DIR + "/activity.log";

    public static void main(String[] args) {
        try {
            createDirectories();

            Scanner sc = new Scanner(System.in);
            String username = "Sparsh"; // Simulating a logged-in user

            while (true) {
                System.out.println("\n===== DocuVault Menu =====");
                System.out.println("1. Upload Document");
                System.out.println("2. Read Document");
                System.out.println("3. Append to Document");
                System.out.println("4. Search Keyword in Documents");
                System.out.println("5. Delete Document");
                System.out.println("6. Show Total Storage Used");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter file name to upload: ");
                        String uploadName = sc.nextLine();
                        uploadDocument(username, uploadName);
                        break;
                    case 2:
                        System.out.print("Enter file name to read: ");
                        String readName = sc.nextLine();
                        readDocument(readName);
                        break;
                    case 3:
                        System.out.print("Enter file name to append to: ");
                        String appendName = sc.nextLine();
                        System.out.print("Enter content to append: ");
                        String content = sc.nextLine();
                        appendToDocument(username, appendName, content);
                        break;
                    case 4:
                        System.out.print("Enter keyword to search: ");
                        String keyword = sc.nextLine();
                        searchKeyword(keyword);
                        break;
                    case 5:
                        System.out.print("Enter file name to delete: ");
                        String deleteName = sc.nextLine();
                        deleteDocument(username, deleteName);
                        break;
                    case 6:
                        showTotalStorage();
                        break;
                    case 7:
                        System.out.println("Exiting... Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create data and trash directories if not exist
    private static void createDirectories() throws IOException {
        Files.createDirectories(Paths.get(DATA_DIR));
        Files.createDirectories(Paths.get(TRASH_DIR));
    }

    // Upload document
    private static void uploadDocument(String username, String fileName) {
        File file = new File(DATA_DIR, fileName);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("Sample content for " + fileName);
            logActivity(username, fileName, "Uploaded");
            System.out.println("File uploaded successfully!");
        } catch (IOException e) {
            System.out.println("Error uploading file: " + e.getMessage());
        }
    }

    // Read file content
    private static void readDocument(String fileName) {
        File file = new File(DATA_DIR, fileName);
        if (!file.exists()) {
            System.out.println("File not found!");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n----- File Content -----");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("------------------------");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Append content to file
    private static void appendToDocument(String username, String fileName, String content) {
        File file = new File(DATA_DIR, fileName);
        if (!file.exists()) {
            System.out.println("File not found!");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write("\n" + content);
            logActivity(username, fileName, "Appended");
            System.out.println("Content appended successfully!");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    // Search keyword in all stored files
    private static void searchKeyword(String keyword) {
        File folder = new File(DATA_DIR);
        File[] files = folder.listFiles((dir, name) -> !name.equals("activity.log") && !name.equals("Trash"));

        if (files == null || files.length == 0) {
            System.out.println("No files to search.");
            return;
        }

        System.out.println("\nFiles containing '" + keyword + "':");
        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                boolean found = false;
                while ((line = br.readLine()) != null) {
                    if (line.contains(keyword)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    System.out.println(file.getName());
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }

    // Secure delete (move to Trash)
    private static void deleteDocument(String username, String fileName) {
        File file = new File(DATA_DIR, fileName);
        if (!file.exists()) {
            System.out.println("File not found!");
            return;
        }
        try {
            Files.move(file.toPath(), Paths.get(TRASH_DIR, fileName), StandardCopyOption.REPLACE_EXISTING);
            logActivity(username, fileName, "Moved to Trash");
            System.out.println("File moved to Trash successfully!");
        } catch (IOException e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
    }

    // Show total storage used
    private static void showTotalStorage() {
        File folder = new File(DATA_DIR);
        long totalSize = 0;
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (!file.getName().equals("activity.log") && !file.isDirectory()) {
                totalSize += file.length();
            }
        }
        System.out.println("Total Storage Used: " + (totalSize / 1024.0) + " KB");
    }

    // Log user activity
    private static void logActivity(String username, String fileName, String action) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            bw.write(username + " | " + fileName + " | " + timeStamp + " | " + action);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to log: " + e.getMessage());
        }
    }
}
