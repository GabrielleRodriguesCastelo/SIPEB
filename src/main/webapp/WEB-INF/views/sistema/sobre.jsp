
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sobre - SiPEB</title>
    
    <!-- Adicionar favicon - Ícone da aba das páginas -->
	<link rel="shortcut icon" href="../resoufavicon.ico" type="image/x-icon">
	<link rel="icon" href="/resources/fonts/favicon.ico" type="image/x-icon">

  
	
  	
  	 <link href="<c:url value='/resources/css/bootstrap.css'  />" rel="stylesheet"/>
	<link href="<c:url value='/resources/css/business-casual.css'  />" rel="stylesheet"/>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	

<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <div class="brand">SiPEB</div>
    <div class="address-bar">Sistema de Presença Escolar Biométrico</div>

    <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    
                     <form:form action="${spring:mvcUrl('montarSobre').build()}"
					method="GET" commandName="sistema" role="sobre"> 
                </button>   
					
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <a class="navbar-brand" href="index.html">SiPEB</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="../sistema/home">Home</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Sobre
                        <strong>SiPEB</strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-md-6">
                    <!-- Embedded Google Map using an iframe - to select your location find it on Google maps and paste the link as the iframe src. If you want to use the Google Maps API instead then have at it! -->					
					<iframe width="100%" height="400" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3655.067930239925!2d-46.58107208576615!3d-23.637738170300604!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce5cb6c8baf5a7%3A0x24fb537795849cc!2sFaculdade+de+Tecnologia+de+S%C3%A3o+Caetano+do+Sul!5e0!3m2!1spt-BR!2sbr!4v1472315780481" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                </div>
                <div class="col-md-6">
                    <p>Faculdade de Tecnologia de São Caetano do Sul Antônio Russo</p>
                    <p>Análise e Desenvolvimento de Sistemas.</p>
                    <p>Trabalho de Conclusão de Curso.</p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Nosso
                        <strong>Grupo</strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="/resources/img/gabi.jpg" width="750" height="450" alt="">
                    <h3>Gabrielle Rodrigues castelo
                        <small>Analista/Desenvolvedor</small>
                    </h3>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="/resources/img/gui.jpg" width="750" height="450" alt="">
                    <h3>Guilherme Augusto Ponte
                        <small>Analista/Desenvolvedor</small>
                    </h3>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="/resources/img/nati.jpg" width="750" height="450" alt="">
                    <h3>Natália Sousa de Jesus
                        <small>Analista/Desenvolvedor</small>
                    </h3>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

    </div>
    </form:form>
    <!-- /.container -->

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p> &copy; SiPEB 2016</p>
                </div>
            </div>
        </div>
    </footer>

 

</body>

</html>
