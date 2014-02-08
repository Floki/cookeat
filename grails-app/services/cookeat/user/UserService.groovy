package cookeat.user

import cookeat.recipe.Comment
import cookeat.recipe.Recipe
import cookeat.recipe.Vote
import grails.transaction.Transactional

@Transactional
class UserService {

		public static enum Action {	
			USERNAME,
			PASSWORD,
			FIRSTNAME,
			LASTNAME,
			DESCRIPTION,
			EMAIL,
			AVATAR
		};
	
    def createUser(String username, String password, String email) {
			User user = new User(username: username, password: password, email : email );
			user.votes = new HashSet<Vote>()
			user.comments = new HashSet<Comment>()
			user.favoritesRecipes = new HashSet<Recipe>()
			user.friends = new HashSet<User>()
			user.ownerRecipes = new HashSet<Recipe>()
			return user.save();
		}
		
		def deleteUser(String stringToSearch) {
			if(stringToSearch.toUpperCase() ==~ /[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}/) {
				User.findByEmail(stringToSearch).delete()
			}
			else {
				User.findByUsername(stringToSearch).delete()
			}
		}

		def updateUser(User actualUser, def update, Action action) {
			if(actualUser == null) {
				return null;
			}
			switch(action) {
				case Action.PASSWORD :
					if(!(update instanceof String)) {
						return null;
					}	
					actualUser.setPassword(update)
					return actualUser.save()
				case Action.EMAIL :
					if(!(update instanceof String)) {
						return null;
					}
					actualUser.setEmail(update)
					return actualUser.save()
				case Action.FIRSTNAME :
					if(!(update instanceof String)) {
						return null;
					}
					actualUser.setFirstname(update)
					return actualUser.save()
				case Action.LASTNAME :
					if(!(update instanceof String)) {
						return null;
					}
					actualUser.setLastname(update)
					return actualUser.save()
				case Action.DESCRIPTION :
					if(!(update instanceof String)) {
						return null;
					}
					actualUser.setDescription(update)
					return actualUser.save()
				case Action.AVATAR :
					if(!(update instanceof byte[])) {
						return null
					}
					actualUser.setAvatar(update)
					return actualUser.save()
			}
			return null;
		}
		
		def readUser(String stringToSearch) {
			if(stringToSearch.toUpperCase() ==~ /[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}/) {
				return User.findByEmail(stringToSearch)
			}
			else {
				return User.findByUsername(stringToSearch)
			}
		}

		def addFriend(User actualUser,User friend){
			if(actualUser.friends==null){
				actualUser.friends = new HashSet()
			}
			if(!actualUser.friends.contains(friend)){
				actualUser.friends.add(friend)
				return true;
			}
				return false;
		}
		
		def addRecipeToFavorite(User user,Recipe recipe){
			
			if((user.favoritesRecipes.contains(recipe)) == false){
				user.favoritesRecipes.add(recipe)
				return user.save()
			}
			return false

		}
}
