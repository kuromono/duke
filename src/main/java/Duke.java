import task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        // Print intro text
        printIntro();

        /*
        ArrayList<task.Task> task_list = new ArrayList<task.Task>();

        // INPUT LOOP
        Scanner read_line = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye"))
        {
            // read input & tokenize
            input = read_line.nextLine();
            String[] tokenized_input = input.split(" ");

            if (input.equals("list")) // Lists Everything
            {
                if (task_list.size() != 0) // Set task list boundary
                {
                    for (int i = 0; i < task_list.size(); i += 1)
                    {
                        task.Task t = task_list.get(i);
                        System.out.println((i + 1) + "." + t.toString());
                    }
                }
                else
                {
                    System.out.println(line + "\nThere is nothing in the list!\n" + line);
                }
            }
            else if (tokenized_input[0].equals("done")) // Mark Tasks as Done
            {
                int input_num = Integer.parseInt(tokenized_input[1]);
                if (input_num > 0 && input_num <= task_list.size()) // Set task list boundary
                {
                    task_list.get(input_num - 1).isDone = true;
                }
                System.out.println(line + "\nNice! I've marked this task as done:");
                System.out.println("  " + task_list.get(input_num - 1).getStatusIcon() + " " + task_list.get(input_num - 1).description);
                System.out.println(line);
            }
            else // Add new tasks
            {
                if (input.equals("todo"))
                {
                    task.Task t = new task.ToDo(input);
                    task_list.add(t);
                }
                else if (input.equals("deadline"))
                {
                    task.Task t = new task.Deadline(input, "placeholder");
                    task_list.add(t);
                }
                else if (input.equals("event"))
                {
                    task.Task t = new task.Event(input, "placeholder");
                    task_list.add(t);
                }
                else
                {
                    task.Task t = new task.Task(input);
                    task_list.add(t);
                    System.out.println(line + "\nadded: " + input + "\n" + line);
                }
            }

        }

         */

        // main logic
        input_parser();

        // Print outro text & exits
        exitDuke();
    }

    public static void input_parser()
    {
        ArrayList<Task> task_list = new ArrayList<Task>();

        // INPUT LOOP
        Scanner read_line = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye"))
        {
            // read input & tokenize
            input = read_line.nextLine();
            String[] tokenized_input = input.split(" ");

            if (input.equals("list")) // Lists Everything
            {
                TaskLogic.list(task_list);
            }
            else if (tokenized_input[0].equals("done")) // Mark Tasks as Done
            {
                TaskLogic.done(task_list, Integer.parseInt(tokenized_input[1]));
            }
            else // Add new tasks
            {
                if (input.equals("todo"))
                {
                    Task t = new ToDo(input);
                    task_list.add(t);
                }
                else if (input.equals("deadline"))
                {
                    Task t = new Deadline(input, "placeholder");
                    task_list.add(t);
                }
                else if (input.equals("event"))
                {
                    Task t = new Event(input, "placeholder");
                    task_list.add(t);
                }
                else
                {
                    task_list.add(TaskLogic.add_task(input));
                }
            }

        }

        // close scanner
        read_line.close();
    }

    public static void printIntro()
    {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(LINE);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(LINE);
    }

    public static void exitDuke()
    {
        // OUTRO TEXT
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);

        System.exit(0);
    }
}
