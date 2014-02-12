<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Cookeat</title>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><g:link controller="User" action="index">Dernières recettes</g:link></li>
		<li><g:link controller="Recipe" action="index">Top recettes</g:link></li>
		<sec:ifLoggedIn>
			<li><a href="#">Celles de mes amis</a></li>
		</sec:ifLoggedIn>
		<form class="navbar-form " role="search">
			<div class="input-group">

				<input type="text" class="form-control" onfocus="this.value ='';" value="Effectuez une recherche">
				<div class="input-group-btn">
					<button type="submit" class="btn btn-default" tabindex="-1">Rechercher</button>
					<button class="btn btn-default dropdown-toggle"
						data-toggle="dropdown">
						<span id="dropdown_title">Titre</span> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right" id="searchParam">
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
	
	<div id="controller-list" role="navigation">
		<h2>Bienvenue sur CookEat !</h2>
		<h4>La référence des recettes en ligne!</h4>
		<sec:ifLoggedIn>
			Vous êtes maintenant connecté, bravo :)
		</sec:ifLoggedIn>

	</div>

</body>
</html>
