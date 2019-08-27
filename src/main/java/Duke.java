import program.*;
import task.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";

    public static void main(String[] args) throws DukeException, IOException {
        // Print intro text
        printIntro();

        // New file
        String path = new File("data/duke.txt").getAbsolutePath();
        File file = new File(path);

        // Read file
        ArrayList<Task> task_list = FileLogic.ReadFile(file, path);

        // main logic
        input_parser(task_list);
    }

    public static void input_parser(ArrayList<Task> task_list) throws DukeException {
        System.out.println(LINE);

        // INPUT LOOP
        Scanner read_line = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            try {
                // read input & tokenize
                input = read_line.nextLine();
                String[] tokenized_input = input.strip().split(" ");

                if (input.equals("list")) {
                    // List everything
                    TaskLogic.list(task_list);
                } else if (tokenized_input[0].equals("done")) {
                    // Marks task as Done
                    if (tokenized_input.length == 1)
                        throw new DukeException ("The value of <done> cannot be empty.");

                    TaskLogic.done(task_list, Integer.parseInt(tokenized_input[1]));
                } else {
                    // Adds new Tasks
                    String desc;
                    if (tokenized_input[0].equals("todo")) {
                        if (input.strip().length() < 5)
                            throw new DukeException ("The description of a <todo> cannot be empty.");

                        TaskLogic.add_task(task_list, TaskLogic.make_task("todo", input.substring(5), ""));
                    } else if (tokenized_input[0].equals("deadline")) {
                        if (input.strip().length() < 9)
                            throw new DukeException ("The description of a <deadline> cannot be empty.");

                        tokenized_input = input.substring(9).split(" /by ");
                        TaskLogic.add_task(task_list, TaskLogic.make_task("deadline", tokenized_input[0], tokenized_input[1]));
                    } else if (tokenized_input[0].equals("event")) {
                        if (input.strip().length() < 6)
                            throw new DukeException ("The description of a <event> cannot be empty.");

                        tokenized_input = input.substring(6).split(" /at ");
                        TaskLogic.add_task(task_list, TaskLogic.make_task("event", tokenized_input[0], tokenized_input[1]));
                    } else if (input.equals("bye")) {
                        // Close scanner and exit programs
                        read_line.close();
                        exitDuke();
                    } else {
                        throw new DukeException("\n"
                                + "The system currently only supports the following formats:\n"
                                + "  todo <description>\n"
                                + "  deadline <description> /by <date>\n"
                                + "  event <description> /at <date>");
                    }
                }
            } catch (DukeException e) {
                System.err.println(e);
            }

        }
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
