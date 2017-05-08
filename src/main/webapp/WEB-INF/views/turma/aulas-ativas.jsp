
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<link href="<c:url value='/resources/css/bootstrap.css'  />"
	rel="stylesheet" />
<link href="<c:url value='/resources/css/business-casual.css'  />"
	rel="stylesheet" />

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Sistema de presença escolar biométrico</title>

<!-- Adicionar favicon - Ícone da aba das páginas -->
	<link rel="shortcut icon" href="../resoufavicon.ico" type="image/x-icon">
	<link rel="icon" href="/resources/fonts/favicon.ico" type="image/x-icon">


<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
	rel="stylesheet" type="text/css">


</head>

<body>

<!-- Adicionar favicon - Ícone da aba das páginas -->
	<link rel="shortcut icon" href="../resoufavicon.ico" type="image/x-icon">
	<link rel="icon" href="/resources/fonts/favicon.ico" type="image/x-icon">

	<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<div class="brand">SiPEB</div>
	<div class="address-bar">Sistema de Presença Escolar Biométrico</div>

	<div class="container">

		<div class="row">
			<div class="box">
				<div class="col-lg-12">
					<hr>
					<h2 class="intro-text text-center">
						<strong>Aulas Ativas</strong>
					</h2>
					<center>
						<c:if test="${empty presencas}">
							<p>Nenhuma aula ativa no momento.</p>
						</c:if>
					</center>

					<c:if test="${not empty presencas}">
						<table class="table">
							<thead>
								<tr>
									<th>Data Inicio</th>
									<th>Professor</th>
									<th>Matéria</th>
									<th>Visualizar</th>
								</tr>
							</thead>

							<c:forEach items="${presencas}" var="presenca">
								<tr>
									
									<td></td>
									<td>${presenca.data}</td>
									<td>${presenca.aula.professor.nome}</td>
									<td>${presenca.aula.materia.nome}</td>
									<td><a href="./visualizar?idPresenca=${presenca.id}">visualizar</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
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
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
			<a class="navbar-brand" href="index.html">SiPEB</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>


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
