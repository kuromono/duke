package program;

import task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskLogic {
    public static final String LINE = "____________________________________________________________";

    public static void list(ArrayList<Task> task_list) {
        if (task_list.size() != 0) { // Set task list boundary
            for (int i = 0; i < task_list.size(); i += 1) {
                Task t = task_list.get(i);
                System.out.println(" " + (i + 1) + "." + t.toString());
            }
        } else {
            System.out.println(LINE);
            System.out.println("There is nothing in the list!");
            System.out.println(LINE);
        }
    }

    public static void done(ArrayList<Task> task_list, int index) {
        if (task_list.size() == 0 || index < 0 || index > task_list.size()) { // Set task list boundary
            System.err.println("Value is out of bounds.");
        } else {
            task_list.get(index - 1).setDone();
            System.out.println(LINE);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task_list.get(index - 1).toString());
            System.out.println(LINE);
        }
    }

    public static void add_task(ArrayList<Task> task_list, Task t)
    {
        task_list.add(t);
        System.out.println("Now you have " + task_list.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public static Task make_task(String type, String desc, String date)
    {
        Task t;

        if (type.equals("event")) {
            t = new Event(desc, DateMaker(date));
        } else if (type.equals("deadline")) {
            t = new Deadline(desc, DateMaker(date));
        } else {
            t = new ToDo(desc);
        }

        System.out.println(LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println("   " + t.toString());

        return t;
    }

    public static ArrayList<Task> reset(ArrayList<Task> task_list) {
        System.out.println(LINE);
        System.out.println("Everything has been cleared!");
        System.out.println(LINE);
        return new ArrayList<Task>();
    }

    public static boolean DateValidator(String date) {
        try {
            DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime.parse(date, date_format);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static LocalDateTime DateMaker (String date) {
        DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(date, date_format);
    }
}
