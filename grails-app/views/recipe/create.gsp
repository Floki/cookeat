<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recipe.label', default: 'Recipe')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:render template="navBarRecipe"/>
		
		<div id="create-recipe" class="content scaffold-create" role="main">
		<g:form url="[resource:recipeInstance, action:'save']">
			
			
			<h1>
				Création d'une recette
				<g:submitButton name="create" class="save btn btn-success pull-right" value="Créer la recette" />
			</h1>

			<g:hasErrors bean="${recipeInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${recipeInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
				<fieldset class="form">
					<g:render template="createform"/>
				</fieldset>
				
		</g:form>
		</div>
	</body>
</html>
