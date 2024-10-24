package org.example;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/todo")
public class ToDoServlet extends HttpServlet {
    private ToDoList todoList;

    @Override
    public void init() throws ServletException {
        super.init();
        todoList = ToDoList.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ToDoItem> items = todoList.getItems();
        request.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/todo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            String description = request.getParameter("description");
            if (description != null && !description.trim().isEmpty()) {
                todoList.addItem(new ToDoItem(description));
            }
        } else if ("remove".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    todoList.removeItem(id);
                } catch (NumberFormatException e) {
                    // Handle invalid ID
                }
            }
        } else if ("clear".equals(action)) {
            todoList.clearItems();
        }
        
        response.sendRedirect(request.getContextPath() + "/todo");
    }
}
