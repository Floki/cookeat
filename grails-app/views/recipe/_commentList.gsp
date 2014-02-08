<g:if test="${recipeInstance?.comments}">
	<ul class="list-group">
		<g:each in="${recipeInstance.comments}" var="c">
			<li class="list-group-item">
			 <g:link controller="user" action="show" id="${c.owner?.id}">
			   <h4>${c.owner?.username}</h4>
		   </g:link>
				${c.text}
				<g:if test="${sec.loggedInUserInfo(field:'username') == c.owner.username}">
				  <g:link action="removeComment" params="[commentId:c.id, id: recipeInstance.id]">
				    <button type="button" class="close" aria-hidden="true">&times;</button>
			    </g:link>
			  </g:if>
			</li>
		</g:each>
	</ul>
</g:if>