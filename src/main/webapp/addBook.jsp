<!DOCTYPE html>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
<h2>Add a New Book</h2>
<form action="addBook" method="post">
    Book Title<input type="text" name="title" placeholder="Title" required>
    Publisher <input type="text" name="author" placeholder="Author" required>
    Amount <input type="number" name="price" step="0.01" placeholder="Price" required>
    <input type="submit" value="Add Book">
</form>

<br>
<a href="viewBooks">View Books</a>
</body>
</html>
