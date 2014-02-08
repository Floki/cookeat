<div id="create-comment" class="content scaffold-create" role="main">
	<div class="form-group">
		<label for="addComment">Ajouter un commentaire :</label>
		<textarea class="form-control" id="addComment" placeholder="Commentaire" rows="3">
		</textarea>
		<g:link action="addComment" params="[comment:'TEST', id: recipeInstance.id]">
			<button type="button" class="btn btn-primary">Envoyer</button>
		</g:link>
	</div>
</div>
