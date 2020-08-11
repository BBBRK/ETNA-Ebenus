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
                        <a href="${pageContext.request.contextPath}" title="Ebenus" class="logo">Ebenus projet libre back</a>
                    </div>
                </header>
            </div>
            <!-- Section -->
            <section>
                <div class="content">
                    <div class="account-login">
                        <div class="page-title">
                            <h1>Identifiez vous</h1>
                        </div>
                        <form action="${pageContext.request.contextPath}/LoginServlet" method="Post" id="customer-info-form"> 
                            <div class="row">
                                <div class="col-md-5 registered-users">
                                    <div>
                                        <p>Seuls les administrateurs ont accès à cette partie de l'application</p>
                                        <p>Connectez-vous avec votre <strong>adresse e-mail</strong> et votre <strong>mot de passe</strong> pour y acceder.</p>
                                        <ul class="form-list">
                                            <li>
                                                <label for="email" class="required">Email<em>*</em></label>
                                                <div class="input-box">
                                                    <input type="text" name="email" value="" id="email" class="input-text" title="Email">
                                                </div>
                                            </li>
                                            <li>
                                                <label for="pass" class="required">Mot de passe<em>*</em></label>
                                                <div class="input-box">
                                                    <input type="password" name="password" class="input-text" id="pass" title="Password">
                                                </div>
                                            </li>
                                        </ul>
                                        <p class="required-msg">* champs obligatoires</p>
                                    </div>
                                    <br>
                                    <button class="btn btn-default" type="submit">Connexion</button>
                                </div>
                            </div>
                        </form>
                    </div> 
                </div>
            </section>
        </div>
    </body>
</html>
