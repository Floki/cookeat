package cookeat.recipe



import java.util.Map;

import cookeat.user.User
import cookeat.user.UserService
import spock.lang.*

/**
 *
 */
class RecipeServiceIntegrationSpec extends Specification {
	
	UserService userService
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
			def user2 = new User(email: "test2@test.test", username: "test2", password: "test2" )
			user2.save(failOnError : true)
			Recipe recipe = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			recipe.save(failOnError:true)
			
		expect:
			Recipe.findAllByOwner(user)!=null
			
		when:
			recipeService.deleteRecipe(recipe, user)
			
		then:
			recipeService.deleteRecipe(recipe, user2) == false
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
	
	void "test recipe search"(){
		setup:
			User user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true);
			Recipe recipe1 = new Recipe(title : 		 "omelette au fromage", 
																	description: "avec des champignons",
																	recipe: 		 "mettez les oeufs",
																	owner:			 user,
																	ingredients: new HashMap<String, String>());
			Recipe recipe2 = new Recipe(title : 		 "gateaux a la noix de coco", 
																	description: "avec des pepites de chocolats",
																	recipe: 		 "mettez les oeufs",
																	owner:			 user,
																	ingredients: new HashMap<String, String>());
			Recipe recipe3 = new Recipe(title : 		 "scouts aux epices de pins", 
																	description: "cru et au feu de camp",
																	recipe: 		 "prenez une hache et attendez la nuit",
																	owner:			 user,
																	ingredients: new HashMap<String, String>());
			recipe1.save(failOnError : true)
			recipe2.save(failOnError : true)
			recipe3.save(failOnError : true)			
		expect:
			recipeService.searchRecipe("omelette").size() == 1
			recipeService.searchRecipe("choco").size() == 1
			recipeService.searchRecipe("gateau").size() == 1
			recipeService.searchRecipe("noix").size() != 0
			recipeService.searchRecipe("champignons").size() == 1
			recipeService.searchRecipe("geaorge").size() == 0
			recipeService.searchRecipe("viandes").size() == 0
			recipeService.searchRecipe("chauuuddd cacao").size() == 0
	}
	
	void "test vote on recipe"(){
		setup:
			User user = new User(email: "test@test.test", username: "test", password: "test" )
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
			User user = userService.createUser("test", "test", "test@test.test")
		
		when:
			Recipe recipe = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			Recipe recipe2 = new Recipe(title: "rr", recipe: "tet", owner: user, ingredients: new HashMap<String,String>(), )
			
		then:
			Comment comment=recipeService.commentOnRecipe(recipe, "comment", user)
			recipe.comments.contains(comment)
			user.comments.contains(comment)
			Comment comment2=recipeService.commentOnRecipe(recipe, "comment2", user)
			recipe.comments.contains(comment2)
			user.comments.contains(comment)
			
	}
	
	void "test read all recipe"(){
		
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			
			Recipe recipe = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			
			Recipe recipe1 = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			
			Recipe recipe2 = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
			
		when:				
			List<Recipe> actual=recipeService.readAllRecipe(user)			
		
		then:
			actual.contains(recipe)==true
			actual.contains(recipe1)==true
			actual.contains(recipe2)==true
	}
}
