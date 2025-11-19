
package com.bookhub.servlet;

import com.bookhub.dao.BookDAO;
import com.bookhub.model.Book;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ViewBooksServlet extends HttpServlet {
	private BookDAO bookDAO;

	public void init() {
		bookDAO = new BookDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> listBooks = bookDAO.selectAllBooks();
		request.setAttribute("listBooks", listBooks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewBooks.jsp");
		dispatcher.forward(request, response);
	}
}