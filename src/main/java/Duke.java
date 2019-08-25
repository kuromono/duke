import task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        // Print intro text
        printIntro();

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
        while (!input.equals("bye")) {
            // read input & tokenize
            input = read_line.nextLine();
            String[] tokenized_input = input.strip().split(" ");

            if (input.equals("list")) {
                // List everything
                TaskLogic.list(task_list);
            } else if (tokenized_input[0].equals("done")) {
                // Marks task as Done
                TaskLogic.done(task_list, Integer.parseInt(tokenized_input[1]));
            } else {
                // Adds new Tasks
                if (tokenized_input[0].equals("todo")) {
                    TaskLogic.add_task(task_list, TaskLogic.make_task("todo", input.substring(5), ""));
                } else if (tokenized_input[0].equals("deadline")) {
                    tokenized_input = input.substring(9).split(" /by ");
                    TaskLogic.add_task(task_list, TaskLogic.make_task("deadline", tokenized_input[0], tokenized_input[1]));
                } else if (tokenized_input[0].equals("event")) {
                    tokenized_input = input.substring(6).split(" /at ");
                    TaskLogic.add_task(task_list, TaskLogic.make_task("event", tokenized_input[0], tokenized_input[1]));
                } else {
                    TaskLogic.error_task();
                }
            }

        }

        // close scanner
        read_line.close();
    }

    public static void printIntro() {
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

    public static void exitDuke() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);

        System.exit(0);
    }
}
