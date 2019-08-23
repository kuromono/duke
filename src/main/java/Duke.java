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

        ArrayList<Task> task_list = new ArrayList<Task>();

        // INPUT LOOP
        Scanner read_line = new Scanner(System.in);
        String input = "";
        while(!input.equals("bye"))
        {
            // read input & tokenize
            input = read_line.nextLine();
            String[] tokenized_input = input.split(" ");

            if (input.equals("list")) // Lists Everything
            {
                if (task_list.size() != 0) // Set task list boundary
                {
                    for(int i = 0; i < task_list.size(); i += 1)
                    {
                        Task t = task_list.get(i);
                        System.out.println((i + 1) + "." + t.getStatusIcon() + " " + t.description);
                    }
                }
                else
                {
                    System.out.println(line + "\nThere is nothing in the list!\n" + line);
                }
            }
            else if(tokenized_input[0].equals("done")) // Mark Tasks as Done
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
                Task t = new Task(input);
                task_list.add(t);
                System.out.println(line + "\nadded: " + input + "\n" + line);
            }

        }

        // OUTRO TEXT
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        // Close scanner & Exit
        read_line.close();
        System.exit(0);
    }
}
