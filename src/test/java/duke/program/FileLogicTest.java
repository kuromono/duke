package duke.program;

import duke.task.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileLogicTest {

    public File initFile(String path) {
        // New test file
        path = new File(path).getAbsolutePath();
        File file = new File(path);
        return file;
    }

    @Test
    public void testFileOperations() {
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
            expected_task_list = SampleData.initTestTasks();
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
