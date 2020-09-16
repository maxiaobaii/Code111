<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">

    <title>欢迎登陆~</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js">
    <script type="text/javascript">
        window.parent.location.href = "${pageContext.request.contextPath}/login/login.jsp";
    </script>
</head>

<body>
This is my JSP page.
<br>
<h4>中转页~</h4>
</body>
</html>
