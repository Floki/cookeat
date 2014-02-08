<g:form method="post" controller="recipe" action="addComment">
	<div id="create-comment" class="content scaffold-create" role="main">
		<div class="form-group">
			<label for="addComment">Ajouter un commentaire :</label>
			<textarea row="3" class="form-control" name="comment"></textarea> 

			<g:hiddenField name="id" value="${recipeInstance.id}" />
				<button type="submit" class="btn btn-primary">Envoyer</button>
		</div>
	</div>
</g:form>
