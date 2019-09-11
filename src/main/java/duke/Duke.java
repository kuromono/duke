package duke;

import duke.program.*;
import duke.task.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";

    /**
     * Main method to initialize the program Duke.
     * @param args Arguments passed to program, currently does nothing.
     */
    public static void main(String[] args) {
        // Print intro text
        printIntro();

        // New file
        String path = new File("data/duke.txt").getAbsolutePath();
        File file = new File(path);

        Scanner read_line = new Scanner(System.in);

        try {
            // Read file
            ArrayList<Task> task_list = FileLogic.read_file(file, path);

            // main logic
            Parser.input_parser(task_list, file, read_line);
        } catch (ClassNotFoundException | IOException e) {
            TaskLogic.printError("Error occurred in reading file.");
        }

        exitDuke();
    }

    /**
     * Prints introductory message on program launch.
     */
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

    /**
     * Prints exiting message and exits the program.
     */
    public static void exitDuke() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);

        System.exit(0);
    }
}
