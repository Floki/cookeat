package cookeat.user

import grails.transaction.Transactional

class UserService {

    def addUser(User user) {
			user.save(FailOnError : true)
			return true
		}
}
