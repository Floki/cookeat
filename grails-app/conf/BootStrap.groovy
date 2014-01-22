import org.junit.internal.runners.statements.FailOnTimeout;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import cookeat.recipe.Recipe;
import cookeat.user.Role
import cookeat.user.User
import cookeat.user.UserRole

class BootStrap {

    def init = { servletContext ->
		Role adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true, FailOnError : true)
		
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
		
		Recipe rec1 = new Recipe(title: "rec1", owner: user1, recipe: "rec1", ingredients: ingr)
		rec1.save(FailOnError : true)
		Recipe rec2 = new Recipe(title: "rec2", owner: user2, recipe: "rec2", ingredients: ingr)
		rec2.save(FailOnError : true)
		Recipe rec3 = new Recipe(title: "rec3", owner: user2, recipe: "rec3", ingredients: ingr)
		rec3.save(FailOnError : true)
    }
    def destroy = {
    }
}
