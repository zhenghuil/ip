import java.io.*;
import java.util.Scanner;

public class Mem {
    protected File mem;
    public Mem() {
        try {
            File mem = new File("./memory.txt");
            if (mem.createNewFile()) {
                System.out.println("File created: " + mem.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void add(String t) {
        try {
            FileWriter fw = new FileWriter("./memory.txt", true);
            fw.write(t + "\n");
            fw.close();
            System.out.println("I've added " + t);
        } catch (IOException e) {
            System.out.println("error writing to memory.");
        }
    }

    public void print() throws FileNotFoundException {
        int c = 1;
        Scanner s = new Scanner(mem); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            System.out.println(s.nextLine());
            c++;
        }
        s.close();
    }

    public void delete(int line) throws IOException {
        File temp = new File("./memTemp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(mem));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String currentLine;
        int count = 0;
        while ((currentLine = reader.readLine()) != null) {
            count++;
            if (count == line) {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        mem.delete();
        temp.renameTo(mem);
    }
}
