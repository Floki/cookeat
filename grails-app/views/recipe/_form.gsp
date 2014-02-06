<%@ page import="cookeat.recipe.Recipe" %>



<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="recipe.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${recipeInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="recipe.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${cookeat.user.User.list()}" optionKey="id" required="" value="${recipeInstance?.owner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'recipe', 'error')} required">
	<label for="recipe">
		<g:message code="recipe.recipe.label" default="Recipe" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="recipe" required="" value="${recipeInstance?.recipe}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'ingredients', 'error')} ">
	<label for="ingredients">
		<g:message code="recipe.ingredients.label" default="Ingredients" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'picture', 'error')} ">
	<label for="picture">
		<g:message code="recipe.picture.label" default="Picture" />
		
	</label>
	<input type="file" id="picture" name="picture" />
</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'nbPeople', 'error')} required">
	<label for="nbPeople">
		<g:message code="recipe.nbPeople.label" default="Nb People" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nbPeople" type="number" value="${recipeInstance.nbPeople}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="recipe.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${recipeInstance?.description}"/>
</div>

