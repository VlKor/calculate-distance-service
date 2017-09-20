<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Upload file to database</h1>
<form action="upload" method="post" enctype="multipart/form-data">
    <p>Select a file : <input type="file" name="uploadedFile" size="50"/></p>
    <input type="submit" value="Upload"/>
</form>
</body>
</html>