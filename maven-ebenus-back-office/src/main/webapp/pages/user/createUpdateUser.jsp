<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.User"%>
<%@ page import="com.cours.ebenus.maven.ebenus.dao.entities.Role"%>
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
          	 <% User u = (User) request.getAttribute("user"); %>
          	 <% List<Role> roles = (List<Role>) request.getAttribute("roles"); %>
          	 
          	<% if(u != null){ %>
          	
          	<section>
                    <div class="content">
                        <div class="User quest">
                            <h1>UPDATE USER</h1>
                            
                            <form action="UserServlet?action=update&id=<%= u.getIdUser() %>" method="Post" id="customer-info-form"> 
						        <div class="row">
							        <div class="col-md-5 registered-users">
								        <div>
								        <ul class="form-list">
									        <li>
										        <label for="email" class="required">Identifiant<em>*</em></label>
										        <div class="input-box">
										       		<input type="text" name="identifiant" class="input-text" title="identifiant" value="<%= u.getIdentifiant() %>">
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Nickname<em>*</em></label>
										        <div class="input-box">
										       		<input type="text" name="nickname" class="input-text" title="nickname" value="<%= u.getNickname() %>">
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Civilite<em>*</em></label>
										        <div class="input-box">
										       	 	<select  class="required" name="civilite" id="select_city"> 
                                                        <option value="Mr" <% if(u.getCivilite().equals("Mr")) { %> selected <% } %>>Mr</option>
                                                        <option value="Mrs" <% if(u.getCivilite().equals("Mrs")) { %> selected <% } %>>Mrs</option>
                                                     </select>	
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Role<em>*</em></label>
										        <div class="input-box">
										       		<select  class="required" name="role" id="select_city"> 
                                                        <option value="role" selected disabled>role</option>
                                                        <c:forEach items="${roles}" var="role">
                                                      	   <option value="${role.getIdRole()}">${role.getName()}</option>
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
                            <h1>CREATE USER</h1>
                            
                            <form action="UserServlet?action=create" method="Post" id="customer-info-form"> 
						        <div class="row">
							        <div class="col-md-5 registered-users">
								        <div>
								        <ul class="form-list">
									        <li>
										        <label for="email" class="required">Identifiant<em>*</em></label>
										        <div class="input-box">
										       		<input type="text" name="identifiant" class="input-text" title="identifiant">
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Password<em>*</em></label>
										        <div class="input-box">
										       		<input type="password" name="password" class="input-text" title="password">
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Nickname<em>*</em></label>
										        <div class="input-box">
										       		<input type="text" name="nickname" class="input-text" title="nickname">
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Civilite<em>*</em></label>
										        <div class="input-box">
										       	 	<select  class="required" name="civilite" id="select_city"> 
                                                        <option value="Mr">Mr</option>
                                                        <option value="Mrs">Mrs</option>
                                                     </select>	
										        </div>
									        </li>
									        <li>
										        <label for="email" class="required">Role<em>*</em></label>
										        <div class="input-box">
										       		<select  class="required" name="role" id="select_city"> 
                                                        <option value="role" selected disabled>role</option>
                                                        <c:forEach items="${roles}" var="role">
                                                      	   <option value="${role.getIdRole()}">${role.getName()}</option>
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
