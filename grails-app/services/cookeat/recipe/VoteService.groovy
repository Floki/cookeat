package cookeat.recipe

import grails.transaction.Transactional

@Transactional
class VoteService {

    def createVote() {

    }
	
	def readVote(){
	
	}
	
	def updateVote(Vote actual, int newRate){
		def vote = Vote.get(actual)
		vote.rate = newRate
		vote.save()
	}
	
	def deleteVote(){
		
	}
	
	
}
