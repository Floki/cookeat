package cookeat.recipe

import cookeat.user.User
import grails.transaction.Transactional

@Transactional
class VoteService {

    def createVote(User owner, Recipe recipe, int rate) {
		Vote vote = new Vote(owner: owner, recipe: recipe, rate: rate)
		return vote.save()
    }
	
	def readAllVoteOnRecipe(Recipe recipe){
		List<Vote> listVote =  Vote.getAll()
		List<Vote> result = new ArrayList<Vote>()
		for (var in listVote) {
			if (var.getRecipe() == recipe)
				result.add(var)
		}
		return result
	}
	
	def updateVote(Vote actual, int newRate){
		actual.rate = newRate
		return actual.save()
	}
	
	def deleteVote(Vote vote){
		vote.delete()
	}
}
