package cookeat.user

import grails.transaction.Transactional

@Transactional
class UserService {

    def createUser(String username, String password, String email) {
			User user = new User(username: username, password: password, email : email );
			return user.save();
		}
}
