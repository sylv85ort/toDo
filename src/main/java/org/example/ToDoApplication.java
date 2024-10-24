package org.example;

import java.util.List;
import java.util.Scanner;

public class ToDoApplication {
    private ToDoList todoList;
    private Scanner scanner;

    public ToDoApplication() {
        todoList = ToDoList.getInstance();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    viewItems();
                    break;
                case 4:
                    clearItems();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Goodbye!");
    }

    private void printMenu() {
        System.out.println("\n1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. View Items");
        System.out.println("4. Clear All Items");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addItem() {
        System.out.print("Enter the todo item: ");
        String description = scanner.nextLine();
        todoList.addItem(new ToDoItem(description));
        System.out.println("Item added successfully.");
    }

    private void removeItem() {
        viewItems();
        System.out.print("Enter the ID of the item to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        todoList.removeItem(id);
        System.out.println("Item removed successfully.");
    }

    private void viewItems() {
        List<ToDoItem> items = todoList.getItems();
        if (items.isEmpty()) {
            System.out.println("The todo list is empty.");
        } else {
            System.out.println("Todo List:");
            for (ToDoItem item : items) {
                System.out.println(item.getId() + ". " + item.getDescription());
            }
        }
    }

    private void clearItems() {
        todoList.clearItems();
        System.out.println("All items have been cleared.");
    }

    public static void main(String[] args) {
        new ToDoApplication().run();
    }
}
