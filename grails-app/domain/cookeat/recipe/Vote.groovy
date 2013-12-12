package cookeat.recipe

import cookeat.user.User

class Vote {
	
	User owner
	Recipe recipe
	int rate
	
	static belongsTo = [recipe:Recipe]

    static constraints = {
		owner blank:false
		recipe blank:false
		rate blank:false, max: 5, min:0
    }
}
