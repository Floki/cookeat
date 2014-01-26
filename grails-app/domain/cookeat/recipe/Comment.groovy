package cookeat.recipe

import cookeat.user.User

class Comment {
	
	User owner
	String text
	
	static belongsTo = [recipe:Recipe]

    static constraints = {
		owner blank:false
		recipe blank:false
		text blank:false
		
    }
}
