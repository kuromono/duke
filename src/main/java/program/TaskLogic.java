package program;

import task.*;

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
            System.out.println(" There is nothing in the list!");
            System.out.println(LINE);
        }
    }

    public static void done(ArrayList<Task> task_list, int index) throws DukeException {
        if (index < 0 || index > task_list.size()) // Set task list boundary
            throw new DukeException("Value is out of bounds.");

        task_list.get(index - 1).setDone();
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + task_list.get(index - 1).toString());
        System.out.println(LINE);
    }

    public static void add_task(ArrayList<Task> task_list, Task t)
    {
        task_list.add(t);
        System.out.println(" Now you have " + task_list.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public static Task make_task(String type, String desc, String date)
    {
        Task t;

        if (type.equals("event")) {
            t = new Event(desc, date);
        } else if (type.equals("deadline")) {
            t = new Deadline(desc, date);
        } else {
            t = new ToDo(desc);
        }

        System.out.println(LINE);
        System.out.println(" Got it. I've added this task: ");
        System.out.println("   " + t.toString());

        return t;
    }

    public static void error_task() {
        System.out.println(LINE);
        System.out.println(" The system currently only supports the following formats:");
        System.out.println("   todo <description>");
        System.out.println("   deadline <description> /by <date>");
        System.out.println("   event <description> /at <date>");
        System.out.println(LINE);
    }
}
