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
            Duke.input_parser(task_list, file, read_line);


        System.setOut(sysout);
        return out.toString();
    }
}
