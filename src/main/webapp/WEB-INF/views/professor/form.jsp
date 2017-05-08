<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="pt-br">

<head>
	<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"/>
	<link href="<c:url value='/resources/css/business-casual.css'  />" rel="stylesheet"/>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
		<!-- Funcionar Boostrap com jquery -->
	 <link href="<c:url value='/resources/css/bootstrap-responsive.css'/>" rel="stylesheet"/> 
	<!-- Ajuste para máscaras -->
	<script src="<c:url value='/resources/js/jquery-1.10.2.min.js' />"></script>
	
	<!-- Ajuste para máscaras -->
  	<script src="<c:url value='/resources/js/jquery.maskedinput.min.js' />"></script>

  <script type="text/javascript">
	jQuery(function($) {
	      $.mask.definitions['~']='[+-]';

		  $('#cpf').mask('999.999.999-99');
	   });
	</script>
    

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <title>Sistema de presença escolar biométrico</title>
    
    <!-- Adicionar favicon - Ícone da aba das páginas -->
	<link rel="shortcut icon" href="../resoufavicon.ico" type="image/x-icon">
	<link rel="icon" href="/resources/fonts/favicon.ico" type="image/x-icon">
    

</head>

<body>
	
	<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
			
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<script language="JavaScript">
		function limpar_form(){
			if(document.getElementById('id').value!=""){
				document.getElementById('id').value="";
				document.getElementById('nome').value="";
				document.getElementById('cpf').value="";
				document.getElementById('dataNascimento').value="";
				document.getElementById('email').value="";
				document.getElementById('password').value="";
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
                        <strong>Professor</strong>
                    </h2>
                    <hr>
                    <p align="center">Preencha corretamente os campos abaixo:</p>
                    
					<center>
					<c:if test="${mensagem != null}">
					<div  style="background-color:#A2CD5A" class="alert alert-success"  role="alert" ><p>${mensagem}</p></div>
					</c:if> 
					
                    
                    <form:form action="${spring:mvcUrl('cadastrarProfessor').build()}"
					method="POST" commandName="professor">
					<form:hidden path="id"/>
						<div class="row">
						
							<table>
								<tr>
									<div class="form-group col-lg-1">
										<td><br><label for="id"><spring:message code='views.professor.form.id' text='ID:'/></label></td>
										<td><br><form:input path="id" type ="text" class="form-control" disabled = "true" id="id"/></td>
										<td><br><form:errors path="id" /></td>
									</div>
										<td> </td>
										<td> </td>
										<td> </td>
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
									<td>
									<td>
									<td>

										
									</div>
							
								</tr>
								
								<tr>
									
									<div class="form-group col-lg-4">
										<td><br><label for="nome"><spring:message code='views.professsor.form.name' text='Nome:' /></label></td>
										<td colspan="3"><br><form:input path="nome" type ="text" class="form-control" id="nome"/></td>
										<td><br><form:errors path="nome" />	</td>
									</div>
	
								</tr>
								
								<tr>
									
									<div class="form-group col-lg-4">
										<td><br><label for="cpf"><spring:message code='views.professor.form.cpf' text='Cpf:'/></label></td>
										<td><br><form:input path="cpf" type ="text" id="cpf" class="form-control" maxlength="14" placeholder="Ex.: 000.000.000-00"/></td>
										<td><br><form:errors path="cpf" /></td>
									</div>
									<td> </td>
									<td> </td>
									<td> </td>
	
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										<td><br><label for="dataNascimento"><spring:message code='views.professor.form.datanasc' text='Data Nascimento:'/></label></td>
										<td><br> <form:input path="dataNascimento" id="dataNascimento" type="date"  class="form-control"/></td>
										<td><br> <form:errors path="dataNascimento" /></td>
									</div>
									<td> </td>
									<td> </td>
									<td> </td>
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										<td><br><label for="email"><spring:message code='views.professor.form.email' text='Email'/></label></td>
										<td colspan="3"><br><form:input path="email" id="email" placeholder="email@exemplo.com" type="email"  class="form-control"/></td>
										<td><br><form:errors path="email" /></td>
									</div>
									
								</tr>
								
								
								
								<tr>
									<div class="form-group col-lg-4">
										<td><br><label for="password"><spring:message code='views.professor.form.password' text='Senha:'/></label></td>
										<td><br><form:input path="password" type="password"  id="password" class="form-control" /></td>
										<td><br><form:errors path="password" /></td>
									</div>								
								</tr>
								
								
								<tr>
									<div class="form-group col-lg-4">
										
										
									</div>								
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										
										
									</div>								
								</tr>
								<tr>
																	
								</tr>
													
								
							</table>
							
							<table name="tab_02">
								<tr>
									<div class="form-group col-lg-0">
										<td> <button type="submit" class="btn btn-success">Salvar</button> </td>
									</div>
								
									<div class="form-group col-lg-4">
										<td> <button type="reset" class="btn btn-primary" onClick="limpar_form">Limpar</button> </td>
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
