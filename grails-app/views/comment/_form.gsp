<%@ page import="cookeat.recipe.Comment" %>



<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="comment.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${cookeat.user.User.list()}" optionKey="id" required="" value="${commentInstance?.owner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'recipe', 'error')} required">
	<label for="recipe">
		<g:message code="comment.recipe.label" default="Recipe" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="recipe" name="recipe.id" from="${cookeat.recipe.Recipe.list()}" optionKey="id" required="" value="${commentInstance?.recipe?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'text', 'error')} required">
	<label for="text">
		<g:message code="comment.text.label" default="Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="text" required="" value="${commentInstance?.text}"/>
</div>

