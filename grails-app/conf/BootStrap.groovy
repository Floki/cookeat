import org.junit.internal.runners.statements.FailOnTimeout;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import cookeat.recipe.Recipe;
import cookeat.user.Role
import cookeat.user.User
import cookeat.user.UserRole

class BootStrap {

    def init = { servletContext ->
		Role adminRole = new Role(authority: 'ROLE_USER').save(flush: true, FailOnError : true)
		
		User user1 = new User(username:"user1",password:"test",email:"user1@test.test")
		user1.save(failOnError : true)
		User user2 = new User(username:"user2",password:"test",email:"user2@test.test")
		user2.save(FailOnError : true)
		User user3 = new User(username:"user3",password:"test",email:"user3@test.test")
		user3.save(FailOnError : true)
		
		UserRole.create user1, adminRole, true;
		UserRole.create user2, adminRole, true;
		UserRole.create user3, adminRole, true;
		
		Map<String,String> ingr = new HashMap();
		ingr.put("Poulet", "1kg");
		ingr.put("Vache Hallal", "100 vaches");
		
		Recipe rec1 = new Recipe(title: "Poulet aux noix", owner: user1, recipe: "Poulet aux noix", ingredients: ingr, description: "Venez gouter ce délicieux met", nbPeople: 4)
		rec1.save(FailOnError : true)
		Recipe rec2 = new Recipe(title: "Mamie épicée aux amandes douces", owner: user2, recipe: "Mamie épicée aux amandes douces", ingredients: ingr, description: "Bonne mamie d'antan. Vous allez adorer!")
		rec2.save(FailOnError : true)
		Recipe rec3 = new Recipe(title: "Coulis de jus de chaussette", owner: user2, recipe: "Coulis de jus de chaussette", ingredients: ingr, description: "Blup blop. Blap Hihi Bloup")
		rec3.save(FailOnError : true)
    }
    def destroy = {
    }
}
