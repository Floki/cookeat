<%@ page import="cookeat.recipe.Recipe"%>
<%@ page import="cookeat.recipe.Comment"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'recipe.label', default: 'Recipe')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<g:render template="navBarRecipe"/>
	
	<div id="show-recipe" class="content scaffold-show" role="main">
		<br />
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>

		<g:link class="create pull-right" action="create">
			<button type="button" class="btn btn-primary">Créer une
				recette</button>
		</g:link>
		<g:if test="${sec.loggedInUserInfo(field:'username') == recipeInstance.owner.username}">
		
			<g:link class="create pull-left" action="edit" resource="${recipeInstance}">
				<button type="button" class="btn btn-success">Modifier cette
					recette</button>
			</g:link>
			<g:form url="[resource:recipeInstance, action:'delete']" method="DELETE">
				<g:actionSubmit class="delete btn btn-danger " action="delete"
					value="${message(code: 'Supprimer cette recette')}"
					onclick="return confirm('${message(code: 'Êtes vous sur de vouloir supprimer cette recette?')}');" />
			</g:form>
		</g:if>
			
		<div class="row">
			<div class="col-md-4">
				
				<g:if test="${recipeInstance?.picture}">
					<li class="fieldcontain"><span id="picture-label"
						class="property-label"></span></li>
				</g:if>
				<g:else>
					<img src="http://media.katu.com/designimages/amnw_recipe_icon.gif"/>
				</g:else>
			</div>
			<div class="col-md-4 text-center">
				<h1>
					<g:fieldValue bean="${recipeInstance}" field="title" />
				</h1>
				<span>
						Par: 
						<g:link controller="user" action="show" id="${recipeInstance?.owner?.id}">
							${recipeInstance?.owner?.username}
						</g:link>
						<g:if test="${recipeInstance?.nbPeople}">
							(<g:fieldValue bean="${recipeInstance}" field="nbPeople" />
										
							<g:if test="${recipeInstance?.nbPeople  > 1 }">
								Personnes
							</g:if>
							<g:else>
								Personne
							</g:else>
							)
						</g:if>
					</span> 
				<span class="glyphicon glyphicon-star"></span>
				<span class="glyphicon glyphicon-star"></span>
				<span class="glyphicon glyphicon-star"></span>
				<span class="glyphicon glyphicon-star"></span>
				<span class="glyphicon glyphicon-star-empty"></span>
			</div>
		</div>

		<div class="row" style="margin-top:50px;">
			<div class="col-md-4">
				<h3 class="text-center">
					Description: 
				</h3>
			</div>
			<div class="col-md-6">
				<br/><g:fieldValue bean="${recipeInstance}" field="description" />
			</div>
		</div>
		

		<div class="row" style="margin-top:50px">
			<div class="col-md-4">
				<h3 class="text-center">
					Ingrédients: 
				</h3>
			</div>

			<div class="col-md-6">
				<table class="table">
					<tr>
						<th>Ingrédient</th>
						<th>Valeur</th>
					</tr>
					<g:each in="${recipeInstance.ingredients}">
						<tr>
							<td>
								${it.key}
							</td>
							<td>
								${it.value}
							</td>
						</tr>
					</g:each>

				</table>
			</div>
			
			<div class="col-md-2"></div>
		</div>
		
		<div class="row" style="margin-top:50px">
			<div class="col-md-4">
				<h3 class="text-center">
					Recette: 
				</h3>
			</div>
			<div class="col-md-6">
				<g:fieldValue bean="${recipeInstance}" field="recipe" />
			</div>
		</div>

		<g:render template="commentList"/>

		<g:if test="${recipeInstance?.votes}">
			<li class="fieldcontain"><span id="votes-label"
				class="property-label"><g:message code="recipe.votes.label"
						default="Votes" /></span> <g:each in="${recipeInstance.votes}" var="v">
					<span class="property-value" aria-labelledby="votes-label"><g:link
							controller="vote" action="show" id="${v.id}">
							${v?.encodeAsHTML()}
						</g:link></span>
				</g:each></li>
		</g:if>
    
    <g:render template="addComment"/>
  
	</div>
</body>
</html>
