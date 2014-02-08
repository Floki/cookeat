package cookeat.recipe

import cookeat.user.User

/**
 * Comment Domain class
 * @author Exasky, Smail, Skaboy, Floki
 *
 */
class Comment {
	
	User owner
	String text
	
	/*
	 * A comment is on a recipe
	 */
	static belongsTo = [recipe:Recipe]

		/*
		 * The text, the recipe and the owner must be indicated
		 */
    static constraints = {
		owner blank:false
		recipe blank:false
		text blank:false
		
    }
}
