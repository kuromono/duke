package program;

import task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileLogic {
    public static Task LineParser(String line) {
        String[] tokenized_line = line.split(" | ");

        Task t;

        int done = Integer.parseInt(tokenized_line[1]);
        String desc = tokenized_line[2];
        String date = tokenized_line[3];

        if (tokenized_line[0].equals("E")) { //Event
            t = TaskLogic.make_task("event", desc, date);
        } else if (tokenized_line[0].equals("D")) { //Deadline
            t = TaskLogic.make_task("deadline", desc, date);
        } else  { //ToDo
            t = TaskLogic.make_task("todo", desc, "");
        }

        if (done != 0)
            t.setDone();

        return t;
    }

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
           } catch (IOException e) {
               System.err.println("File is empty/corrupted, starting from fresh...");
           }

       } else {
           // Make directory & file if not exist
           file.getParentFile().mkdirs();
           FileWriter write_file = new FileWriter(path);

           System.out.println("File not found. Making new file at : " + path);
       }

       return task_list;
   }

   public static void update_file(File file, ArrayList<Task> task_list) throws IOException {
       FileOutputStream file_output = new FileOutputStream(file);
       ObjectOutputStream object_output = new ObjectOutputStream(file_output);

        object_output.writeObject(task_list);

        file_output.close();
        object_output.close();
   }
}
