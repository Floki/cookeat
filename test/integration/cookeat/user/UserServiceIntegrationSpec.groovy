package cookeat.user

import spock.lang.*
import cookeat.recipe.Recipe
import cookeat.recipe.RecipeService;
import cookeat.user.UserService
import cookeat.user.User

/**
 *
 */
class UserServiceIntegrationSpec extends Specification {

		UserService userService
		RecipeService recipeService
		
    void "test create user"() {
			expect:
				User.findByUsername("login") == null
				User.findByEmail("login@mdp.fr") == null
				userService.createUser("login","mdp","login@mdp.fr") != null;
				User.findByUsername("login") != null
				User.findByEmail("login@mdp.fr") != null
				userService.createUser("","mdp","login@mdp.fr") == null;
				userService.createUser("login","","login@mdp.fr") == null;
				userService.createUser("login","mdp","") == null;
				userService.createUser("login","mdp","login@mdp.fr") == null;
				userService.createUser("login","mdp","loginr") == null;
    }
		
		
		void "test delete user"() {
			setup :
				userService.createUser("login","mdp","login@mdp.fr") 
				userService.createUser("login2","mdp2","login2@mdp.fr") 
			expect:
				User.findByUsername("login") != null
				User.findByEmail("login2@mdp.fr") != null
			when :
				userService.deleteUser("login")
				userService.deleteUser("login2@mdp.fr")
			then:
				User.findByUsername("login") == null
				User.findByEmail("login2@mdp.fr") == null
		}
		
		void "test update user"() {
			setup :
				userService.createUser("login","mdp","login@mdp.fr")
				User user = User.findByUsername("login");
			expect:
				userService.updateUser(user, "test", UserService.Action.PASSWORD) != null
				userService.updateUser(user, "test@mail.com", UserService.Action.EMAIL) != null
				User.findByEmail("test@mail.com") != null
				userService.updateUser(user, "last", UserService.Action.LASTNAME) != null
				User.findByLastname("last") != null
				userService.updateUser(user, "first", UserService.Action.FIRSTNAME) != null
				User.findByFirstname("first") != null
				userService.updateUser(user, "desc", UserService.Action.DESCRIPTION) != null
				User.findByDescription("desc") != null
				userService.updateUser(user, new byte[20], UserService.Action.AVATAR) != null;
				
				userService.updateUser(null, "desc2", UserService.Action.DESCRIPTION) == null;
				User.findByDescription("desc2") == null
				
				userService.updateUser(user, 777, UserService.Action.EMAIL) == null
				userService.updateUser(user, 777, UserService.Action.FIRSTNAME) == null
				userService.updateUser(user, 777, UserService.Action.LASTNAME) == null
				userService.updateUser(user, 777, UserService.Action.DESCRIPTION) == null
				userService.updateUser(user, 777, UserService.Action.PASSWORD) == null
				userService.updateUser(user, 777, UserService.Action.AVATAR) == null
				
				// Decide to avoid username change
				userService.updateUser(user, "username", UserService.Action.USERNAME) == null
		}
		
		void "test read user"() {
			setup :
				userService.createUser("login","mdp","login@mdp.fr")
				userService.createUser("login2","mdp2","login2@mdp.fr")
			expect:
				userService.readUser("login") != null
				userService.readUser("login2@mdp.fr") != null
				userService.readUser("logindqsdqd") == null
				userService.readUser("login2@mddsqp.fr") == null
		}
		
		void "test add recipe to favorites"(){
			
			setup :
				def user = new User(email: "test@test.test", username: "test", password: "test" )
				user.save(failOnError:true)	
				
				Recipe recipe = recipeService.createBaseRecipe("title", "recipe", new HashMap<String, String>(), user)
				recipe.save(failOnError:true)
				
				user.favoritesRecipes = new HashSet()
			
			expect:
				user.favoritesRecipes.contains(recipe)==false
			
			when:
				userService.addRecipeToFavorite(user, recipe)
			
			then:
				user.favoritesRecipes.contains(recipe)==true
		}
}
