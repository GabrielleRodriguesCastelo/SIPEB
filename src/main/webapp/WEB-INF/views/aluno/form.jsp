
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="pt-br">

<head>

<!-- Adicionar favicon - Ícone da aba das páginas -->
	<link rel="shortcut icon" href="../resoufavicon.ico" type="image/x-icon">
	<link rel="icon" href="/resources/fonts/favicon.ico" type="image/x-icon">
		  
    
    
    <link href="<c:url value='/resources/css/bootstrap.css'  />" rel="stylesheet"/>
	<link href="<c:url value='/resources/css/business-casual.css'  />" rel="stylesheet"/>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    
    <link href="<c:url value='/resources/css/bootstrap-responsive.css'/>" rel="stylesheet"/>
    
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

	<!-- Funcionar Boostrap com jquery -->
	 <link href="<c:url value='/resources/css/bootstrap-responsive.css'/>" rel="stylesheet"/> 
	<!-- Ajuste para máscaras -->
	<script src="<c:url value='/resources/js/jquery-1.10.2.min.js' />"></script>
	
	<!-- Ajuste para máscaras -->
  	<script src="<c:url value='/resources/js/jquery.maskedinput.min.js' />"></script>

  <script type="text/javascript">
	jQuery(function($) {
	      $.mask.definitions['~']='[+-]';
		  $('#rg').mask('99.999.999-9');
		  $('#responsavelAlunoTelefone').mask('(99)9999-9999');
		  $('#cpf').mask('999.999.999-99');
		  $('#responsavelAlunoCpf').mask('999.999.999-99');
		  $('#enderecoCep').mask('99999-999');
	   });
	</script>
	
	<title>Sistema de presença escolar biométrico</title>

</head>

<body>



	
	<!--  <script src="../resources/js/jquery.maskedinput.js" type="text/javascript" ></script>-->
	<!--<script src="../resources/js/jquery.maskedinput.min.js" type="text/javascript" ></script>-->
	<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>




<script language="JavaScript">
		function limpar_form(){
			if(document.getElementById('ra').value!=""){
				document.getElementById('ra').value="";
				document.getElementById('nome').value="";
				document.getElementById('datanascimento').value="";
				document.getElementById('email').value="";
				document.getElementById('cpf').value="";
				document.getElementById('rg').value="";
				document.getElementById('responsavelAlunoCpf').value="";
				document.getElementById('responsavelAluno.nome').value="";
				document.getElementById('responsavelAluno.email').value="";
				document.getElementById('responsavelAluno.password').value="";
				document.getElementById('responsavelAlunoTelefone').value="";
				document.getElementById('endereco.rua').value="";
				document.getElementById('endereco.numero').value="";
				document.getElementById('endereco.complemento').value="";
				document.getElementById('endereco.bairro').value="";
				document.getElementById('endereco.cidade').value="";
				document.getElementById('endereco.estado').value="";
				document.getElementById('enderecoCep').value="";
			
			}
		}
	</script>

<!-- Adicionar favicon - Ícone da aba das páginas -->
	<link rel="shortcut icon" href="../resoufavicon.ico" type="image/x-icon">
	<link rel="icon" href="/resources/fonts/favicon.ico" type="image/x-icon">


    <div class="brand">SiPEB</div>
    <div class="address-bar">Sistema de Presença Escolar Biométrico</div>
	
    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Formulário
                        <strong>Aluno</strong>
                    </h2>
                    <hr>
					<div>
						<p align="center">Preencha corretamente os campos abaixo:</p>
					</div>
					<center>
					<c:if test="${mensagem != null}">
					<div  style="background-color:#A2CD5A" class="alert alert-success"  role="alert" ><p>${mensagem}</p></div>
					</c:if>
					
					<center>				
                    <form:form action="${spring:mvcUrl('cadastrarAluno').build()}"
					method="POST" commandName="aluno" role="form"> 
	 
					<form:hidden path="ra"/>
					
						<div class="row">
							<table name="tab_01">
								<tr>				
									<div class="form-group col-lg-4">
										<td> <br> <label for="ra"><spring:message code='views.aluno.form.ra' text='RA:'/></label></td>
										<td> <br> <form:input path="ra" type="text" class="form-control" disabled = "true" id="ra" /> </td>
										<td> <br><form:errors path="ra" /></td>
									</div>
									
									<td></td>
								</tr>
									
								<tr>
									
									<div class="form-group col-lg-4">
										<td> <br><label> Nome:</label></td>
										<td colspan="3"> <br><form:input path="nome" type="text" class="form-control" id="nome"  /></td>
										<td> <br><form:errors path="nome" /></td>
		
									</div>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										<td> <br><label>Data de Nascimento:</label>
										<td> <br>		<form:input path="dataNascimento" type="date"  size="15" class="form-control" id="dataNascimento"  /></td>
										
										<td> <br>		<form:errors path="dataNascimento" /></td>
									</div>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										<td> <br><label for="email"><spring:message code='views.aluno.form.email' text='Email:'/></label></td>
										<td colspan="3"> <br>		<form:input path="email" placeholder="email@exemplo.com" type="email" class="form-control" id="email"/></td>
										<td> <br>		<form:errors path="email" /></td>
									</div>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<div class="form-group col-lg-4">
										<td> <br><label for="cpf"><spring:message code='views.aluno.form.cpf' text='CPF:'/></label></td>
										<td> <br><form:input path="cpf" id="cpf"   type="text" class="form-control" maxlength="14" placeholder="Ex.: 000.000.000-00"  /></td>
										<td> <br><form:errors path="cpf" /></td>
									</div>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										<td> <br><label for="rg"><spring:message code='views.aluno.form.rg' text='RG:'/></label></td>
										<td> <br><form:input path="rg" type="text" class="form-control" maxlength="" placeholder="Ex.: 00.000.000-0" id="rg" /></td>
										<td> <br><form:errors path="rg" /></td>
									</div>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<div class="form-group col-lg-4">
										<td> <br> <label> <spring:message code='views.aluno.form.cpfResp' text='CPF Resp.:'/> </label> </td>
										
										<td><br><form:input  path="responsavelAluno.cpf" type="text" class="form-control" maxlength="14" placeholder="Ex.: 000.000.000-00" id="responsavelAlunoCpf"/></td>
										<td><br><form:errors path="responsavelAluno.cpf" /></td>
										<td> <br> <button  class="btn btn-default">Consultar</button> </td>
									</div>
									<td></td>
									
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										<td> <br> <label for="responsavelAluno.nome"><spring:message code='views.aluno.form.nomeResp' text='Nome Resp.:'/></label></td>
										<td colspan="3"> <br>	<form:input type="text" path="responsavelAluno.nome" class="form-control" id="responsavelAluno.nome"/></td>
										
										<td> <br>	<form:errors path="responsavelAluno.nome" /></td>
									</div>
									<td></td>
									
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										<td> <br> <label for="responsavelAluno.email"><spring:message code='views.aluno.form.emailResp' text='Email Resp.:'/></label></td>
										<td colspan="3"><br><form:input path="responsavelAluno.email" type="text" class="form-control" placeholder="email@exemplo.com" id="responsavelAluno.email"/></td>
										<td> <br><form:errors path="responsavelAluno.email" /></td>
									</div>
									<td></td>
									
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										<td> </td>
										<td> </td>
										<td> </td>
		
									</div>
									
									<div class="form-group col-lg-1">
										    
									</div>
									<td></td>
									
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										<td> <br> <label for="responsavelAluno.telefone"><spring:message code='views.aluno.form.telefoneResp' text='Telefone Resp.:'/></label></td>
										<td> <br>			<form:input path="responsavelAluno.telefone"   id="responsavelAlunoTelefone" type="text" class="form-control" placeholder="Ex.: (00) 00000-0000" /></td>
										<td> <br>			<form:errors path="responsavelAluno.telefone" /></td>
									</div>
									<td></td>
									
								</tr>
								
								
								<tr>
								
									<div class="form-group col-lg-4">
										<td> <br><label for="endereco.rua"><spring:message code='views.aluno.form.endereco.rua' text='Rua:'/></label></td>
											<td> <br>	<form:input path="endereco.rua" type="text" id="endereco.rua" class="form-control"/></td>
											<td> 	<form:errors path="endereco.rua" /></td>
											
											
										<td><br><label for="endereco.numero"><spring:message code='views.aluno.form.numero' text='Número:'/></label></td>
										<td><br> <form:input path="endereco.numero" type="text" id="endereco.numero" class="form-control"/></td>
										<td> 	<form:errors path="endereco.numero" /></td>
									</div>
									<div class="form-group col-lg-4">
										    
									</div>
									
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
										<td> <br> <label for="endereco.complemento"><spring:message code='views.aluno.form.endereco.complemento' text='Complemento: '/></label></td>
										<td> 	<form:input path="endereco.complemento" type="text" id ="endereco.complemento" class="form-control"/></td>
										<td> 	<form:errors path="endereco.complemento" /></td>
									</div>
								
									<div class="form-group col-lg-4">
										<td><br>  <label for="endereco.estado"><spring:message code='views.aluno.form.endereco.estado' text='Estado:'/></label></td>
										
										  <form:errors path="endereco.estado" />
										 <td><br><select name="estado_aluno"  id="endereco.estado" class="form-control"  >
												<option>AC </option>
												<option>AL </option>
												<option>AP </option>
												<option>AM </option>
												<option>BA </option>
												<option>CE </option>
												<option>DF </option>
												<option>ES </option>
												<option>GO </option>
												<option>MA </option>
												<option>MT </option>
												<option>MS </option>
												<option>MG </option>
												<option>PA </option>
												<option>PB </option>
												<option>PR </option>
												<option>PE </option>
												<option>PI </option>
												<option>RJ </option>
												<option>RN </option>
												<option>RS </option>
												<option>RO </option>
												<option>RR </option>
												<option>SC </option>
												<option>SP </option>
												<option>SE </option>
												<option>TO </option>
											</select>
											
										</td>
									</div>
									<div class="form-group col-lg-4">
										<td>  </td>
										<td> 	</td>
										<td> 	
									</div>
								</tr>
								<tr>
								<div class="form-group col-lg-4">
										
										<td> <br><label for="endereco.bairro"><spring:message code='views.aluno.form.endereco.bairro' text='Bairro:'/></label></td>
										<td><form:input path="endereco.bairro" type="text" class="form-control" id="endereco.bairro"/></td>
										<td><form:errors path="endereco.bairro" /></td>
									</div>
								<div class="form-group col-lg-4">
										<td> <br> <label for="endereco.cep"><spring:message code='views.aluno.form.cep' text='CEP:'/></label></td>
										<td> 	<form:input path="endereco.cep" type="text" id="enderecoCep" class="form-control" placeholder="Ex.: 00000-000"/></td>
										<td> 	<form:errors path="endereco.cep" /></td>
									</div>
								</tr>
								
								<tr>
									<div class="form-group col-lg-4">
									
										<td> <br><label for="endereco.cidade"><spring:message code='views.aluno.form.endereco.cidade' text='Cidade:'/></label></td>
										<td> <br>		<form:input path="endereco.cidade" id="endereco.cidade" type="text" class="form-control"/></td>
										<td> <br>		<form:errors path="endereco.cidade" /></td>
										
										
							
									
									</div>
								
									<div class="form-group col-lg-4">
										<td> <br> </td>
										
									</div>
									<td></td>
									<td></td>
								</tr>
								
								
							</table>
							
							<table name="tab_02">
								<tr>
									<div class="form-group col-lg-0">
										<td> <button type="submit" class="btn btn-success">Salvar</button> </td></button>
										

									</div>
									
									<div class="form-group col-lg-4">
										<td> <button type="reset" class="btn btn-primary" onClick="limpar_form()">Limpar</button> </td></button>
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
