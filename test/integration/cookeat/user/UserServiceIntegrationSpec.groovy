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
				
				userService.updateUser(user, 666, UserService.Action.EMAIL) == null
				userService.updateUser(user, 666, UserService.Action.FIRSTNAME) == null
				userService.updateUser(user, 666, UserService.Action.LASTNAME) == null
				userService.updateUser(user, 666, UserService.Action.DESCRIPTION) == null
				userService.updateUser(user, 666, UserService.Action.PASSWORD) == null
				
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
}
