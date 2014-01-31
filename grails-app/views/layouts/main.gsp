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
					<a href="http://localhost:8080/cookeat"> 
						<img src="${resource(dir: 'images', file: 'logo.gif')}" alt="Grails" />
					</a>
				</div>
				<sec:ifNotLoggedIn>
					<div class="col-md-9" style="line-height:5">
						<form class="navbar-form" role="form">
							<div class="form-group">
								<input type="text" placeholder="Utilisateur" class="form-control">
							</div>
							<div class="form-group">
								<input type="password" placeholder="Mot de passe"
									class="form-control">
							</div>
							<button type="submit" class="btn btn-success">Connexion</button>
							<button type="button" class="btn btn-primary">Inscription</button>
						</form>
					</div>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
				
				</sec:ifLoggedIn>
		</div>
		</div>
	</div>


	<div class="container">
		<g:layoutBody />
		<r:layoutResources />
	</div>
</body>
</html>
