package cookeat.recipe



import cookeat.user.User
import spock.lang.*

/**
 *
 */
class RecipeServiceIntegrationSpec extends Specification {
	
	RecipeService recipeService

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
			recipeService.createRecipe("", "", "recipe", new HashMap<String, String>(), new Byte[0], -1, user) == null
			recipeService.createRecipe("title", "", "", new HashMap<String, String>(), new Byte[0], -1, user) == null
			recipeService.createRecipe("title", "", "recipe", new HashMap<String, String>(), new Byte[0], -1, null) == null
			
    }
}
