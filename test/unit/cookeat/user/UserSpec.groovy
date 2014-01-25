package cookeat.user

import cookeat.user.User;
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {
						
    def setup() {
    }

    def cleanup() {
    }

    void "create non valid user"() {
		when:"new user is not valid"
		then:
		!(new User(username:usr,password:pwd,firstname:name,lastname:nickname,email:mail).validate());
		
		where:
		usr 	|pwd  	|mail 
		"user" 	|"pwd"	|""
		"user"	|""		|"mail"
		"" 		|"pwd"	|"mail"
    }
	void "create valid user"(){
		when:"validate new user"
		then:
		(new User(username:usr,password:pwd,firstname:name,lastname:nickname,email:mail).validate());
		
		where:
		usr 	|pwd  	|mail
		"user" 	|"pwd"	|"mail"
	}
}
