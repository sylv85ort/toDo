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
        // Initialize a new ToDoList instance before each test
        toDoList = new ToDoList();
    }

    @Test
    public void testAddToDoItem() {
        // Arrange
        ToDoItem task = new ToDoItem("Complete project");

        // Act
        toDoList.addItem(task);
        List<ToDoItem> items = toDoList.getItems();

        // Assert
        assertNotNull("The list should not be null", items);  // Ensure the list is not null
        assertEquals("The list should contain 1 item", 1, items.size());  // Ensure the list has one item
        assertTrue("The task should be in the list", items.contains(task));  // Ensure the task was added
    }

    @Test
    public void testRemoveToDoItem() {
        // Arrange
        ToDoItem task = new ToDoItem("Go to the gym");
        toDoList.addItem(task);

        // Act
        boolean removed = toDoList.removeItem(task);
        List<ToDoItem> items = toDoList.getItems();

        // Assert
        assertTrue("The task should be removed successfully", removed);  // Ensure the item was removed successfully
        assertEquals("The list should be empty after removal", 0, items.size());  // Ensure the list is now empty
        assertFalse("The task should no longer be in the list", items.contains(task));  // Ensure the task is not in the list
    }

    @Test
    public void testClearToDoList() {
        // Arrange
        toDoList.addItem(new ToDoItem("Task 1"));
        toDoList.addItem(new ToDoItem("Task 2"));

        // Act
        toDoList.clearItems();
        List<ToDoItem> items = toDoList.getItems();

        // Assert
        assertNotNull("The list should not be null after clearing", items);  // Ensure the list is not null
        assertEquals("The list should be empty after clearing", 0, items.size());  // Ensure the list is cleared
    }
}
