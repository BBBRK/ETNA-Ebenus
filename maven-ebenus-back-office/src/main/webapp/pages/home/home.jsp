<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
             		<a class="buttons" href="${pageContext.request.contextPath}/MessageServlet">Messages</a>
             	</div>
            </section>
        </div>
    </body>
</html>
