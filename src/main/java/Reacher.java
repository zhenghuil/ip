import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Reacher {
    public static int countLines(File file) {
        int fileLines = 0;
        try(BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(file));) {
            while (bufferedReader.readLine() != null) {
                fileLines++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return fileLines;
    }
    public static void delete(int line, File file) throws IOException {
        File temp = new File("./memTemp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
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
        if (!file.delete()) {
            System.out.println("Could not delete file");
            return;
        }

        //Rename the new file to the filename the original file had.
        if (!temp.renameTo(file))
            System.out.println("Could not rename file");

    }
    public static void changeDone(int line, File file) throws IOException {
        File temp = new File("./memTemp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String currentLine;
        int count = 0;
        while ((currentLine = reader.readLine()) != null) {
            count++;
            if (count == line) {
                StringBuilder task = new StringBuilder(currentLine);
                task.setCharAt(4, 'X');
                currentLine = String.valueOf(task);
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        if (!file.delete()) {
            System.out.println("Could not delete file");
            return;
        }

        //Rename the new file to the filename the original file had.
        if (!temp.renameTo(file))
            System.out.println("Could not rename file");

    }
    public static void changeUndone(int line, File file) throws IOException {
        File temp = new File("./memTemp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String currentLine;
        int count = 0;
        while ((currentLine = reader.readLine()) != null) {
            count++;
            if (count == line) {
                StringBuilder task = new StringBuilder(currentLine);
                task.setCharAt(4, ' ');
                currentLine = String.valueOf(task);
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        if (!file.delete()) {
            System.out.println("Could not delete file");
            return;
        }

        //Rename the new file to the filename the original file had.
        if (!temp.renameTo(file))
            System.out.println("Could not rename file");

    }
    public static void main(String[] args) throws FileNotFoundException {
        Mem m = new Mem();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!\n" +
                "I'm Reacher.\n" +
                "Give me tasks.\n" +
                "Functions are edit, list, delete and bye");
        while (true) {
            File mem = new File("./memory.txt");
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    throw new ReacherException("pls type a task name.");
                }
                if (input.equalsIgnoreCase("bye")) { //check for end request
                    System.out.println("bye");
                    break;
                } else if (input.equalsIgnoreCase("list")) {// check for list request
                    System.out.println("Tasks:");
                    int c = 1;
                    Scanner s = new Scanner(mem); // create a Scanner using the File as the source
                    while (s.hasNextLine()) {
                        System.out.println(c + s.nextLine());
                        c++;
                    }
                    s.close();
                } else if (input.equalsIgnoreCase("edit")) {
                    System.out.println("Which task number would u like to edit?");
                    try {
                        int num = scanner.nextInt();
                        scanner.nextLine();
                        if (num > countLines(mem) || num < 1) {
                            throw new ReacherException("No such task number");
                        }
                        System.out.println("Mark Done or Undone or Delete?");
                        String change = scanner.nextLine();
                        if (change.equalsIgnoreCase("done")) {
                            changeDone(num, mem);
                            System.out.println("Task " + num + " marked done");
                        } else if (change.equalsIgnoreCase("undone")) {
                            changeUndone(num, mem);
                            System.out.println("Task " + num + " marked Undone");
                        } else if (change.equalsIgnoreCase("delete")) {
                            delete(num, mem);
                            System.out.println("Task " + num + " deleted");
                        }else {
                            throw new ReacherException("u did not write done, undone or delete.");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("What type of task is this?(Deadline, Event, Todo)");
                    Task t = new Todos("");
                    try {
                        String type = scanner.nextLine();
                        if (type.equalsIgnoreCase("todo")) {
                            t = new Todos(input);
                        } else if (type.equalsIgnoreCase("deadline")) {
                            System.out.println("When is the deadline?");
                            String deadline = scanner.nextLine();
                            t = new Deadline(input, deadline);
                        } else if (type.equalsIgnoreCase("event")) {
                            System.out.println("When is the start?");
                            String start = scanner.nextLine();
                            System.out.println("When is the end?");
                            String end = scanner.nextLine();
                            t = new Events(input, start, end);
                        } else {
                            throw new ReacherException("That is not a type of task.");
                        }
                        m.add(t.toString());
                    } catch (ReacherException e) {
                        System.out.println(e.getMessage());
                    }

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        scanner.close();
    }
}
