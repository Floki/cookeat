package cookeat.user

import grails.transaction.Transactional

@Transactional
class UserService {

		public static enum Action {	
			USERNAME,
			PASSWORD,
			FIRSTNAME,
			LASTNAME,
			DESCRIPTION,
			EMAIL,
			AVATAR
		};
	
    def createUser(String username, String password, String email) {
			User user = new User(username: username, password: password, email : email );
			return user.save();
		}
		
		def deleteUser(String stringToSearch) {
			if(stringToSearch.toUpperCase() ==~ /[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}/) {
				User.findByEmail(stringToSearch).delete()
			}
			else {
				User.findByUsername(stringToSearch).delete()
			}
		}
		
}
