package cookeat.recipe



import spock.lang.*
import cookeat.user.User;

/**
 *
 */
class VoteServiceIntegrationSpec extends Specification {
	
	Vote v1
	VoteService voteService = new VoteService()

    def setup() {
		
    }

    def cleanup() {
    }

    void "test vote update"() {
		setup:
		def user = Mock(User)
		def recipe = Mock(Recipe)
		v1 = new Vote(owner: user, recipe: recipe, rate: 3)
		
	expect:
		voteService.updateVote(v1,0)
		voteService.updateVote(v1,5)
    }
}
