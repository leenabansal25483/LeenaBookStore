
package com.bookhub.servlet;

import com.bookhub.dao.BookDAO;
import com.bookhub.model.Book;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddBookServlet extends HttpServlet {
    private BookDAO bookDAO;

    public void init() {
        bookDAO = new BookDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setPrice(price);

        System.out.println("📘 Adding Book: " + title + ", " + author + ", " + price);
        bookDAO.insertBook(newBook);

        response.sendRedirect("viewBooks"); // must match your servlet mapping
    }
}
