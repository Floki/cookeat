<%@ page import="cookeat.recipe.Recipe" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recipe.label', default: 'Recipe')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:render template="navBarRecipe"/>
		
		<div id="edit-recipe" class="content scaffold-edit" role="main">
		<g:form url="[resource:recipeInstance, action:'update']" method="POST" >
			<h1>
				Édition de la recette
				<g:actionSubmit class="save btn btn-success pull-right" action="update" value="Mise à jour" />
			</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${recipeInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${recipeInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
				<g:hiddenField name="version" value="${recipeInstance?.version}" />
				<fieldset class="form">
					<g:render template="createform"/>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
