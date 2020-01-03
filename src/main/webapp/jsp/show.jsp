<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>

    <script>

    </script>
</head>

<body>
    <table>
        <tr>
           <td>id</td>
           <td>title</td>
           <td>author</td>
           <td>content</td>
           <td>guru_id</td>
           <td>create_date</td>
           <td>status</td>
        </tr>
        <tr>
            <td>${sessionScope.text.id}</td>
            <td>${sessionScope.text.title}</td>
            <td>${sessionScope.text.author}</td>
            <td>${sessionScope.text.content}</td>
            <td>${sessionScope.text.guru_id}</td>
            <td><fmt:formatDate value="${sessionScope.text.create_date}" pattern="yyyy-MM-dd"/></td>
            <td>${sessionScope.text.status}</td>
        </tr>
    </table>
</body>

</html>