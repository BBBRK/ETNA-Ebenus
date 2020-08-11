<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.Channel"%>
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
             		<a class="buttons" href="${pageContext.request.contextPath}/UserServlet">Users</a>
             		<a class="buttons selected" href="${pageContext.request.contextPath}/ChannelServlet">Channels</a>
             		<a class="buttons" href="${pageContext.request.contextPath}/MessageServlet">Messages</a>
             	</div>
             	
             	<% List<Channel> channels = (List<Channel>) request.getAttribute("channels"); %>
             	
             	<div class="container">
	             	<div class="page-title">
	                    <h1>ALL CHANNELS</h1>
	                </div>
	                
	                <a class="new" href="${pageContext.request.contextPath}/ChannelServlet?action=create">New Channel</a>
	                
	                <div class="table-responsive tble">
                     <table class="table table-bordered table-hover table-striped">
	                     <thead>
		                     <tr>
			                     <th>Id</th>
			                     <th>Name</th>
			                     <th>Description</th>
			                     <th>Id Creator</th>
			                     <th>Edit</th>
			                     <th>Delete</th>
							 </tr>
						 </thead>
						 <tbody>
						 	<c:forEach items="${channels}" var="channel">
						 		<tr>
						 			<td>${channel.getIdChannel()}</td>
						 			<td>${channel.getName()}</td>
						 			<td>${channel.getDescription()}</td>
						 			<td>${channel.getCreator()}</td>
						 			<td><a class="action" href="ChannelServlet?action=update&id=${channel.getIdChannel()}">Edit</a></td>
						 			<td><a class="action" href="ChannelServlet?action=delete&id=${channel.getIdChannel()}">Delete</a></td>
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
