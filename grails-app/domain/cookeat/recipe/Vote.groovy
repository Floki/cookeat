package cookeat.recipe

import cookeat.user.User

class Vote {
	
	int rate
	
	static belongsTo = [recipe:Recipe, owner: User]

    static constraints = {
		owner blank:false
		recipe blank:false
		rate blank:false, max: 5, min:0
    }
}
