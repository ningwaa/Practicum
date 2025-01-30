import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
        ArrayList<String> recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean doneInput = false;
        String id = "";
        String name = "";
        String description = "";
        String products = "";
        double cost = 0;

        // Use the current working directory dynamically
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), "src", "ProductData.txt");

        do {
            id = SafeInput.getRegExString(in, "Enter product id", "^00000\\d$");
            name = SafeInput.getNonZeroLenString(in, "Enter product name");
            description = SafeInput.getNonZeroLenString(in, "Enter product description");
            cost = SafeInput.getDouble(in, "What is the cost of the product?");

            products = id + ", " + name + ", " + description + ", " + cost;
            System.out.println(products);

            recs.add(products);

            doneInput = SafeInput.getYNConfirm(in, "Do you want to add another product?");
        } while (doneInput);

        try {
            // Writing to file using BufferedWriter
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : recs) {
                writer.write(rec);
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}