
package cookeat.user

import grails.test.mixin.TestFor;
import spock.lang.Specification;

@TestFor(Role)
class RoleSpec extends Specification{
    
	def setup() {
    }

    def cleanup() {
    }
	
	void "non valid authority"(){
		when :"authorithy is not vadlid"
		
		then:
		!(new Role(authority:auth).validate());
		
		where:
		auth=""
	}
	
	void "valid authority"(){
		when:"authority is valid"
		
		then:
		new Role(authoritu:auth).validate();
		
		
		where:
		auth="authority"
	}
}
