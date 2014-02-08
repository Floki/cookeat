<g:if test="${recipeInstance?.comments}">
	<ul class="list-group">
		<g:each in="${recipeInstance.comments}" var="c">
			<li class="list-group-item">
			 <g:link controller="user" action="show" id="${c.owner?.id}">
			   <h4>${c.owner?.username}</h4>
		   </g:link>
				${c.text}
			</li>
		</g:each>
	</ul>
</g:if>