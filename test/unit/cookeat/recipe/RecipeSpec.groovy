package cookeat.recipe

import java.util.HashMap;

import cookeat.user.User;
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Recipe)
@Mock(User)
class RecipeSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "create non valid recipe"() {
		when: "non valid recipe create"
		
		then:
		!(new Recipe(title: titre,description: desc,recipe: rec,ingredients: ing,owner: us).validate());
		
		where:
		titre 	 | desc     | rec      | ing          |us           
		"cookie" | "desc"   | "recette"| new HashMap().put("String", "ing")|  null 
		"cookie" | "desc"   | "recette"|null| null   
		"cookie" | "desc"   |""| new HashMap().put("String", "ing")|  new User()   
		"toto" 	 | "desc"   |""| new HashMap().put("String", "ing")|   new User()   
		 	""| "desc"   | "recette"| null|  new User()   
		 	""  | "desc"   | "recette"| new HashMap().put("String", "ing")|  new User()   
		
    }
	
	
	
	void "valid recipe"(){
		when: "valid recipe"
		
		then:
		new Recipe(title: titre,description: desc,recipe: rec,ingredients: ing,owner: us).validate();
		
		where :
		titre 	 | desc     | rec      | ing          |us
		"cookie" | "desc"   | "recette"| new HashMap().put("String", "ing") |  new User()
		"cookie" | "desc"   | "recette"| new HashMap().put("String", "ing")| new User()
		"cookie" | "desc"   |"recette"| new HashMap().put("String", "ing")|  new User()
		"toto" 	 | "desc"   |"recette"| new HashMap().put("String", "ing")|   new User()
		
	}
	
	
	
}
