package cookeat.user



import spock.lang.*
import cookeat.user.UserService
import cookeat.user.User

/**
 *
 */
class UserServiceIntegrationSpec extends Specification {

		UserService userService = new UserService()

    void "add user"() {
			when:"When we add an normal user"
				User user = new User(username:"userSIS",password:"test",email:"userSIS@test.test")
				assert user != null
				assert userService.addUser(user) == true
				
			then:"L'utilisateur est enregistre dans la base de donnees"
				def userFound = User.findByUsername("userSIS")
				userFound
    }
}
