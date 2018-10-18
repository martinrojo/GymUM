<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Spring MVC Application</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="span8 offset2">
            <h1>Users</h1>
            <form:form method="post" action="add" modelAttribute="user" class="form-horizontal">
            <div class="control-group">
                <form:label cssClass="control-label" path="nombre">Nombre:</form:label>
                <div class="controls">
                    <form:input path="nombre"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="apellido">Apellido:</form:label>
                <div class="controls">
                    <form:input path="apellido"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="email">Email:</form:label>
                <div class="controls">
                    <form:input path="email"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="email">DNI:</form:label>
                <div class="controls">
                    <form:input path="dni"/>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input type="submit" value="Add User" class="btn"/>
                    </form:form>
                </div>
            </div>

            <c:if test="${!empty user}">
                <h3>Users</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>DNI</th>
                        <th>&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="usuario">
                        <tr>
                            <td>${usuario.apellido}, ${usuario.nombre}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.dni}</td>
                            <td>
                                <form action="delete/${usuario.id}" method="post"><input type="submit"
                                                                                      class="btn btn-danger btn-mini"
                                                                                      value="Delete"/></form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <a href="/api/users" target="_blank" class="btn btn-default">Test API</a>
        </div>
    </div>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>