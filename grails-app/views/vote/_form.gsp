<%@ page import="cookeat.recipe.Vote" %>



<div class="fieldcontain ${hasErrors(bean: voteInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="vote.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${cookeat.user.User.list()}" optionKey="id" required="" value="${voteInstance?.owner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: voteInstance, field: 'recipe', 'error')} required">
	<label for="recipe">
		<g:message code="vote.recipe.label" default="Recipe" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="recipe" name="recipe.id" from="${cookeat.recipe.Recipe.list()}" optionKey="id" required="" value="${voteInstance?.recipe?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: voteInstance, field: 'rate', 'error')} required">
	<label for="rate">
		<g:message code="vote.rate.label" default="Rate" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="rate" type="number" min="0" max="5" value="${voteInstance.rate}" required=""/>
</div>

