package toDo;

import org.example.ToDoItem;
import org.example.ToDoList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class ToDoListTest {

    private ToDoList toDoList;

    @Before
    public void setUp() {
        // Use the singleton instance to initialize ToDoList before each test
        toDoList = ToDoList.getInstance();
        // Clear all items in the list to ensure a clean state for each test
        toDoList.clearItems();
    }

    @Test
    public void testAddToDoItem() {
        // Arrange
        ToDoItem task = new ToDoItem("Complete project");

        // Act
        toDoList.addItem(task);  // This will save the task in the database
        List<ToDoItem> items = toDoList.getItems();

        // Assert
        assertNotNull("The list should not be null", items);
        assertEquals("The list should contain 1 item", 1, items.size());
        assertEquals("The task description should match", "Complete project", items.get(0).getDescription());
    }

    @Test
    public void testRemoveToDoItemById() {
        // Arrange
        ToDoItem task = new ToDoItem("Go to the gym");
        toDoList.addItem(task);
        int taskId = task.getId();  // The ID is assigned after saving the task

        // Act
        toDoList.removeItem(taskId);  // Remove by ID
        List<ToDoItem> items = toDoList.getItems();

        // Assert
        assertTrue("The task should be removed, so the list should be empty", items.isEmpty());
    }

    @Test
    public void testClearToDoList() {
        // Arrange
        toDoList.addItem(new ToDoItem("Task 1"));
        toDoList.addItem(new ToDoItem("Task 2"));

        // Act
        toDoList.clearItems();  // Clears all items from the database
        List<ToDoItem> items = toDoList.getItems();

        // Assert
        assertNotNull("The list should not be null", items);
        assertEquals("The list should be empty after clearing", 0, items.size());
    }
}
