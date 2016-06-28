<%--
  Created by IntelliJ IDEA.
  User: Mefju
  Date: 2016-06-03
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js"></script>
    <title>Title</title>
    <script th:inline="javascript">
        var bookList = [[${bookList}]];
    </script>
</head>
<body>
<h1 layout:fragment="header">Book list</h1>
<div layout:fragment="content" class="container">
    <div  id="list">
        <ul class="list"></ul>
    </div>
    <div style="display:none;">
        <table>
            <tr id="item" >
                <td><h3 class="title" id="title-field"></h3></td>
                <td><h3 class="author" id="author-field"></h3></td>
                <td><h3 class="publisher" id="publisher-field"></h3></td>
                <td><h3 class="comment" id="comment-field"></h3></td>
            </tr>
        </table>
    </div>
    <script>
        var options = {
            item: 'item'
        };
        var values = [];

        for (var g of bookList) {
            values.push({title: g.title, author: g.author, publisher: g.publisher, comment: g.comment})
        }
        var gList = new List('list', options, values);
        gList.sort("title");

    </script>
</div>

</body>
</html>
