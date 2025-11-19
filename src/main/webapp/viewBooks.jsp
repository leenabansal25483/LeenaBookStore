
<%@ page import="java.util.*,com.bookhub.model.Book" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book List</title>
</head>
<body>
<h2>List of Books</h2>
<table border="1" cellpadding="5">
    <tr>
        <th>ID</th><th>Title</th><th>Author</th><th>Price</th>
    </tr>
    <%
        List<Book> listBooks = (List<Book>) request.getAttribute("listBooks");
        if (listBooks != null) {
            for (Book b : listBooks) {
    %>
        <tr>
            <td><%= b.getBookId() %></td>
            <td><%= b.getTitle() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getPrice() %></td>
        </tr>
    <%
            }
        }
    %>
</table>
<br>
<a href="addBook.jsp">Add Another Book</a>
</body>
</html>
