package cookeat.recipe

import cookeat.user.User;
import grails.test.mixin.*
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Vote)
@Mock([Recipe,User])
class VoteSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "create vote"() {
    
	when: "create valid test"
	
	then:
	new Vote(owner: own,recipe:rec,rate:note).validate();
	
	where:
	own		  | rec           |note
	new User()| new Recipe()| 0
	new User()| new Recipe()|5
	new User()| new Recipe()|4
	
	
	}
	
	void "create non valid vote"(){
		
		when: "create valid test"
		
		then:
		!(new Vote(owner: own,recipe:rec,rate:note).validate());
		
		where:
		own		  | rec           |note
		new User()| new Recipe()| -1
		new User()| new Recipe()|6
		new User()|null|4
		null| new Recipe()|4
		
	}
}
