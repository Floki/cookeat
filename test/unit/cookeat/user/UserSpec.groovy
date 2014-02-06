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

    void "create user"() {
			when:"new user valid or not valid"
			then:
				new User(username:username,password:password,firstname:firstname,lastname:lastname,email:email).validate() == isValid;
			
			where:
				username 		|password  	|firstname 	|lastname		|email	 					|isValid
				"username"	|"password"	|"firstname"|"lastname"	|"email"					|false
				""					|"password"	|"firstname"|"lastname"	|"email@mail.fr"	|false
				"username"	|""					|"firstname"|"lastname"	|"email@mail.fr"	|false
				"username"	|"password"	|""					|"lastname"	|"email@mail.fr"	|true
				"username"	|"password"	|"firstname"|""					|"email@mail.fr"	|true
				"username"	|"password"	|"firstname"|"lastname"	|""								|false
				"username"	|"password"	|"firstname"|"lastname"	|"email@mail.fr"	|true
				null				|"password"	|"firstname"|"lastname"	|"email@mail.fr"	|false
				"username"	|null				|"firstname"|"lastname"	|"email@mail.fr"	|false
				"username"	|"password"	|"firstname"|"lastname"	|null							|false
    }
}
