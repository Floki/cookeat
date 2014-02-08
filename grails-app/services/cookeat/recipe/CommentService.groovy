package cookeat.recipe

//import com.sun.org.apache.xalan.internal.xsltc.compiler.ForEach;

import cookeat.user.User
import grails.transaction.Transactional

/**
 * User Service class
 * @author Exasky, Smail, Skaboy, Floki
 *
 */
@Transactional
class CommentService {

  def createComment(User owner, Recipe recipe, String text) {
		Comment comment = new Comment(owner: owner, recipe: recipe, text: text)
		return comment.save();
  }
	
	def readAllCommentOnRecipe(Recipe recipe){
		List<Comment> listComment = Comment.getAll()
		List<Comment> result = new ArrayList<Comment>()
		
		for (comment in listComment){
			if (comment.recipe == recipe)
				result.add(comment)
		}
		
		return result
	}
	
	def updateComment(Comment actual, String text){
		actual.text = text
		return actual.save()
	}
	
	def deleteComment(Comment comment){
		comment.delete flush: true
	}
}
