
<%@ page import="cookeat.recipe.Recipe" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recipe.label', default: 'Recipe')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
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
