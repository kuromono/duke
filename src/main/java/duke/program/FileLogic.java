package duke.program;

import duke.task.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileLogic {

    /**
     * Reads a file from path that contains values to create a new Task ArrayList.
     * @param file File object to be read from.
     * @param path Location where the file is located.
     * @return A new Task ArrayList containing the values read from the specified file.
     * @throws IOException Thrown when error in input/output operation to the file.
     * @throws ClassNotFoundException Thrown when error in converting data from file to a Task ArrayList.
     */
   public static ArrayList<Task> read_file(File file, String path) throws IOException, ClassNotFoundException {

       ArrayList<Task> task_list = new ArrayList<Task>();

       if (file.exists()) {
           System.out.println("Loading file from : " + path);
           Scanner read_file = new Scanner(file);


           try {
               FileInputStream file_input = new FileInputStream(file);
               ObjectInputStream object_input = new ObjectInputStream(file_input);
               task_list = (ArrayList<Task>) object_input.readObject();

               file_input.close();
               object_input.close();
           } catch (IOException | ClassNotFoundException | NoClassDefFoundError e) {
               TaskLogic.printError("File is empty/corrupted, starting from fresh...");
           }

       } else {
           // Make directory & file if not exist
           file.getParentFile().mkdirs();
           FileWriter write_file = new FileWriter(path);
           write_file.close();

           System.out.println("File not found. Making new file at : " + path);
       }

       return task_list;
   }

    /**
     * Writes the specified file with the values of Task ArrayList.
     * @param file File to be write into.
     * @param task_list Task ArrayList containing the data to be write into the specified file.
     * @throws IOException Thrown when error in input/output operation to the file.
     */
   public static void update_file(File file, ArrayList<Task> task_list) throws IOException {
       FileOutputStream file_output = new FileOutputStream(file);
       ObjectOutputStream object_output = new ObjectOutputStream(file_output);

        object_output.writeObject(task_list);

        file_output.close();
        object_output.close();
   }

    /**
     * Resets specified file to a new/empty state.
     * @param file File to be reset.
     */
   public static void reset_file(File file) {
        try {
            FileWriter write_file = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
            TaskLogic.printError("Error in resetting the file.");
        }
   }
}
