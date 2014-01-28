package cookeat.recipe

import java.util.Map;

import cookeat.user.User
import grails.transaction.Transactional

@Transactional
class RecipeService {
	
	VoteService voteService
	CommentService commentService 

    def createRecipe(String title,String description,String recipe,Map<String,String> ingredients,
						byte[] picture,	int nbPeople, User owner) {
		Recipe recipeToSave = new Recipe(owner: owner, title: title, description: description, recipe: recipe, 
			ingredients: ingredients,
			picture: picture, nbPeople: nbPeople)
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
		for( var in listRecipe){
			if(var.getOwner==owner){
				result.add(var)
			}
			return result	
		}	
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
		actual.comments.add(comment)
		actual.save()
		return comment
	}
	
	def deleteRecipe(Recipe actual,User user){
		Recipe.findByRecipeAndOwner(actual, user).delete()
	}
}
