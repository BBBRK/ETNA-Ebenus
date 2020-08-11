<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.Channel"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.Message"%>
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
             		<a class="buttons" href="${pageContext.request.contextPath}/ChannelServlet">Channels</a>
             		<a class="buttons selected" href="${pageContext.request.contextPath}/MessageServlet">Messages</a>
             	</div>
             	
             	<% List<Message> messages = (List<Message>) request.getAttribute("messages"); %>
             	
             	<div class="container">
	             	<div class="page-title">
	                    <h1>ALL MESSAGES</h1>
	                </div>
	                <div class="table-responsive tble">
                     <table id="myTable" class="table table-bordered table-hover table-striped tablesorter">
	                     <thead>
		                     <tr>
			                     <th class="clickable">Id</th>
			                     <th class="clickable">Content</th>
			                     <th class="clickable">Date</th>
			                     <th class="clickable">User</th>
			                     <th class="clickable">Channel</th>
			                     <th class="clickable">Delete</th>
							 </tr>
						 </thead>
						 <tbody>
						 	<c:forEach items="${messages}" var="message">
						 		<tr>
						 			<td>${message.getIdMessage()}</td>
						 			<td>${message.getContentMessage()}</td>
						 			<td>${message.getDateMessage()}</td>
						 			<td><a href="UserServlet?action=update&id=${message.getUserMessage().getIdUser()}">${message.getUserMessage().getNickname()}</a></td>
						 			<td><a href="ChannelServlet?action=update&id=${message.getChannelMessage().getIdChannel()}">${message.getChannelMessage().getName()}</a></td>
						 			<td><a class="action" href="MessageServlet?action=delete&id=${message.getIdMessage()}">Delete</a></td>
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
        <script src="./assets/js/jquery.min.js"></script>
		<script src="./assets/js/jquery.tablesorter.js"></script>
		<script src="./assets/js/tablesorter.js"></script>
    </body>
</html>
