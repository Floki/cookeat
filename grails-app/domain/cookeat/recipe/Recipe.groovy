package cookeat.recipe

import cookeat.user.User
import java.awt.Image

class Recipe {
	
	String title
	String description
	String recipe
	Map<String,String> ingredients
	byte[] picture
	int nbPeople					// Recipe is for nbPeope people
	
	static belongsTo = [owner:User]
	static hasMany = [votes:Vote,comments:Comment]

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
