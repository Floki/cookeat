<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Cookeat</title>
</head>
<body>


	<div class="row">
	
		<sec:ifLoggedIn>
			<div class="col-md-3">
				<div class="bs-sidebar affix">
					<ul class="nav bs-sidenav jumbotron">
						<li><a href="#">Accueil</a></li>
						<li><a href="#">Profil</a></li>
						<li><a href="#">Mes recettes</a></li>
						<li><a href="#">Mes amis</a></li>
					</ul>
				</div>
			</div>
		</sec:ifLoggedIn>
		
		<div class="col-md-9">

			<ul class="nav nav-tabs">
				<li class="active"><a href="#">Dernières recettes</a></li>
				<li><g:link controller="Recipe" action="index">Top recettes</g:link></li>
				<sec:ifLoggedIn>
					<li><a href="#">Celles de mes amis</a></li>
				</sec:ifLoggedIn>
				<form class="navbar-form " role="search">
					<div class="input-group">

						<input type="text" class="form-control">
						<div class="input-group-btn">
							<button type="submit" class="btn btn-default" tabindex="-1">Rechercher</button>
							<button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
								<span id="dropdown_title">Titre</span>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu pull-right" id="searchParam" >
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
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c"
						in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link
								controller="${c.logicalPropertyName}">
								${c.fullName}
							</g:link></li>
					</g:each>
				</ul>
				
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
