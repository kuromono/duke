package duke.program;

import duke.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static final String LINE = "____________________________________________________________";

    /**
     * Method responsible for parsing input from the user and perform program tasks.
     * @param task_list Task ArrayList containing values to perform operations on.
     * @param file File object to write updates into.
     * @param read_line Scanner object for reading input from.
     */
    public static void input_parser(ArrayList<Task> task_list, File file, Scanner read_line) {
        System.out.println(LINE);

        // INPUT LOOP
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
                        TaskLogic.printError("The index of <done> cannot be empty.");
                        continue;
                    } else if (!TaskLogic.IntValidator(tokenized_input[1])) {
                        TaskLogic.printError("The index of <done> is not a valid integer.");
                        continue;
                    }

                    TaskLogic.done(task_list, Integer.parseInt(tokenized_input[1]));
                    FileLogic.update_file(file, task_list);
                } else if (tokenized_input[0].equals("delete")) {
                    if (tokenized_input.length == 1) {
                        TaskLogic.printError("The index of <delete> cannot be empty.");
                        continue;
                    } else if (!TaskLogic.IntValidator(tokenized_input[1])) {
                        TaskLogic.printError("The index of <delete> is not a valid integer.");
                        continue;
                    }

                    TaskLogic.delete(task_list, Integer.parseInt(tokenized_input[1]));
                    FileLogic.update_file(file, task_list);
                } else if (tokenized_input[0].equals("find")) {
                    // Search task based on keyword
                    if (tokenized_input.length == 1) {
                        TaskLogic.printError("The value of <find> cannot be empty.");
                        continue;
                    }

                    TaskLogic.find_task(task_list, tokenized_input[1]);
                } else {
                    // Adds new Tasks
                    String desc;
                    if (tokenized_input[0].equals("todo")) {
                        if (input.strip().length() < 5) {
                            TaskLogic.printError("The description of a <todo> cannot be empty.");
                            continue;
                        }

                        TaskLogic.add_task(task_list, TaskLogic.make_task("todo", input.substring(5), ""));
                        FileLogic.update_file(file, task_list);
                    } else if (tokenized_input[0].equals("deadline")) {
                        if (tokenized_input.length < 3) {
                            TaskLogic.printError("The description of a <deadline> cannot be empty.");
                            TaskLogic.printError("Format: deadline <description> /by DD/MM/YYYY HHMM");
                            continue;
                        }

                        tokenized_input = input.substring(9).split(" /by ");
                        if (tokenized_input.length < 2) {
                            TaskLogic.printError("Incorrect format. Format should be: deadline <description> /by DD/MM/YYYY HHMM");
                            continue;
                        } else if (!TaskLogic.DateValidator(tokenized_input[1])) {
                            TaskLogic.printError("Date format must be DD/MM/YYYY HHMM, E.g. 02/12/2019 1800");
                            continue;
                        }

                        TaskLogic.add_task(task_list, TaskLogic.make_task("deadline", tokenized_input[0], tokenized_input[1]));
                        FileLogic.update_file(file, task_list);
                    } else if (tokenized_input[0].equals("event")) {
                        if (tokenized_input.length < 3) {
                            TaskLogic.printError("The description of a <event> cannot be empty.");
                            TaskLogic.printError("Format: event <description> /at DD/MM/YYYY HHMM");
                            continue;
                        }

                        tokenized_input = input.substring(6).split(" /at ");
                        if (tokenized_input.length < 2) {
                            TaskLogic.printError("Incorrect format. Format should be: event <description> /at DD/MM/YYYY HHMM");
                            continue;
                        } else if (!TaskLogic.DateValidator(tokenized_input[1])) {
                            TaskLogic.printError("Date format must be DD/MM/YYYY HHMM, E.g. 02/12/2019 1800");
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
                        return;
                    } else {
                        throw new DukeException("\n"
                                + "The system currently only supports the following formats:\n"
                                + "  todo <description>\n"
                                + "  deadline <description> /by <date>\n"
                                + "  event <description> /at <date>\n"
                                + "  list\n"
                                + "  done <index>\n"
                                + "  delete <index>\n"
                                + "  find <keyword>\n"
                                + "  reset");
                    }
                }
            } catch (DukeException | IOException | ArrayIndexOutOfBoundsException e) {
                System.err.println(e);
            }

        }
    }
}
