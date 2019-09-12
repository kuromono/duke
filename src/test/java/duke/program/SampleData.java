package duke.program;

import duke.task.Task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class SampleData {
    public static ArrayList<Task> initTestTasks() {
        //suppress output with redirector
        PrintStream sysout = System.out;
        SampleData.outputRedirector();


        ArrayList<Task> task_list = new ArrayList<Task>();

        TaskLogic.add_task(task_list, TaskLogic.make_task("todo", "test_1", ""));
        TaskLogic.add_task(task_list, TaskLogic.make_task("todo", "test_2", ""));
        TaskLogic.add_task(task_list, TaskLogic.make_task("deadline", "test_3", "10/10/2010 1000"));
        TaskLogic.add_task(task_list, TaskLogic.make_task("todo", "test_4", ""));
        TaskLogic.add_task(task_list, TaskLogic.make_task("event", "test_5", "10/10/2010 2000"));
        TaskLogic.add_task(task_list, TaskLogic.make_task("todo", "test_6", ""));

        TaskLogic.done(task_list, 3);
        TaskLogic.done(task_list, 4);
        TaskLogic.done(task_list, 6);

        //revert output
        System.setOut(sysout);

        return task_list;
    }

    public static ByteArrayOutputStream outputRedirector() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);

        return out;
    }
}
