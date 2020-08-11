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

	    <!-- Header -->
	    <div class="header-outer" id="header-outer">
		    <header id="header"  class="header">
			    <div class="header padd-top">
					<a href="TchatServlet" class="me">Revenir au tchat</a>
			    </div>
			</header>
		</div>
		
		<section>
			<% List<Channel> channels = (List<Channel>) request.getAttribute("channels"); %>
			<div class="container">
				<div class="page-title">
                    <h1>DELETE CHANNEL</h1>
                </div>
				<form action="TchatServlet?action=deleteChannel" method="Post" id="customer-info-form"> 
			        <div class="row">
				        <div class="col-md-5 registered-users">
					        <div>

					        <ul class="form-list">        
						        <label for="pass" class="required">My channels</label>
						        <select class="required" name="deletedChannel">
							        <c:forEach items="${channels}" var="channel"> 
											<option value="${channel.getIdChannel()}">${channel.getName()}</option>
									</c:forEach> 
								</select>
					        </ul>
					        </div>
				        <br>
				        <button class="btn btn-default" type="submit">Delete !</button>
				        </div>
			        </div>
		        </form>
			</div>
		</section>
		
		<script src="./assets/js/jquery.min.js"></script>
    </body>
</html>
