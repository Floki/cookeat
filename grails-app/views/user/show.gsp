
<%@ page import="cookeat.user.User"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'user.label', default: 'User')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<g:render template="navBarUser" />

	<div id="show-user" class="content scaffold-show" role="main">
		<br />
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		
		<g:if test="${sec.loggedInUserInfo(field:'username') == userInstance.username}">
			<g:link class="create pull-left" action="edit" resource="${userInstance}">
				<button type="button" class="btn btn-success">Édition du
					profil</button>
			</g:link>
		</g:if>
		<br /><br/>
		<div class="row">
			<div class="col-md-4">
				<g:if test="${userInstance?.avatar}">
					<li class="fieldcontain"><span id="avatar-label"
						class="property-label"><g:message code="user.avatar.label"
								default="Avatar" /></span></li>
				</g:if>
				<g:else>
					<img class="avatar" height="150" width="150"
						src="https://cdn3.iconfinder.com/data/icons/pictofoundry-pro-vector-set/512/Avatar-512.png" />
				</g:else>
			</div>
			<div class="col-md-4 text-center">
				<h1>
					<g:fieldValue bean="${userInstance}" field="username" /> 
				</h1>
				( <g:fieldValue bean="${userInstance}" field="email" /> )
				<g:if test="${userInstance?.firstname}">
					<g:fieldValue
							bean="${userInstance}" field="firstname" /></span>
				</g:if>
				<g:if test="${userInstance?.lastname}">
					<g:fieldValue
							bean="${userInstance}" field="lastname" /></span>
				</g:if>
			</div>
		</div>
		<g:if test="${userInstance?.description}">
			<div class="row" style="margin-top:50px;">
				<div class="col-md-4">
					<h3 class="text-center">
						Votre description:
					</h3>
				</div>
				<div class="col-md-6">
					<h5>
						<br/><g:fieldValue bean="${userInstance}" field="description" />
					</h5>
				</div>
			</div>
		</g:if>
		<g:if test="${userInstance?.favoritesRecipes}">
			<div class="row" style="margin-top:50px;">
				<div class="col-md-4">
					<h3 class="text-center">
						Vos recettes favorites:
					</h3>
				</div>
				<div class="col-md-6">
				<table class="table">
				<tr>
					<th>Recette</th>
					<th>Description</th>
				</tr>
					<g:each in="${userInstance.favoritesRecipes}">
						<tr>
							<td>
								<g:link controller="recipe" action="show" id="${it.id}">
									${it.title}
								</g:link>
							</td>
							<td>
								${it.description }
							</td>
						</tr>
					</g:each>
					</table>
				</div>
			</div>
		</g:if>
		
		<g:if test="${userInstance?.comments}">
			<div class="row" style="margin-top:50px;">
				<div class="col-md-4">
					<h3 class="text-center">
						Vos commentaires
					</h3>
				</div>
				<div class="col-md-6">
				<table class="table">
				<tr>
					<th>Recette</th>
					<th>Commentaire</th>
				</tr>
					<g:each in="${userInstance.comments}">
						<tr>
							<td>
								<g:link controller="recipe" action="show" id="${it.recipe.id}">
									${it.recipe.title}
								</g:link>
								<g:if test="${sec.loggedInUserInfo(field:'username') == userInstance.username}">
									<g:link action="removeComment" params="[commentId:it.id, id: userInstance.id]">
				            			<button type="button" class="close" aria-hidden="true">&times;</button>
				          			</g:link>	
			         			 </g:if>
							</td>
							<td>
								${it.text}
							</td>
						</tr>
					</g:each>
					</table>
				</div>
			</div>
		</g:if>
		
		<g:if test="${userInstance?.ownerRecipes}">
			<div class="row" style="margin-top:10px;">
				<div class="col-md-4">
					<h3 class="text-center">
						Vos recettes:
					</h3>
				</div>
				<div class="col-md-6">
				<table class="table">
				<tr>
					<th>Recette</th>
				</tr>
					<g:each in="${userInstance.ownerRecipes}">
						<tr>
							<td>
								<g:link controller="recipe" action="show" id="${it.id}">
									${it.title}
								</g:link>
							</td>
						</tr>
					</g:each>
					</table>
				</div>
			</div>
		</g:if>
		
		<g:if test="${userInstance?.friends}">
			<div class="row" style="margin-top:10px;">
				<div class="col-md-4">
					<h3 class="text-center">
						Vos amis: 
					</h3>
				</div>
				<div class="col-md-6">
				<table class="table">
				<tr>
					<th>Amis</th>
				</tr>
					<g:each in="${userInstance.friends}">
						<tr>
							<td>
								<g:link controller="user" action="show" id="${it.id}">
									${it.username}
								</g:link>
							</td>
							<td>
								${it.firstname}
								${it.lastname}
							</td>
							<td>
								${it.email}
							</td>
						</tr>
					</g:each>
					</table>
				</div>
			</div>
		</g:if>
		
		<g:if test="${userInstance?.votes}">
			<div class="row" style="margin-top:10px;">
				<div class="col-md-4">
					<h3 class="text-center">
						Vos votes:
					</h3>
				</div>
				<div class="col-md-6">
				<table class="table">
				<tr>
					<th>Recette</th>
					<th>Vote</th>
				</tr>
					<g:each in="${userInstance.votes}">
						<tr>
							<td>
								<g:link controller="user" action="show" id="${it.recipe}">
									${it.recipe.title}
								</g:link>
							</td>
							<td>
								${it.rate}
							</td>
						</tr>
					</g:each>
					</table>
				</div>
			</div>
		</g:if>
	</div>
</body>
</html>
