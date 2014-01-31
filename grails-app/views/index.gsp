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
	<div class="col-md-9" role="main">
		<div style="float: left">
			<ul class="nav nav-tabs">
					<li class="active"><a href="#">Dernières recettes</a></li>
					<li><a href="#">Top recettes</a></li>
				<sec:ifLoggedIn>
					<li><a href="#">Celles de mes amis</a></li>
				</sec:ifLoggedIn>
			</ul>
		</div>
	</div>
	</div>

</body>
</html>
