
<%@ page import="cookeat.user.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
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
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<g:if test="${userInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="user.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${userInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="user.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${userInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.avatar}">
				<li class="fieldcontain">
					<span id="avatar-label" class="property-label"><g:message code="user.avatar.label" default="Avatar" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="user.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${userInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.firstname}">
				<li class="fieldcontain">
					<span id="firstname-label" class="property-label"><g:message code="user.firstname.label" default="Firstname" /></span>
					
						<span class="property-value" aria-labelledby="firstname-label"><g:fieldValue bean="${userInstance}" field="firstname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.lastname}">
				<li class="fieldcontain">
					<span id="lastname-label" class="property-label"><g:message code="user.lastname.label" default="Lastname" /></span>
					
						<span class="property-value" aria-labelledby="lastname-label"><g:fieldValue bean="${userInstance}" field="lastname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.accountExpired}">
				<li class="fieldcontain">
					<span id="accountExpired-label" class="property-label"><g:message code="user.accountExpired.label" default="Account Expired" /></span>
					
						<span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${userInstance?.accountExpired}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.accountLocked}">
				<li class="fieldcontain">
					<span id="accountLocked-label" class="property-label"><g:message code="user.accountLocked.label" default="Account Locked" /></span>
					
						<span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${userInstance?.accountLocked}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.comments}">
				<li class="fieldcontain">
					<span id="comments-label" class="property-label"><g:message code="user.comments.label" default="Comments" /></span>
					
						<g:each in="${userInstance.comments}" var="c">
						<span class="property-value" aria-labelledby="comments-label"><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.enabled}">
				<li class="fieldcontain">
					<span id="enabled-label" class="property-label"><g:message code="user.enabled.label" default="Enabled" /></span>
					
						<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${userInstance?.enabled}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.favoritesRecipes}">
				<li class="fieldcontain">
					<span id="favoritesRecipes-label" class="property-label"><g:message code="user.favoritesRecipes.label" default="Favorites Recipes" /></span>
					
						<g:each in="${userInstance.favoritesRecipes}" var="f">
						<span class="property-value" aria-labelledby="favoritesRecipes-label"><g:link controller="recipe" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.friends}">
				<li class="fieldcontain">
					<span id="friends-label" class="property-label"><g:message code="user.friends.label" default="Friends" /></span>
					
						<g:each in="${userInstance.friends}" var="f">
						<span class="property-value" aria-labelledby="friends-label"><g:link controller="user" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.ownerRecipes}">
				<li class="fieldcontain">
					<span id="ownerRecipes-label" class="property-label"><g:message code="user.ownerRecipes.label" default="Owner Recipes" /></span>
					
						<g:each in="${userInstance.ownerRecipes}" var="o">
						<span class="property-value" aria-labelledby="ownerRecipes-label"><g:link controller="recipe" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.passwordExpired}">
				<li class="fieldcontain">
					<span id="passwordExpired-label" class="property-label"><g:message code="user.passwordExpired.label" default="Password Expired" /></span>
					
						<span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${userInstance?.passwordExpired}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.votes}">
				<li class="fieldcontain">
					<span id="votes-label" class="property-label"><g:message code="user.votes.label" default="Votes" /></span>
					
						<g:each in="${userInstance.votes}" var="v">
						<span class="property-value" aria-labelledby="votes-label"><g:link controller="vote" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:userInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${userInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
