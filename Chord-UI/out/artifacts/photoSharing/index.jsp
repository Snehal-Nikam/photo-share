<%--
  Created by IntelliJ IDEA.
  User: Snehal-Nikam
  Date: 2019-03-10
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>This is photo share library</title>
</head>
<body>
    <h1>Enter tag and node you want to search file in : </h1>
    <form action="/photoShare/home">
        <label for="input1">Tag : </label>
        <input type="text" id="input1" name="tag"><br><br>
        <label for="input2"> Node :</label>
        <input type="text" id="input2" name="node"><br><br>
        <input type="submit" value="Submit">
    </form>

</body>
</html>
