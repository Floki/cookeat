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

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="recipe.comments.label" default="Comments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${recipeInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comment" action="create" params="['recipe.id': recipeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: recipeInstance, field: 'votes', 'error')} ">
	<label for="votes">
		<g:message code="recipe.votes.label" default="Votes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${recipeInstance?.votes?}" var="v">
    <li><g:link controller="vote" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="vote" action="create" params="['recipe.id': recipeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'vote.label', default: 'Vote')])}</g:link>
</li>
</ul>

</div>

