import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________";

        // INTRO TEXT
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);

        ArrayList<String> input_list = new ArrayList<String>();

        // INPUT LOOP
        Scanner read_line = new Scanner(System.in);
        String input = "";
        while(!input.equals("bye"))
        {
            input = read_line.nextLine();

            if (!input.equals("list"))
            {
                input_list.add(input);
                System.out.println(line + "\nadded: " + input + "\n" + line);
            }
            else
            {
                for(int i = 0; i < input_list.size(); i += 1)
                {
                    System.out.println(i + ". " + input_list.get(i));
                }
            }

        }

        // OUTRO TEXT
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        // CLOSE SCANNER
        read_line.close();
    }
}
