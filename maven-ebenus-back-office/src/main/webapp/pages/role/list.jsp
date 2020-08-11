<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.Role"%>
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
             		<a class="buttons selected" href="${pageContext.request.contextPath}/RoleServlet">Roles</a>
             		<a class="buttons" href="${pageContext.request.contextPath}/UserServlet">Users</a>
             		<a class="buttons" href="${pageContext.request.contextPath}/ChannelServlet">Channels</a>
             		<a class="buttons" href="${pageContext.request.contextPath}/MessageServlet">Messages</a>
             	</div>
             	
             	<% List<Role> roles = (List<Role>) request.getAttribute("roles"); %>
             	
             	<div class="container">
	             	<div class="page-title">
	                    <h1>ALL ROLES</h1>
	                </div>
	                
	                <a class="new" href="${pageContext.request.contextPath}/RoleServlet?action=create">New role</a>
	                
	                <div class="table-responsive tble">
                     <table class="table table-bordered table-hover table-striped">
	                     <thead>
		                     <tr>
			                     <th>Id</th>
			                     <th>Name</th>
			                     <th>Edit</th>
			                     <th>Delete</th>
							 </tr>
						 </thead>
						 <tbody>
						 	<c:forEach items="${roles}" var="role">
						 		<tr>
						 			<td>${role.getIdRole()}</td>
						 			<td>${role.getName()}</td>
						 			<td><a class="action" href="RoleServlet?action=update&id=${role.getIdRole()}">Edit</a></td>
						 			<td><a class="action" href="RoleServlet?action=delete&id=${role.getIdRole()}">Delete</a></td>
						 		<tr>
						 	</c:forEach>
	
	 					 </tbody>
					 </table>
					 <ul class="User">

					 </ul>
                 </div>
             	</div>
            </section>
        </div>
    </body>
</html>
