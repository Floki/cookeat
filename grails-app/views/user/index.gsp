
<%@ page import="cookeat.user.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
					<button class="btn btn-default dropdown-toggle"
						data-toggle="dropdown">
						<span id="dropdown_title">Titre</span> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right" id="searchParam">
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
	
	
		<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-user" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'user.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'user.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="avatar" title="${message(code: 'user.avatar.label', default: 'Avatar')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'user.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="firstname" title="${message(code: 'user.firstname.label', default: 'Firstname')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userInstanceList}" status="i" var="userInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td>
					
						<td>${fieldValue(bean: userInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "avatar")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: userInstance, field: "firstname")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
