
<%@ page import="cookeat.recipe.Recipe"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'recipe.label', default: 'Recipe')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>

	<g:render template="navBarRecipe"/>
	
	<div id="list-recipe" class="content scaffold-list" role="main">
		<br/>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:link class="create pull-right" action="create" style="margin-bottom:100px">
			<button type="button" class="btn btn-primary btn-lg">
				Créer une recette
			</button>
		</g:link>
		
		<g:each in="${recipeInstanceList}" status="i" var="recipeInstance">
			<div class="panel panel-primary" style="margin-top:65px">

				<div class="panel-heading">
					<g:link action="show" id="${recipeInstance.id}" style="color:white">
						${fieldValue(bean: recipeInstance, field: "title")}
					</g:link>
				</div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-9">
							${fieldValue(bean: recipeInstance, field: "description")}
						</div>
						<div class="col-md-3">
							${fieldValue(bean: recipeInstance, field: "picture")}
						</div>
					</div>
				</div>

				<div class="panel-footer"></div>
			</div>
		</g:each>

		<%--		<div class="pagination">--%>
		<%--			<g:paginate total="${recipeInstanceCount ?: 0}" />--%>
		<%--		</div>--%>
	</div>

</body>
</html>
