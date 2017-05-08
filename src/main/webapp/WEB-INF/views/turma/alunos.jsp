
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html >

<head>

	<link href="<c:url value='/resources/css/bootstrap.css'  />" rel="stylesheet"/>
	<link href="<c:url value='/resources/css/business-casual.css'  />" rel="stylesheet"/>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sistema de presença escolar biométrico</title>
    
    <!-- Adicionar favicon - Ícone da aba das páginas -->
	<link rel="shortcut icon" href="../resoufavicon.ico" type="image/x-icon">
	<link rel="icon" href="/resources/fonts/favicon.ico" type="image/x-icon">


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
	
	<script>		
			
			function excluir(idA, idT) {
			    var resposta = confirm("Deseja realmente excluir?");
			    if (resposta == true) {
			    	
			    	window.location.href = "./remover-aluno?idAluno="+ idA + "&idTurma=" + idT;
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
                    <h2 class="intro-text text-center">Inserir
                        <strong>Aluno na Turma ${turma.codigoTurma}</strong>
                    </h2>
					<form:form action="${spring:mvcUrl('inserirAluno').build()}"
					method="POST" commandName="combinedCommand">
					<form:hidden path="id"/>
                    <hr>
					<div>
						<p align="center">Inserir por CPF ou RA :</p>
						
					</div>
					<center><p> <c:if test="${mensagem != null}">
					<div  class="alert alert-warning"  role="alert" ><p>${mensagem}</p></div>
					
					
						
					</c:if> 
					</center>
						
					<center><p> <c:if test="${sms != null}">
					<div  class="alert alert-success"  role="alert" ><p>${sms}</p></div>
						
						</c:if> 
					</center>	
						<center><p> <c:if test="${mens != null}">
					<div  class="alert alert-danger"  role="alert" ><p>${mens}</p></div>
						
						</c:if>
						
					</center>
								
                    <form role="form">
                    <center>
						<div class="row">
							<table name="tab_01">
								<tr>
									<div class="form-group col-lg-">
										<td> <label> CPF: </label> </td>
										<td><form:input path="buscaAluno.cpf"  type="text" size="25" class="form-control"/></td>
										
										
										<td> <label> RA: </label> </td>
										<td><form:input path="buscaAluno.ra"  type="text" size="25" class="form-control"/></td>
										
										
										
										<td> <form:button class="btn btn-default" >Inserir Aluno</form:button> </td>		
										
						
								<tr>	
						<table>
						
					</form:form>
					
					<div class="row">
							
								
									<c:if test="${turma.alunos ne null and not empty turma.alunos}">
	  									<h2>Alunos</h2>
		  									<table class="table table-hover">
		    								<thead>
		      								<tr>
		    	    							<th>RA</th>
		        								<th>Nome</th>
		        								<th>CPF</th>
		        								<th>Responsável</th>
		        								<th>Excluir</th>
		      								</tr>
		    								</thead>
		    							<tbody>
		    								<c:forEach items="${turma.alunos}" var="aluno">
			      							<tr>
			        							<td>${aluno.ra }</td>
			        							<td>${aluno.nome }</td>
			        							<td>${aluno.cpf }</td>
			        							<td>${aluno.responsavelAluno.nome }</td>
			        							<td><button type="button" onClick="excluir('${aluno.ra}', '${turma.id}')"  class="btn btn-danger btn-xs"> Remover </button>  </td>
			        							
			        							
			      							</tr>
		    								</c:forEach>
		    							</tbody>
		  								</table>
					
								</c:if>
								<c:if test="${turma.alunos eq null or empty turma.alunos}">
								<div  class="alert alert-danger"  role="alert" ><p>Nenhum Aluno registrado</p></div>
								</c:if>
								
									
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
                        <a href="../turma/buscar">Voltar</a>
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
