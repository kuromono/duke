package duke.program;

import duke.task.*;
import duke.program.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileLogicTest {

    public File initFile(String path) {
        // New test file
        path = new File(path).getAbsolutePath();
        File file = new File(path);
        return file;
    }

    public ArrayList<Task> initTestTasks() {
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

        return task_list;
    }

    @Test void testFileOperations() {
        try {
            File file = initFile("data/empty.txt");
            File update_file = initFile("data/test.txt");
            ArrayList<Task> task_list;
            ArrayList<Task> expected_task_list = new ArrayList<Task>();

            // Test empty file read
            task_list = FileLogic.read_file(file, file.getAbsolutePath());
            assertEquals(expected_task_list, task_list);

            // Test expected file read
            file = initFile("data/expected.txt");
            task_list = FileLogic.read_file(file, file.getAbsolutePath());
            expected_task_list = initTestTasks();
            assertEquals(expected_task_list.toString(), task_list.toString());

            // Test update file
            FileLogic.update_file(update_file, expected_task_list);
            task_list = FileLogic.read_file(update_file, update_file.getAbsolutePath());
            assertEquals(expected_task_list.toString(), task_list.toString());

            // Test reset Task ArrayList
            task_list = TaskLogic.reset(task_list);
            expected_task_list = new ArrayList<Task>();
            assertEquals(expected_task_list, task_list);

            // Test reset file
            file = initFile("data/empty.txt");
            FileLogic.reset_file(update_file);
            assertEquals(file.length(), update_file.length());

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Test failed : Error occured in reading file.");
        }

    }

}
