
package cookeat.user

import spock.lang.Specification



@TestFor(UserRole)
class UserRolespec extends Specification{
	def setup() {
	}

	def cleanup() {
	}
	
	void "set user role wrong"(){
		when:"wrong user role set"
		
		then:
		!(new UserRole(user:usr,role:role).validate());
		
		where:
		usr		|role
		new User()	|null
		null	|new Role()
	}
	
	void "set user role valid"() {
		when: "valide user role set"
		
		then:
		new UserRole(user:usr,role:role).validate();
		
		where:
		usr		|role
		new User()|new Role()
	}
	

}
