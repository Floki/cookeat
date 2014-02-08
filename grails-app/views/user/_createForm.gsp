<%@ page import="cookeat.user.User"%>

<table class="table">
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
			<label for="username">Nom d'utilisateur: <span class="required-indicator">*</span>
		</label>
		</th>
		<td><g:textField name="username" required=""
				value="${userInstance?.username}" /></td>

	</tr>
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
			<label for="email"> <g:message code="user.email.label"
					default="Email" /> <span class="required-indicator">*</span>
		</label>
		</th>
		<td><g:field type="email" name="email" required=""
				value="${userInstance?.email}" /></td>
	</tr>
<%--	<sec:ifNotLoggedIn >--%>
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
			<label for="password"> Mot de passe: <span class="required-indicator">*</span>
		</label>
		</th>
		<td><g:field type="password" name="password" required=""
				value="" /></td>
	</tr>	
<%--	</sec:ifNotLoggedIn>--%>
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: userInstance, field: 'description', 'error')} ">
			<label for="description"> <g:message
					code="user.description.label" default="Description" />
		</label>
		</th>
		<td><g:textField name="description"
				value="${userInstance?.description}" /></td>
	</tr>
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstname', 'error')} ">
			<label for="firstname"> <g:message
					code="user.firstname.label" default="Firstname" />

		</label>
		</th>
		<td><g:textField name="firstname"
				value="${userInstance?.firstname}" /></td>
	</tr>

	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastname', 'error')} ">
			<label for="lastname"> <g:message code="user.lastname.label"
					default="Lastname" />

		</label>
		</th>
		<td><g:textField name="lastname"
				value="${userInstance?.lastname}" /></td>
	</tr>
</table>