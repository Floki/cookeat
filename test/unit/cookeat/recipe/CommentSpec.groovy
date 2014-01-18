package cookeat.recipe

import cookeat.user.User;
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comment)
@Mock([User,Recipe])
class CommentSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "create non valid comment"() {

		when: "non valid comment"
		
		then:
		!(new Comment( owner:own, recipe :rec, text: txt).validate());
		
		where:
		own		  | rec           |txt
		new User()| new Recipe()| null
		null| new Recipe()|null
		new User()|null|""
		null| new Recipe()|"text"
		
	}
	
	void "create valid comment"() {
	
	when: "valid comment"
		
		then:
		(new Comment( owner:own, recipe :rec, text: txt).validate());
		
		where:
		own		  | rec           |txt
		new User()| new Recipe()| "new comment"
	
	}
}
