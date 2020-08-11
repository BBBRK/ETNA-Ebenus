<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.User"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.Channel"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>


<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Ebenus</title>
        <!-- CSS files -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800|Oswald:300,400,500,600,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/master.css"> 
    </head>
    <body>
        <div class="outer">
            <div class="header-outer" id="header-outer">
                <!-- Header -->
                <header id="header"  class="header">
                    <div class="header padd-top">
                        <a href="HomeServlet" class="me">Ebenus projet libre back</a>
                        <a href="LoginServlet?action=logout" class="me logout" >Logout</a>
                    </div>
                </header>
            </div>
            <!-- Section -->
          	 <% Channel c = (Channel) request.getAttribute("channel"); %>
          	 <% List<User> users = (List<User>) request.getAttribute("users"); %>
          	 
          	<% if(c != null){ %>
          	
          	<section>
                    <div class="content">
                        <div class="User quest">
                            <h1>UPDATE CHANNEL</h1>
                            
                            <form action="ChannelServlet?action=update&id=<%= c.getIdChannel() %>" method="Post" id="customer-info-form"> 
						        <div class="row">
							        <div class="col-md-5 registered-users">
								        <div>
								        <ul class="form-list">
									         <li>
										        <label for="email" class="required">Channel's name<em>*</em></label>
										        <div class="input-box">
										       		<input type="text" name="name" class="input-text" title="name" value="<%= c.getName() %>">
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Description<em>*</em></label>
										        <div class="input-box">
										       		<textarea type="text" name="description" class="input-text" title="description"><%= c.getDescription() %></textarea>
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Creator<em>*</em></label>
										        <div class="input-box">
										       		<select  class="required" name="creator" id="select_city"> 
                                                        <option value="creator" selected disabled>creator</option>
                                                        <c:forEach items="${users}" var="user">
                                                      	   <option value="${user.getIdUser()}">${user.getNickname()}</option>
                                                        </c:forEach>
                                                     </select>
										        </div>
									        </li>
								        </ul>
								        <p class="required-msg">* champs obligatoires</p>
								        </div>
							        <br>
							        <button class="btn btn-default" type="submit">Update !</button>
							        </div>
						        </div>
				        	</form>
                        </div> 
                    </div>
                </section>
          	
          	 <% }else{ %>
          	 
          	 <section>
                    <div class="content">
                        <div class="User quest">
                            <h1>CREATE CHANNEL</h1>
                            
                            <form action="ChannelServlet?action=create" method="Post" id="customer-info-form"> 
						        <div class="row">
							        <div class="col-md-5 registered-users">
								        <div>
								        <ul class="form-list">
									        <li>
										        <label for="email" class="required">Channel's name<em>*</em></label>
										        <div class="input-box">
										       		<input type="text" name="name" class="input-text" title="name">
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Description<em>*</em></label>
										        <div class="input-box">
										       		<textarea type="text" name="description" class="input-text" title="description"></textarea>
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Creator<em>*</em></label>
										        <div class="input-box">
										       		<select  class="required" name="creator" id="select_city"> 
                                                        <option value="creator" selected disabled>creator</option>
                                                        <c:forEach items="${users}" var="user">
                                                      	   <option value="${user.getIdUser()}">${user.getNickname()}</option>
                                                        </c:forEach>
                                                     </select>
										        </div>
									        </li>
								        </ul>
								        <p class="required-msg">* champs obligatoires</p>
								        </div>
							        <br>
							        <button class="btn btn-default" type="submit">Create !</button>
							        </div>
						        </div>
				        	</form>
                        </div> 
                    </div>
                </section>
          	 
          	  <% } %>
             
           </div>
        <!-- JS files -->
    </body>
</html>
