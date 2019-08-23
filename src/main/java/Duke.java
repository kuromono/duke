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

        // INPUT LOOP
        Scanner read_line = new Scanner(System.in);
        String input = "";
        while(!input.equals("bye"))
        {
            input = read_line.nextLine();
            System.out.println(line + "\n" + input + "\n" + line);
        }

        // OUTRO TEXT
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
