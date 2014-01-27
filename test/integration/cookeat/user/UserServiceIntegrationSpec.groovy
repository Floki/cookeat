package cookeat.user



import spock.lang.*
import cookeat.user.UserService
import cookeat.user.User

/**
 *
 */
class UserServiceIntegrationSpec extends Specification {

		UserService userService = new UserService()

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
}
