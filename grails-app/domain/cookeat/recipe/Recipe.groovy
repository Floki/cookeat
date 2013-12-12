package cookeat.recipe

import cookeat.user.User
import java.awt.Image

class Recipe {
	
	String title
	String despription
	String recipe
	Map<String,String> ingredients
	Bytes[] picture
	int nbPeople
	User owner
	
	static belongsTo = [owner:User]
	static hasMany = [votes:Vote,comments:Comment]

    static constraints = {
		title blank:false
		owner blank:false, unique: true
		recipe blank:false
		ingredients blank:false
    }
}
