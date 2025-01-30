import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean doneInput = false;
        String ID = "";
        String fName = "";
        String lName = "";
        String title = "";
        String record = "";
        int yob = 0;

        // Use the current working directory
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), "src", "data.txt");

        do {
            ID = SafeInput.getRegExString(in, "Enter your ID", "^00000\\d$");
            fName = SafeInput.getNonZeroLenString(in, "Enter your First Name");
            lName = SafeInput.getNonZeroLenString(in, "Enter your Last Name");
            title = SafeInput.getNonZeroLenString(in, "Enter your title");
            yob = SafeInput.getRangedInt(in, "Enter the year of your birth", 1000, 9999);

            record = ID + ", " + fName + ", " + lName + ", " + title + ", " + yob;
            System.out.println(record);

            recs.add(record);

            doneInput = SafeInput.getYNConfirm(in, "Do you have another record");
        } while (doneInput);

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : recs) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
