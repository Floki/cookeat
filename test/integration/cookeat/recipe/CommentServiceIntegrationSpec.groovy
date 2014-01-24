package cookeat.recipe



import cookeat.user.User
import spock.lang.*

/**
 *
 */
class CommentServiceIntegrationSpec extends Specification {

	CommentService commentService
	
    def setup() {
    }

    def cleanup() {
    }

    void "test create comment"() {
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			def recipe = new Recipe(ingredients: new HashMap<String, String>(), title: "title", owner: user, recipe: "test" )
			recipe.save(failOnError: true)
			def test = "ceci est un commentaire"
			
		expect:
			commentService.createComment(user, recipe, test)
			commentService.createComment(user, recipe, "") == null
    }
	
	void "test get all comment on one recipe"(){
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			
			def recipe1 = new Recipe(ingredients: new HashMap<String, String>(), title: "recipe1", owner: user, recipe: "recipe1" )
			def recipe2 = new Recipe(ingredients: new HashMap<String, String>(), title: "recipe2", owner: user, recipe: "recipe2" )
			recipe1.save(failOnError: true)
			recipe2.save(failOnError: true)
			
			def comment1 = new Comment(owner: user, recipe: recipe1, text: "commentaire1")
			def comment2 = new Comment(owner: user, recipe: recipe2, text: "commentaire2")
			comment1.save(failOnError : true)
			comment2.save(failOnError : true)
			
		when:
			List<Comment> listComment = commentService.readAllCommentOnRecipe(recipe1)
			
		then:
			listComment.contains(comment1) == true
			listComment.contains(comment2) == false
	}
	
	void "test update comment"(){
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			
			def recipe = new Recipe(ingredients: new HashMap<String, String>(), title: "recipe1", owner: user, recipe: "recipe1" )
			recipe.save()
			
			def comment = new Comment(owner: user, recipe: recipe, text: "hoho")
			comment.save()
			
		when:
			commentService.updateComment(comment, "hihi")
			
		then:
			comment.text == "hihi"
	}
	
	void "test delete comment"(){
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			
			def recipe = new Recipe(ingredients: new HashMap<String, String>(), title: "recipe1", owner: user, recipe: "recipe1" )
			recipe.save()
			
			def comment = new Comment(owner: user, recipe: recipe, text: "hoho")
			comment.save()
			
		when:
			commentService.deleteComment(comment)
			
		then:
			Comment.getAll().empty == true
	}
}
