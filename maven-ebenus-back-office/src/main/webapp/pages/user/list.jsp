<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Ebenus back</title>
        <!-- CSS files -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800|Oswald:300,400,500,600,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="./assets/css/master.css"> 
    </head>
    <body>
        <div class="outer">
            <div class="header-outer" id="header-outer">
                <!-- Header -->
                <header id="header"  class="header">
                    <div class="header padd-top">
                        <p class="me">Ebenus projet libre back</p>
                        <a href="LoginServlet?action=logout" class="me logout" >Logout</a>
                    </div>
                </header>
            </div>
            <!-- Section -->
            <section>
             	<div class="links">
             		<a class="buttons" href="${pageContext.request.contextPath}/RoleServlet">Roles</a>
             		<a class="buttons selected" href="${pageContext.request.contextPath}/UserServlet">Users</a>
             		<a class="buttons" href="${pageContext.request.contextPath}/ChannelServlet">Channels</a>
             		<a class="buttons" href="${pageContext.request.contextPath}/MessageServlet">Messages</a>
             	</div>
             	
             	<% List<User> users = (List<User>) request.getAttribute("users"); %>
             	
             	<div class="container">
	             	<div class="page-title">
	                    <h1>ALL USERS</h1>
	                </div>
	                
	                <a class="new" href="${pageContext.request.contextPath}/UserServlet?action=create">New User</a>
	                
	                <div class="table-responsive tble">
                     <table class="table table-bordered table-hover table-striped">
	                     <thead>
		                     <tr>
			                     <th>Id</th>
			                     <th>Identifiant</th>
			                     <th>Nickname</th>
			                     <th>Role</th>
			                     <th>Civilite</th>
			                     <th>Created At</th>
			                     <th>Updated At</th>
			                     <th>Edit</th>
			                     <th>Delete</th>
							 </tr>
						 </thead>
						 <tbody>
						 	<c:forEach items="${users}" var="user">
						 		<tr>
						 			<td>${user.getIdUser()}</td>
						 			<td>${user.getIdentifiant()}</td>
						 			<td>${user.getNickname()}</td>
						 			<td><a href="RoleServlet?action=update&id=${user.getRole().getIdRole()}">${user.getRole().getName()}</a></td>
						 			<td>${user.getCivilite()}</td>
						 			<td>${user.getCreationDate()}</td>
						 			<td>${user.getUpdatedDate()}</td>
						 			<td><a class="action" href="UserServlet?action=update&id=${user.getIdUser()}">Edit</a></td>
						 			<td><a class="action" href="UserServlet?action=delete&id=${user.getIdUser()}">Delete</a></td>
						 		<tr>
						 	</c:forEach>
	 					 </tbody>
					 </table>
                 </div>
             	</div>
            </section>
        </div>
    </body>
</html>
