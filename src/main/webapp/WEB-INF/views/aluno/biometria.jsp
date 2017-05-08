
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="pt-br">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Inserir digitais - SiPEB</title>
    
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

    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12" align="center">
                    <hr size="50">
                    <h2 class="intro-text text-center">
                        <strong>Cadastro das digitais do(a) Aluno(a) ${aluno.nome}</strong>
                    </h2>
                    <hr>
					<br>
						<!-- Comentario
						<div class="row">
							<div class="col-md-9"> inserir aqui </div>
							<div class="col-md-9 col-md-offset-9"> inserir aqui </div>
						</div>
						-->
						
						<table width="300px" >
							<center>
					<c:if test="${mensagem != null}">
					<div  style="background-color:#A2CD5A" class="alert alert-success"  role="alert" ><p>${mensagem}</p></div>
					</c:if>
							<tr>
								<div> 
									<td> <img src="/resources/img/digital2.png"   /> </td>
								</div>
								
								<div>
									<td> <img src="/resources/img/digital2.png"  /> </td> 
								</div>
							</tr>
							
							<tr>
								<div> 
									<td>  <c:if test="${digitalUm eq true}">
										<center><b>A digital 1 já está cadastrada para o aluno ${aluno.nome}</td></center>
										</c:if> </td>
								</div> 
								<div> 
									<td>  <c:if test="${digitalDois eq true}">
										<center><b>A digital 2 já está cadastrada para o aluno ${aluno.nome}</td></center>
										</c:if> </td>
									
							</tr>
							
							
							<tr>
								<div> 
									<td>  <c:if test="${digitalUm eq false}">
									<center> <a href="/aluno/cadastrar/biometria/digitalum?id=${aluno.ra}" type="button" class="btn btn-primary" value="Inserir digital 1" /> Inserir Digital 1</td></a> </td>
								</c:if> </td>
								</div> 
								</div> 
								<div> 
									<td>  <c:if test="${digitalDois eq false}">
									<center> <a href="/aluno/cadastrar/biometria/digitaldois?id=${aluno.ra}" type="button" class="btn btn-primary" value="Inserir digital 1" /> Inserir Digital 2</a> </td>
								</td>
								</div> 
								</c:if> 
							</tr>	
									
									
								
								
								</tr>	
								
						</table>
                </div>
            </div>
        </div>
		<nav class="navbar navbar-default" role="navigation">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li>
							<a href="../buscar">Voltar</a>
						</li>
	
					</ul>
			</div>
		</nav>
    </div>



</body>

</html>
