import task.*;

import java.util.ArrayList;

public class TaskLogic {
    public static final String LINE = "____________________________________________________________";

    public static void list(ArrayList<Task> task_list) {
        if (task_list.size() != 0) // Set task list boundary
        {
            for (int i = 0; i < task_list.size(); i += 1) {
                Task t = task_list.get(i);
                System.out.println((i + 1) + "." + t.toString());
            }
        } else {
            System.out.println(LINE + "\nThere is nothing in the list!\n" + LINE);
        }
    }

    public static void done(ArrayList<Task> task_list, int index) {
        if (index > 0 && index <= task_list.size()) // Set task list boundary
        {
            task_list.get(index - 1).setDone();
        }
        System.out.println(LINE + "\nNice! I've marked this task as done:");
        System.out.println("  " + task_list.get(index - 1).toString());
        System.out.println(LINE);
    }

    public static Task add_task(String desc)
    {
        Task t = new Task(desc);
        System.out.println(LINE + "\nadded: " + desc + "\n" + LINE);
        return t;
    }

    public static void add_todo(String desc)
    {

    }

    public static void add_deadline(String desc)
    {

    }

    public static void add_event(String desc)
    {

    }
}
