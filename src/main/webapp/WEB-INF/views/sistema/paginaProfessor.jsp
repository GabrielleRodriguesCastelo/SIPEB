
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html >

	
	

<head>

	<link href="<c:url value='/resources/css/bootstrap.css'  />" rel="stylesheet"/>
   	<link href="<c:url value='/resources/css/bootstrap.min.css'  />" rel="stylesheet"/>
	<link href="<c:url value='/resources/css/business-casual.css'  />" rel="stylesheet"/>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Administrador - SiPEB</title>
	
	<!-- Adicionar favicon - Ícone da aba das páginas -->
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
	<link rel="icon" href="favicon.ico" type="image/x-icon">
	
	<!-- Finaliza favicon -->

   

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<!-- jQuery -->


</head>

<body>

	<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

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
                    
                    
	 <form:form action="${spring:mvcUrl('montarPaginaProfessor').build()}"
					method="GET" commandName="sistema" role="paginaProfessor">
                </button>
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <a class="navbar-brand" href="index.html">SiPEB</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling Alterei abaixo -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li>
							<a href= ""../sistema/tipoAcesso"" style="color: #FF0000" >Sair</a>
						</li>
					</ul>
            </div>
			    
            </div>
        </nav>

    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12" align="center">
                    <hr>
                    <h2 class="intro-text text-center">Bem vindo 
                        <strong>Professor</strong>
                    </h2>
                    <hr>
                    
					<div class="row">
            <div class="box">
                <div class="col-lg-12">
                </div>
                <div class="col-sm-4 text-center">
                    <h3>
                        <small></small>
                    </h3>
					
					
                </div>
                <div class="col-sm-4 text-center">
                    <h3>
						<font size="3"><span class="glyphicon glyphicon-book"></span></font>
                        <small>Iniciar</small>
                    </h3>
					
						<a href="../turma/buscar" class="btn btn-primary btn-block"> Chamada </a><br>
						
					
                </div>
				
                <div class="col-sm-4 text-center">
                    <h3>
                        <small></small>
                    </h3>
					
					
                </div>
                <div class="clearfix"></div>
            </div>
        </div>	
                </div>
            </div>
        </div>

    </div>
    </form:form>
    <!-- /.container -->

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>SiPEB 2016</p>
                </div>
            </div>
        </div>
    </footer>

	
    

</body>

</html>
