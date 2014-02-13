package cookeat.recipe

import grails.transaction.Transactional
import cookeat.user.User

@Transactional
class RecipeService {
	
	VoteService voteService
	CommentService commentService 

    def createRecipe(String title,String description,String recipe,Map<String,String> ingredients,
						byte[] picture,	int nbPeople, User owner) {
		Recipe recipeToSave = new Recipe()
		recipeToSave.owner = owner
		recipeToSave.title = title
		recipeToSave.description = description
		recipeToSave.recipe = recipe
		recipeToSave.ingredients = (ingredients == null)?  new HashMap<String,String>() : ingredients
		recipeToSave.picture = (picture == null)?  new byte[0] : picture		
		recipeToSave.nbPeople = nbPeople
		recipeToSave.votes = new HashSet<Vote>()
		recipeToSave.comments = new HashSet<Comment>()
		return recipeToSave.save()
    }
						
	def createBaseRecipe(String title, String recipe, Map<String,String> ingredients, User owner){
		Recipe recipeToSave = new Recipe(owner: owner, title: title,recipe: recipe, ingredients: ingredients)
		recipeToSave.votes = new HashSet<Vote>()
		recipeToSave.comments = new HashSet<Comment>()
		return recipeToSave.save()
	}
	
	def readAllRecipe(User owner){
		
		List<Recipe> listRecipe=Recipe.getAll()
		List<Recipe> result=new ArrayList<Recipe>()
		
		for( recipe in listRecipe){
			if(recipe.owner.email == owner.email){
				result.add(recipe)
			}
		}
		return result
	}
	
	def updateTitleRecipe(Recipe actual, String title){
		actual.title = title
		return actual.save()
	}
	
	def updateDescripstionRecipe(Recipe actual, String description){
		actual.description = description
		return actual.save()
	}
	
	def updateRecipeRecipe(Recipe actual, String recipe){
		actual.recipe = recipe
		return actual.save()
	}
	
	def updateIngredientsRecipe(Recipe actual, Map<String,String> ingredients){
		if(ingredients != null)
			actual.ingredients = ingredients			
		return actual.save()
	}
	
	def updatePictureRecipe(Recipe actual, byte[] picture){
		actual.picture = picture
		return actual.save()
	}
	
	def updateNbPeopleRecipe(Recipe actual, int nbPeople){
		actual.nbPeople = nbPeople
		return actual.save()
	}
	
	def voteOnRecipe(Recipe actual, int rate, User user){
		Vote voteTemp = Vote.findByOwnerAndRecipe(user,actual)
		if(voteTemp != null)
			return null
		Vote vote = voteService.createVote(user, actual, rate)
		actual.votes.add(vote)
		actual.save()
		return vote
	}
	
	def commentOnRecipe(Recipe actual, String text, User user){
		Comment comment=commentService.createComment(user, actual, text)
		user.comments.add(comment)
		user.save()
		if(actual.comments == null)
			actual.comments = new HashSet<Comment>()
		actual.comments.add(comment)
		actual.save()
		return comment
	}
	
	def deleteCommentOnRecipe(Recipe actual, Comment comment){
		actual.comments.remove(comment)
		actual.save()
		
		User actualUser = User.findByUsername(comment.owner.username)
		actualUser.comments.remove(comment)
		actualUser.save()
		
		commentService.deleteComment(comment)
		return actual
	}
	
	def deleteRecipe(Recipe actual,User user) {
		List<Recipe> listRecipe=Recipe.findAllByOwner(user)
		for(recipe in listRecipe){
			if(recipe.equals(actual)){
				actual.delete()
				return true
				}
			}
		return false
		}
	
	def searchRecipe(String stringToSearch) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		String[] searchWords = stringToSearch.split(" ");
		for(String currentSearch : searchWords) {
			def search = "%" + currentSearch.replace(' ', '%') + "%";
			def request = "FROM  Recipe as r " +
										"WHERE r.title  LIKE '" + search + "' " +
										"OR r.description  LIKE '" + search + "' "
										"OR r.recipe  LIKE '" + search + "' ";
			recipes.addAll(Recipe.findAll(request));
		}
		
		return recipes;
	}
}
