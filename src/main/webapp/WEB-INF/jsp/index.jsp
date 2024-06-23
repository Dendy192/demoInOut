<%@ page import="com.dendy.countinout.vo.MessageVo" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dtiaw
  Date: 6/23/2024
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
            rel="stylesheet"
    />

    <link rel="stylesheet" href="<c:url value="assets/fonts/icomoon/style.css"/>"/>

    <link rel="stylesheet" href="<c:url value="assets/css/owl.carousel.min.css"/>"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<c:url value="assets/css/bootstrap.min.css"/>"/>

    <!-- Style -->
    <link rel="stylesheet" href="<c:url value="assets/css/login.css"/>"/>

    <title>Login</title>
</head>
<body>
<div class="content">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <img
                        src="<c:url value="assets/images/undraw_remotely_2j6y.svg"/>"
                        alt="Image"
                        class="img-fluid"
                />
            </div>
            <div class="col-md-6 contents">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="mb-4">
                            <h3>Sign In</h3>
                        </div>
                        <% MessageVo m1=(MessageVo) request.getSession().getAttribute("msgLogin");
                            if(m1!=null){
                        %><div class="alert alert-<%=m1.getCssClass()%>" role="alert">
                        <%=m1.getContent() %>
                    </div>
                        <%
                            }
                        %>
                        <%request.getSession().removeAttribute("msgLogin"); %>
                        <c:url value="/login" var="action"></c:url>

                        <form:form action="${action}" method="post" modelAttribute="loginForm">
                            <div class="form-group first">
                                <label for="username">Username</label>
                                <form:input path="username" type="text" class="form-control" id="username"/>
                            </div>
                            <div class="form-group last mb-4">
                                <label for="password">Password</label>
                                <form:input path="password" type="password" class="form-control" id="password"/>
                            </div>

                            <input
                                    type="submit"
                                    value="Log In"
                                    class="btn btn-block btn-primary"
                            />
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="assets/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="assets/js/popper.min.js"/>"></script>
<script src="<c:url value="assets/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="assets/js/login.js"/>"></script>
</body>
</html>
