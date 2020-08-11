<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.Channel"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.Message"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.User"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tchat</title>
        <!-- CSS files -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800|Oswald:300,400,500,600,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="./assets/css/master.css"> 
    </head>
    <body>
    	<% User u = (User) request.getAttribute("user"); %>
    	<% System.out.println(u); %>
	    <!-- Header -->
	    <div class="header-outer" id="header-outer">
		    <header id="header"  class="header">
			    <div class="header padd-top">
			   		 <a href="UserServlet?action=update" class="me">Hello <%= u.getNickname() %></a>	 
			   		 <a href="LoginServlet?action=logout" class="me logout" >Logout</a>
			    </div>
			</header>
		</div>
		
		<section>
			<% List<Channel> channels = (List<Channel>) request.getAttribute("channels"); %>
			<% List<Message> messages = (List<Message>) request.getAttribute("messages"); %>
			
			<div id="which" data-channel="" data-creator=""></div>
			<textarea id="idUser" hidden><%= u.getIdUser() %></textarea>
			<div class="row">
				<div class="col-lg-2 col-channel">
					<div class="container">
						<h1>Channels</h1>
						<div class="row">
							<a class="create-channel" href="TchatServlet?action=createview">Create channel</a>
							<a class="create-channel" href="TchatServlet?action=updateview&iduser=<%= u.getIdUser() %>">Update channel</a>
							<a class="create-channel" href="TchatServlet?action=deleteview&iduser=<%= u.getIdUser() %>">Delete channel</a>
						</div>
						
						<hr>
						<div class="container">
							<ul>
								<c:forEach items="${channels}" var="channel"> 
									<li>
										<button id="${channel.getIdChannel()}" onclick="requestChannel(${channel.getIdChannel()}, ${channel.getCreator()});" class="channel">${channel.getName()}</button>
									</li>
								</c:forEach> 
							</ul>
						</div>
					</div>
				</div>
				
				<div class="col-lg-10 col-tchat">
					<div id="head-tchat">
						<h1 class="head-tchat">Tchat</h1>
					</div>
				
					<hr>
					<div class="container">
						<div id="ajax" class="container conversation">
							<!-- Rempli grâce à l'ajax dans le fichier ajax.js -->
						</div>
						<div class="container message-send">
							<textarea type="text" id="message" class="" name="" placeholder="..."></textarea>
							<button class="btn btn-primary btn-block btn-lg" id="submitMessage" onclick="send();" name="submit">Send</button>
						</div>
					</div>
				</div>
			</div>
		</section>
		
		<script src="./assets/js/jquery.min.js"></script>
		<script src="./assets/js/ajax.js"></script>
    </body>
</html>
