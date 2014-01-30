package cookeat.recipe



import cookeat.user.User
import spock.lang.*

/**
 *
 */
class RecipeServiceIntegrationSpec extends Specification {
	
	RecipeService recipeService
	VoteService voteService

    def setup() {
    }

    def cleanup() {
    }

    void "test create recipe"() {
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			
		when:
			Recipe recipe = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			
		then:
			recipe.title == "title" && recipe.recipe == "recipe" && user == user
			recipeService.createBaseRecipe("", "", new HashMap<String, String>(), user) == null
			recipeService.createRecipe("", "", "recipe", new HashMap<String, String>(), new byte[0], -1, user) == null
			recipeService.createRecipe("title", "", "", new HashMap<String, String>(), new byte[0], -1, user) == null
			recipeService.createRecipe("title", "", "recipe", new HashMap<String, String>(), new byte[0], -1, null) == null
			
    }
	
	void "test delete recipe"(){
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			Recipe recipe = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			recipe.save(failOnError:true)
			
		expect:
			Recipe.findAllByOwner(user)!=null
			
		when:
			recipeService.deleteRecipe(recipe, user)
			
		then:
		Recipe.findByOwnerAndRecipe(user, recipe)==null
	}
	
	void "test update recipe"(){
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			
		when:
			Recipe recipe = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			Map<String,String> map = new HashMap<String, String>()
			map.put("in1", "300gr")
			map.put("in2","200gr")
			byte[] picture = [0, 1, 0, 0, 1] as byte[]
			
		then:
			recipeService.updateDescripstionRecipe(recipe, "description")
			recipe.description == "description"
			recipeService.updateIngredientsRecipe(recipe, map)
			recipe.ingredients == map
			recipeService.updateNbPeopleRecipe(recipe, 10)
			recipe.nbPeople == 10
			recipeService.updatePictureRecipe(recipe, picture)
			recipe.picture.equals(picture)
			recipeService.updateRecipeRecipe(recipe, "newRecipe")
			recipe.recipe == "newRecipe"
			recipeService.updateTitleRecipe(recipe, "newTitle")
			recipe.title == "newTitle"
			recipeService.updatePictureRecipe(recipe, null)
	}
	
	void "test vote on recipe"(){
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			def user2 = new User(email: "test2@test.test", username: "test2", password: "test2" )
			user.save(failOnError : true)
			
		when:
			Recipe recipe = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			
		then:
			Vote vote = recipeService.voteOnRecipe(recipe, 0, user)
			recipe.votes.contains(vote)
			recipeService.voteOnRecipe(recipe, 0, user) == null
			
	}
	
	void "test comment on recipe"(){
		
		setup:
		def user = new User(email: "test@test.test", username: "test", password: "test" )
		user.save(failOnError : true)
		
		when:
		Recipe recipe = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
		
		then:
		Comment comment=recipeService.commentOnRecipe(recipe, "comment", user)
		recipe.comments.contains(comment)
	}
	
	void "test read all recipe"(){
		
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			
			Recipe recipe = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			recipe.save(failOnError:true)
			
			Recipe recipe1 = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			recipe1.save(failOnError:true)
			
			Recipe recipe2 = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			recipe2.save(failOnError:true)
		when:
			List<Recipe> actual=recipeService.readAllRecipe(user)
		
		then:
			actual.contains(recipe)==true
			actual.contains(recipe1)==true
			actual.contains(recipe2)==true
	}
}
