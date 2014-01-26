package cookeat.recipe

import java.util.Map;

import cookeat.user.User
import grails.transaction.Transactional

@Transactional
class RecipeService {

    def createRecipe(String title,String description,String recipe,Map<String,String> ingredients,
						Byte[] picture,	int nbPeople, User owner) {
		Recipe recipeToSave = new Recipe(owner: owner, title: title, description: description, recipe: recipe, 
			ingredients: ingredients,
			picture: picture, nbPeople: nbPeople)
		return recipeToSave.save()
    }
						
	def createBaseRecipe(String title, String recipe, Map<String,String> ingredients, User owner){
		Recipe recipeToSave = new Recipe(owner: owner, title: title,recipe: recipe, ingredients: ingredients)
		return recipeToSave.save()
	}
}
