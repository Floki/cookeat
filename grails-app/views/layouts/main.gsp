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
					 <a href="http://localhost:8080/cookeat">  
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
								<input class="btn btn-success" type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
								<button type="button" class="btn btn-primary">Inscription</button>
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
								<li><a href="#">Préférences</a></li>
								<li class="divider"></li>
								<li><g:link controller="logout">Déconnexion</g:link></li>
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
							<li><a href="#">Accueil</a></li>
							<li><a href="#">Profil</a></li>
							<li><a href="#">Mes recettes</a></li>
							<li><a href="#">Mes amis</a></li>
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
