<%@ page import="cookeat.recipe.Recipe" %>

<table class="table">
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'title', 'error')} required">
			<label for="title">Titre: <span class="required-indicator">*</span></label>
		</th>
		<td>
			<g:textField name="title" required="" value="${recipeInstance?.title}"/>
		</td>
	</tr>
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'description', 'error')} required">
			<label for="description">Description: <span class="required-indicator">*</span></label>
		</th>
		<td>
			<g:textField name="description" value="${recipeInstance?.description}" required=""/>
		</td>
	</tr>
	
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'picture', 'error')}">
			<label for="picture">Image:</label>
		</th>
		<td>
			<input type="file" id="picture" name="picture" />
		</td>
	</tr>
	
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'nbPeople', 'error')}">
			<label for="nbPeople">Nombre de personnes:</label>
		</th>
		<td>
			<g:field name="nbPeople" type="number" value="${recipeInstance.nbPeople}" />
		</td>
	</tr>
	
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'ingredients', 'error')} required">
			<label for="ingredients">Ingrédients: <span class="required-indicator">*</span></label>
		</th>
		<td>
			En cours
		</td>
	</tr>
	
	<tr>
		<th
			class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'recipe', 'error')} required">
			<label for="recipe">Recette: <span class="required-indicator">*</span></label>
		</th>
		<td>
			<g:textArea rows="10" cols="50" name="recipe" required="" value="${recipeInstance?.recipe}"/>
		</td>
	</tr>
</table>

<g:select style="display:none" id="owner" name="owner.id" from="${cookeat.user.User.list()}" optionKey="id" required="" value="${sec.loggedInUserInfo(field:'id')}" class="many-to-one"/>






