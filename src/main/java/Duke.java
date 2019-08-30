import program.*;
import task.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        // Print intro text
        printIntro();

        // New file
        String path = new File("data/duke.txt").getAbsolutePath();
        File file = new File(path);

        try {
            // Read file
            ArrayList<Task> task_list = FileLogic.read_file(file, path);

            // main logic
            input_parser(task_list, file);
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error occurred in reading file. Exception:\n" + e);
        }
    }

    public static void input_parser(ArrayList<Task> task_list, File file) {
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
                    if (tokenized_input.length == 1) {
                        System.err.println("The index of <done> cannot be empty.");
                        continue;
                    } else if (!TaskLogic.IntValidator(tokenized_input[1])) {
                        System.err.println("The index of <done> is not a valid integer.");
                        continue;
                    }

                    TaskLogic.done(task_list, Integer.parseInt(tokenized_input[1]));
                    FileLogic.update_file(file, task_list);
                } else if (tokenized_input[0].equals("delete")) {
                    if (tokenized_input.length == 1) {
                        System.err.println("The index of <delete> cannot be empty.");
                        continue;
                    } else if (!TaskLogic.IntValidator(tokenized_input[1])) {
                        System.err.println("The index of <delete> is not a valid integer.");
                        continue;
                    }

                    TaskLogic.delete(task_list, Integer.parseInt(tokenized_input[1]));
                    FileLogic.update_file(file, task_list);
                } else if (tokenized_input[0].equals("find")) {
                    // Search task based on keyword
                    if (tokenized_input.length == 1) {
                        System.err.println("The value of <find> cannot be empty.");
                        continue;
                    }

                    TaskLogic.find_task(task_list, tokenized_input[1]);
                } else {
                    // Adds new Tasks
                    String desc;
                    if (tokenized_input[0].equals("todo")) {
                        if (input.strip().length() < 5) {
                            System.err.println("The description of a <todo> cannot be empty.");
                            continue;
                        }

                        TaskLogic.add_task(task_list, TaskLogic.make_task("todo", input.substring(5), ""));
                        FileLogic.update_file(file, task_list);
                    } else if (tokenized_input[0].equals("deadline")) {
                        if (tokenized_input.length < 3) {
                            System.err.println("The description of a <deadline> cannot be empty.");
                            System.err.println("Format: deadline <description> /by DD/MM/YYYY HHMM");
                            continue;
                        }

                        tokenized_input = input.substring(9).split(" /by ");
                        if (tokenized_input.length < 2) {
                            System.err.println("Incorrect format. Format should be: deadline <description> /by DD/MM/YYYY HHMM");
                            continue;
                        } else if (!TaskLogic.DateValidator(tokenized_input[1])) {
                            System.err.println("Date format must be DD/MM/YYYY HHMM, E.g. 02/12/2019 1800");
                            continue;
                        }

                        TaskLogic.add_task(task_list, TaskLogic.make_task("deadline", tokenized_input[0], tokenized_input[1]));
                        FileLogic.update_file(file, task_list);
                    } else if (tokenized_input[0].equals("event")) {
                        if (tokenized_input.length < 3) {
                            System.err.println("The description of a <event> cannot be empty.");
                            System.err.println("Format: event <description> /at DD/MM/YYYY HHMM");
                            continue;
                        }

                        tokenized_input = input.substring(6).split(" /at ");
                        if (tokenized_input.length < 2) {
                            System.err.println("Incorrect format. Format should be: event <description> /at DD/MM/YYYY HHMM");
                            continue;
                        } else if (!TaskLogic.DateValidator(tokenized_input[1])) {
                            System.err.println("Date format must be DD/MM/YYYY HHMM, E.g. 02/12/2019 1800");
                            continue;
                        }

                        TaskLogic.add_task(task_list, TaskLogic.make_task("event", tokenized_input[0], tokenized_input[1]));
                        FileLogic.update_file(file, task_list);
                    } else if (input.equals("reset")) { // Reset the list & delete file - for debug use
                        task_list = TaskLogic.reset(task_list);
                        FileLogic.reset_file(file);
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
            } catch (DukeException | IOException | ArrayIndexOutOfBoundsException e) {
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
