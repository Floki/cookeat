package cookeat.recipe

import cookeat.user.User

/**
 * Vote Domain class
 * @author Exasky, Smail, Skaboy, Floki
 *
 */
class Vote {
	
	int rate
	/*
	 * A rate is for a user and a recipe
	 */
	static belongsTo = [recipe:Recipe, owner: User]

		/*
		 * A rate must be beetwen 0 and 5, have a owner and a recipe 
		 */
    static constraints = {
		owner blank:false
		recipe blank:false
		rate blank:false, max: 5, min:0
    }
}
