<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

	
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sistema de presença escolar biométrico</title>
    
    <!-- Adicionar favicon - Ícone da aba das páginas -->
	<link rel="shortcut icon" href="../resoufavicon.ico" type="image/x-icon">
	<link rel="icon" href="/resources/fonts/favicon.ico" type="image/x-icon">
    
    <link href="<c:url value='/resources/css/bootstrap.css'  />" rel="stylesheet"/>
	<link href="<c:url value='/resources/css/business-casual.css'  />" rel="stylesheet"/>

    

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
	
	
	<script language="JavaScript">
		function limpar_form(){
			if(document.getElementById('nome').value!=""){
				document.getElementById('nome').value="";
				document.getElementById('sigla').value="";
				document.getElementById('planoDeEnsino').value="";
			}
		}
	</script>
	
    <div class="brand">SiPEB</div>
    <div class="address-bar">Sistema de Presença Escolar Biométrico</div>

    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Formulário
                        <strong>Matéria</strong>
                    </h2>
					
                    <hr>
                    
                    <center><c:if test="${mensagem != null}">
						<div  style="background-color:#A2CD5A" class="alert alert-success"  role="alert" ><p>${mensagem}</p></div>
					</c:if> </center>
					
                    <p align="center">Preencha corretamente os campos abaixo:</p>
					<center>					
                    
					<form:form role="form" action="${spring:mvcUrl('cadastrarMateria').build()}"
						method="POST" commandName="materia">
					<form:hidden path="id" />
						<div class="row">
							<table>
								<tr>
									<div class="form-group col-lg-12">
										<td> <label> Nome: </label> </td>
										<td> <form:input path="nome" id="nome" class="form-control"/> </td>
										<td> <form:errors path="nome" /></td>
									</div>															
								</tr>
								
								<tr>
									<div class="form-group col-lg-12">
										<td> <br> <label> Sigla: </label> </td>	
										<td> <br> <form:input path="sigla" id="sigla" class="form-control"/> </td>
										<td><br> <form:errors path="sigla" /></td>
									</div>																
								</tr>
								
								<tr>
									<div class="form-group col-lg-12">
										<td colspan="2"> <br> <label> Plano de ensino: </label> </td>
										<td> 
									</div>							
								</tr>
								
								<tr>
									<div class="form-group col-lg-12">
										<td colspan="2"> <form:textarea id="planoDeEnsino" path="planoDeEnsino" rows="5" cols="30" /> </td>
										<td><form:errors path="planoDeEnsino" /> </td>
									</div>									
								</tr>
							</table>
							
							<table name="tab_02">
								<tr>
								
									<div class="form-group col-lg-4">
										<td> <button type="submit" class="btn btn-success">Salvar</button> </td>
								
								
									<div class="form-group col-lg-4">
										<td> <button type="reset" class="btn btn-primary" onClick="limpar_form()">Limpar</button> </td>
									</div>
								
								</tr>
							</table>
						</div>
                    </form:form>
					</center>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container -->
	
	
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
                </button>
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <a class="navbar-brand" href="index.html">SiPEB</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="../sistema/paginaAdm">Voltar</a>
                    </li>

                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

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
