package cookeat.recipe

import java.util.Map;

import cookeat.user.User
import grails.transaction.Transactional

@Transactional
class RecipeService {

    def createRecipe(String title,String description,String recipe,Map<String,String> ingredients,
						byte[] picture,	int nbPeople, User owner) {
		Recipe recipeToSave = new Recipe(owner: owner, title: title, description: description, recipe: recipe, 
			ingredients: ingredients,
			picture: picture, nbPeople: nbPeople)
		return recipeToSave.save()
    }
						
	def createBaseRecipe(String title, String recipe, Map<String,String> ingredients, User owner){
		Recipe recipeToSave = new Recipe(owner: owner, title: title,recipe: recipe, ingredients: ingredients)
		return recipeToSave.save()
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
}
