package duke;

import duke.program.*;
import duke.task.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    // Carriage Return Line Feed - edit this if test fails
    public static final String CRLF = "\r\n";

    public String initDuke(String input) {
        // New file
        String path = new File("data/duke.txt").getAbsolutePath();
        System.out.println(path);

        File file = new File(path);

        // Input/Output Redirector
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner read_line = new Scanner(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        PrintStream sysout = System.out;
        System.setOut(ps);


            // Read file
            ArrayList<Task> task_list = new ArrayList<Task>();

            // main logic
            Parser.input_parser(task_list, file, read_line);


        System.setOut(sysout);
        return out.toString();
    }

    @Test
    public void testEmpty() {
        String input =
                "reset" + CRLF +
                        "list" + CRLF +
                        "bye";

        String output =
                "____________________________________________________________" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Everything has been cleared!" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "There is nothing in the list!" + CRLF +
                        "____________________________________________________________" + CRLF;

        assertEquals(output, initDuke(input));
        //System.out.println(initDuke(input));
    }

    @Test
    public void testInput() { //Same input/output as text-ui-test
        String input =
                "reset" + CRLF +
                        "list" + CRLF +
                        "todo_wrong" + CRLF +
                        "todo Hello World" + CRLF +
                        "todo More World" + CRLF +
                        "list" + CRLF +
                        "done 4" + CRLF +
                        "done 2" + CRLF +
                        "event" + CRLF +
                        "event Visit World /at 10/12/2019 1900" + CRLF +
                        "event Error in Date /at 10/99/2999 1900" + CRLF +
                        "event Error in Time /at 10/12/2099 9999" + CRLF +
                        "event Visit Again /at 10/12/2020 0000" + CRLF +
                        "list" + CRLF +
                        "done 3" + CRLF +
                        "done 4" + CRLF +
                        "deadline /by wrong_format" + CRLF +
                        "deadline wrong_format /by" + CRLF +
                        "deadline Hello World Again /by 10/12/2019 1900" + CRLF +
                        "deadline Error in Date /by 10/99/2999 2000" + CRLF +
                        "deadline Error in Time /by 10/09/1999 9000" + CRLF +
                        "deadline wrong_format /at 10/12/2018 1230" + CRLF +
                        "todo Hello" + CRLF +
                        "event Test again /at 10/12/2022 0000" + CRLF +
                        "list" + CRLF +
                        "done 5" + CRLF +
                        "bye";

        String output =
                "____________________________________________________________" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Everything has been cleared!" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "There is nothing in the list!" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Got it. I've added this task: " + CRLF +
                        "   [T][✘] Hello World" + CRLF +
                        "Now you have 1 tasks in the list." + CRLF +
                        "____________________________________________________________" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Got it. I've added this task: " + CRLF +
                        "   [T][✘] More World" + CRLF +
                        "Now you have 2 tasks in the list." + CRLF +
                        "____________________________________________________________" + CRLF +
                        " 1.[T][✘] Hello World" + CRLF +
                        " 2.[T][✘] More World" + CRLF +
                        "Value is out of bounds." + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Nice! I've marked this task as done:" + CRLF +
                        "  [T][✓] More World" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "The description of a <event> cannot be empty." + CRLF +
                        "Format: event <description> /at DD/MM/YYYY HHMM" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Got it. I've added this task: " + CRLF +
                        "   [E][✘] Visit World (at: 2019-12-10 19:00)" + CRLF +
                        "Now you have 3 tasks in the list." + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Date format must be DD/MM/YYYY HHMM, E.g. 02/12/2019 1800" + CRLF +
                        "Date format must be DD/MM/YYYY HHMM, E.g. 02/12/2019 1800" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Got it. I've added this task: " + CRLF +
                        "   [E][✘] Visit Again (at: 2020-12-10 00:00)" + CRLF +
                        "Now you have 4 tasks in the list." + CRLF +
                        "____________________________________________________________" + CRLF +
                        " 1.[T][✘] Hello World" + CRLF +
                        " 2.[T][✓] More World" + CRLF +
                        " 3.[E][✘] Visit World (at: 2019-12-10 19:00)" + CRLF +
                        " 4.[E][✘] Visit Again (at: 2020-12-10 00:00)" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Nice! I've marked this task as done:" + CRLF +
                        "  [E][✓] Visit World (at: 2019-12-10 19:00)" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Nice! I've marked this task as done:" + CRLF +
                        "  [E][✓] Visit Again (at: 2020-12-10 00:00)" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Incorrect format. Format should be: deadline <description> /by DD/MM/YYYY HHMM" + CRLF +
                        "Incorrect format. Format should be: deadline <description> /by DD/MM/YYYY HHMM" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Got it. I've added this task: " + CRLF +
                        "   [D][✘] Hello World Again (by: 2019-12-10 19:00)" + CRLF +
                        "Now you have 5 tasks in the list." + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Date format must be DD/MM/YYYY HHMM, E.g. 02/12/2019 1800" + CRLF +
                        "Date format must be DD/MM/YYYY HHMM, E.g. 02/12/2019 1800" + CRLF +
                        "Incorrect format. Format should be: deadline <description> /by DD/MM/YYYY HHMM" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Got it. I've added this task: " + CRLF +
                        "   [T][✘] Hello" + CRLF +
                        "Now you have 6 tasks in the list." + CRLF +
                        "____________________________________________________________" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Got it. I've added this task: " + CRLF +
                        "   [E][✘] Test again (at: 2022-12-10 00:00)" + CRLF +
                        "Now you have 7 tasks in the list." + CRLF +
                        "____________________________________________________________" + CRLF +
                        " 1.[T][✘] Hello World" + CRLF +
                        " 2.[T][✓] More World" + CRLF +
                        " 3.[E][✓] Visit World (at: 2019-12-10 19:00)" + CRLF +
                        " 4.[E][✓] Visit Again (at: 2020-12-10 00:00)" + CRLF +
                        " 5.[D][✘] Hello World Again (by: 2019-12-10 19:00)" + CRLF +
                        " 6.[T][✘] Hello" + CRLF +
                        " 7.[E][✘] Test again (at: 2022-12-10 00:00)" + CRLF +
                        "____________________________________________________________" + CRLF +
                        "Nice! I've marked this task as done:" + CRLF +
                        "  [D][✓] Hello World Again (by: 2019-12-10 19:00)" + CRLF +
                        "____________________________________________________________" + CRLF;

        assertEquals(output, initDuke(input));
    }
}
