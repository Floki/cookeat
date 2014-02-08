<%@ page import="cookeat.user.User"%>

	<g:render template="createForm"></g:render>
	<table class="table">
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: userInstance, field: 'favoritesRecipes', 'error')} ">
			<label for="favoritesRecipes"> <g:message
					code="user.favoritesRecipes.label" default="Favorites Recipes" />

		</label>
		</th>
		<td>
			<table>
				<g:each in="${userInstance?.favoritesRecipes?}" var="f">
					<tr>
						<td><g:link controller="recipe" action="show" id="${f.id}">
								${f?.encodeAsHTML()}
							</g:link></td>
					</tr>
				</g:each>
			</table>
		</td>
	</tr>
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: userInstance, field: 'friends', 'error')} ">
			<label for="friends"> <g:message code="user.friends.label"
					default="Friends" />

		</label>
		</th>
		<td>
			<table class="table">
				<g:each in="${userInstance?.friends?}" var="friend">
					<tr>
						<td><g:link controller="recipe" action="show" id="${friend.id}">
								${friend.username}
							</g:link></td>
						<td>
							<a href="#" style="color:red">X</a>
						</td>
					</tr>
				</g:each>
			</table>
		</td>
	</tr>
</table>