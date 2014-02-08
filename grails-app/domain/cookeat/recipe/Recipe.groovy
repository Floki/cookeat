package cookeat.recipe

import cookeat.user.User
import java.awt.Image

/**
 * Recipe Domain class
 * @author Exasky, Smail, Skaboy, Floki
 *
 */
class Recipe {
	
	String title
	String description
	String recipe
	Map<String,String> ingredients
	byte[] picture
	int nbPeople					// Recipe is for nbPeope people
	
	/*
	 * A Recipe belong to an user
	 */
	static belongsTo = [owner:User]
	/*
	 * A recipe can have several votes and comments
	 */
	static hasMany = [votes:Vote,comments:Comment]

		/*
		 * A recipe must have a title, an owner, a recipe detail and a list of ingredients
		 */
    static constraints = {
		title blank:false
		owner blank:false
		recipe blank:false
		ingredients blank:false
		picture nullable: true
		nbPeople nullable: true
		description nullable: true
    }
}
