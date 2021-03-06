<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>CookEat</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<r:require modules="bootstrap" />
	
	<r:layoutResources />
	<link type="text/css" href="${resource(dir: 'css', file: 'dashboard.css')}" />
	<style>
  		.list-group ul li a{ 
  			color:#FFFFFF
  		}
		body {
  			padding-top: 100px;
		}
	</style>
</head>
<body>

	<div class="navbar navbar-fixed-top text-center">
		<div class="panel panel-warning">
			<div class="row">
				<div class="col-md-3">
					<div id="grailsLogo">
					 <a href="${createLink(uri: '/')}">  
             			 <img src="${resource(dir: 'images', file: 'logo-mini.png')}" alt="Cook'Eat" height="75px"/>	  
					 </a>
					</div>
				</div>
				
				<sec:ifNotLoggedIn>
				<div class="col-md-9" style="line-height:5">
						<form class="navbar-form" action='cookeat/j_spring_security_check' method='POST' id='loginForm' class='cssform' autocomplete='off'>
							<div class="form-group">
								<input type='text' class='text_ form-control' name='j_username' id='username' placeholder="Utilisateur"/>
							</div>
							<div class="form-group">
								<input type='password' class='text_ form-control' name='j_password' id='password' placeholder="Mot de passe"/>
							</div>
							<input class="btn btn-success" type='submit' id="connexion" value='${message(code: "springSecurity.login.button")}'/>
							<g:link controller="User" class="create btn btn-primary" action="create">Inscription</g:link>
						</form>
							
						</div>
				</sec:ifNotLoggedIn >
				<sec:ifLoggedIn>
				<div class="col-md-6" style="line-height:5"></div>
				<div class="col-md-2" style="line-height:5">
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							Bienvenue <sec:loggedInUserInfo field="username"/> <span class="caret"></span> 
							<span class="sr-only">Toggle Dropdown</span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><g:link controller="User" action="show" id="${sec.loggedInUserInfo(field:'id')}">Préférences</g:link></li>
							<li class="divider"></li>
							<li><g:link controller="logout" id="deconnexion">Déconnexion</g:link></li>
						</ul>
					</div>
					</div>
				</sec:ifLoggedIn>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
		
			<sec:ifLoggedIn>
				<div class="col-md-3 ">
					<div class="bs-sidebar affix ">
					<div class="list-group jumbotron" style="background-color:#428bca;">
						<ul class="nav bs-sidenav ">
							<li><a href="${createLink(uri: '/')}">Accueil</a></li>
							<li><g:link controller="User" action="show" id="${sec.loggedInUserInfo(field:'id')}">Profil</g:link></li>
							<li><g:link controller="User" action="show" id="${sec.loggedInUserInfo(field:'id')}">Mes recettes</g:link></li>
							<li><g:link controller="User" action="show" id="${sec.loggedInUserInfo(field:'id')}">Mes amis</g:link></li>
						</ul>
						</div>
					</div>
				</div>
			</sec:ifLoggedIn>
			<div class="col-md-9 ">
				<g:layoutBody />
				<r:layoutResources />
			</div>
		</div>
	</div>
	<script>
		$(".dropdown-menu li a").click(function(){
			$("#dropdown_title").html($(this).text());
		});
	</script>
</body>
</html>
