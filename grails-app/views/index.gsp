<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Cookeat</title>
</head>
<body>


	<div class="row">
		<sec:ifLoggedIn>
			<div class="col-md-3 jumbotron">
				<a href="#">Accueil</a><br /> 
				<a href="#">Profil</a><br /> 
				<a href="#">Mes recettes</a><br /> 
				<a href="#">Mes amis</a><br />
		</sec:ifLoggedIn>
	</div>
	<div class="col-md-9">

		<ul class="nav nav-tabs">
			<li class="active"><a href="#">Dernières recettes</a></li>
			<li><g:link controller="Recipe" action="show" id="1">Top recettes</g:link></li>
			<sec:ifLoggedIn>
				<li><a href="#">Celles de mes amis</a></li>
			</sec:ifLoggedIn>
			<form class="navbar-form " role="search">
				<div class="input-group">
				
					<input type="text" class="form-control">
					<div class="input-group-btn">
						<button type="submit" class="btn btn-default" tabindex="-1">Rechercher</button>
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" tabindex="-1">
							Titre <span class="caret"></span> 
							<span class="sr-only">Toggle Dropdown</span>
						</button>
						<ul class="dropdown-menu pull-right" role="menu">
							<li><a href="#">Titre</a></li>
							<li><a href="#">Description</a></li>
							<li><a href="#">Temps de préparation</a></li>
							<li class="divider"></li>
							<li><a href="#">Nombre de personnes</a></li>
						</ul>
					</div>
				</div>
			</form>
		</ul>
	</div>
	</div>
	</div>

</body>
</html>
