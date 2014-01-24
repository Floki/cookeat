package cookeat.recipe



import spock.lang.*
import cookeat.user.User;

/**
 *
 */
class VoteServiceIntegrationSpec extends Specification {
	
	VoteService voteService 

    def setup() {
		
    }

    def cleanup() {
    }

    void "test vote update"() {
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			def recipe = new Recipe(ingredients: new HashMap<String, String>(), title: "title", owner: user, recipe: "test" )
			recipe.save(failOnError: true)
			def v1 = new Vote(owner: user, recipe: recipe, rate: 3)
			v1.save(failOnError: true)
			
		expect:
			voteService.updateVote(v1,2) == v1
			v1.rate == 2
			voteService.updateVote(v1,12) == null
    }
	
	void "test create vote"(){
		setup:
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			def recipe = new Recipe(ingredients: new HashMap<String, String>(), title: "title", owner: user, recipe: "test" )
			recipe.save(failOnError: true)
			
		when:
			Vote vote = voteService.createVote(user, recipe, 0)
			
		then:
			vote.owner == user && vote.recipe == recipe && vote.rate == 0
			voteService.createVote(user, recipe, 10) == null
	}
	
	void "test delete vote"(){
		setup: 
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			def recipe = new Recipe(ingredients: new HashMap<String, String>(), title: "title", owner: user, recipe: "test" )
			recipe.save(failOnError: true)
			def v1 = new Vote(owner: user, recipe: recipe, rate: 3)
			v1.save(failOnError: true)
			
		when:
			voteService.deleteVote(v1)
			
		then:
			Vote.getAll().empty == true
	}
	
	void "test read all votes on one recipe"(){
		setup:	
			def user = new User(email: "test@test.test", username: "test", password: "test" )
			user.save(failOnError : true)
			
			def recipe1 = new Recipe(ingredients: new HashMap<String, String>(), title: "recipe1", owner: user, recipe: "recipe1" )
			def recipe2 = new Recipe(ingredients: new HashMap<String, String>(), title: "recipe2", owner: user, recipe: "recipe2" )
			recipe1.save(failOnError: true)
			recipe2.save(failOnError: true)
			
			def v1 = new Vote(owner: user, recipe: recipe1, rate: 3)
			def v2 = new Vote(owner: user, recipe: recipe2, rate: 3)
			v1.save(failOnError: true)
			v2.save(failOnError: true)
			
		when:
			List<Vote> actual = voteService.readAllVoteOnRecipe(recipe1)
			
		then:	
			actual.contains(v1) == true
			actual.contains(v2) == false
			
	}
}
