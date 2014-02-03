
<%@ page import="cookeat.recipe.Recipe" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recipe.label', default: 'Recipe')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	
			<ul class="nav nav-tabs">
				<li><g:link controller="User" action="index">Dernières recettes</g:link></li>
				<li class="active"><g:link controller="Recipe" action="index">Top recettes</g:link></li>
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
	
	
	
	
		<a href="#list-recipe" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-recipe" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'recipe.title.label', default: 'Title')}" />
					
						<th><g:message code="recipe.owner.label" default="Owner" /></th>
					
						<g:sortableColumn property="recipe" title="${message(code: 'recipe.recipe.label', default: 'Recipe')}" />
					
						<g:sortableColumn property="ingredients" title="${message(code: 'recipe.ingredients.label', default: 'Ingredients')}" />
					
						<g:sortableColumn property="picture" title="${message(code: 'recipe.picture.label', default: 'Picture')}" />
					
						<g:sortableColumn property="nbPeople" title="${message(code: 'recipe.nbPeople.label', default: 'Nb People')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${recipeInstanceList}" status="i" var="recipeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${recipeInstance.id}">${fieldValue(bean: recipeInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: recipeInstance, field: "owner")}</td>
					
						<td>${fieldValue(bean: recipeInstance, field: "recipe")}</td>
					
						<td>${fieldValue(bean: recipeInstance, field: "ingredients")}</td>
					
						<td>${fieldValue(bean: recipeInstance, field: "picture")}</td>
					
						<td>${fieldValue(bean: recipeInstance, field: "nbPeople")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${recipeInstanceCount ?: 0}" />
			</div>
			</div>

	</body>
</html>
