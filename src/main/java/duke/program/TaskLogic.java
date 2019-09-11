package duke.program;

import duke.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskLogic {
    public static final String LINE = "____________________________________________________________";

    /**
     * Prints out all items in the Task ArrayList.
     * @param task_list Task ArrayList containing items to print out.
     */
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

    /**
     * Marks task of specified index in the Task ArrayList as done.
     * @param task_list Task ArrayList containing the task of the item to mark as done.
     * @param index Index of the task in the ArrayList to mark as done.
     */
    public static void done(ArrayList<Task> task_list, int index) {
        if (task_list.size() == 0 || index < 1 || index > task_list.size()) { // Set task list boundary
            printError("Value is out of bounds.");
        } else {
            task_list.get(index - 1).setDone();
            System.out.println(LINE);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task_list.get(index - 1).toString());
            System.out.println(LINE);
        }
    }

    /**
     * Deletes task of specified index in the Task ArrayList.
     * @param task_list Task ArrayList containing the task of the item to delete.
     * @param index Index of task in the ArrayList to delete.
     */
    public static void delete(ArrayList<Task> task_list, int index) {
        if (task_list.size() == 0 || index < 1 || index > task_list.size()) { // Set task list boundary
            printError("Value is out of bounds.");
        } else {
            System.out.println(LINE);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task_list.get(index - 1).toString());
            System.out.println(LINE);

            task_list.remove(index - 1);
        }

    }

    /**
     * Adds a task to the Task ArrayList.
     * @param task_list Task ArrayList for specified task to be added to.
     * @param t Task to be added to the ArrayList.
     */
    public static void add_task(ArrayList<Task> task_list, Task t)
    {
        task_list.add(t);
        System.out.println("Now you have " + task_list.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Creates a new Task object of specified type.
     * @param type Indicates the type of Task object to be created. E.g. Event, Deadline, Todo
     * @param desc Description of the Task to be created.
     * @param date Date/deadline of the Task to be created if required.
     * @return
     */
    public static Task make_task(String type, String desc, String date)
    {
        Task t;

        if (type.equals("event")) {
            t = new Event(desc, DateMaker(date));
        } else if (type.equals("deadline")) {
            t = new Deadline(desc, DateMaker(date));
        } else {
            t = new Todo(desc);
        }

        System.out.println(LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println("   " + t.toString());

        return t;
    }

    /**
     * Finds task containing the specified query in the Task ArrayList.
     * @param task_list Task ArrayList to perform search on.
     * @param query Keyword of query to be searched against the ArrayList.
     */
    public static void find_task(ArrayList<Task> task_list, String query) {
        int i = 1;
        int search_hits = 0;

        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");

        for (Task t : task_list) {
            if (t.getDescription().contains(query)) {
                System.out.println(i + "." + t.toString());
                search_hits += 1;
            }
            i += 1;
        }

        if (search_hits == 0) {
            printError("There is no tasks containing the keyword : " + query);
        } else {
            System.out.println(LINE);
        }
    }

    /**
     * Resets Task ArrayList to a new/empty state.
     * @param task_list Task ArrayList to be reset.
     * @return Task ArrayList of new/empty state.
     */
    public static ArrayList<Task> reset(ArrayList<Task> task_list) {
        System.out.println(LINE);
        System.out.println("Everything has been cleared!");
        System.out.println(LINE);
        return new ArrayList<Task>();
    }

    /**
     * Validates if input string is a valid integer.
     * @param input String containing a int to check if valid.
     * @return true or false depending if input string is valid or not.
     */
    public static boolean IntValidator(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Validates if input date string is of specified format.
     * @param date String containing a date to check if valid.
     * @return true or false depending if input string is valid or not.
     */
    public static boolean DateValidator(String date) {
        try {
            DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime.parse(date, date_format);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Converts input string to a LocalDateTime object.
     * @param date String containing a date with specified format.
     * @return LocalDateTime object created from input string.
     */
    public static LocalDateTime DateMaker (String date) {
        DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(date, date_format);
    }

    /**
     * Prints anything specified as the input string for errors.
     * It can be used to easily change how you want to format the output.
     * @param err A String containing the error.
     */
    public static void printError(String err) {
        // Able to change output type conveniently here
        //System.out.println((char)27 + "[31m" + err);
        System.out.println(err);
    }
}
