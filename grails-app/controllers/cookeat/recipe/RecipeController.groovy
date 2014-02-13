package cookeat.recipe



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RecipeController {

	static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]
	RecipeService recipeService
	CommentService commentService
	SpringSecurityService springSecurityService

	@Secured(['permitAll'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
       respond Recipe.list(params), model:[recipeInstanceCount: Recipe.count()]
    }

	@Secured(['permitAll'])
    def show(Recipe recipeInstance) {
        respond recipeInstance
    }

	@Secured(['ROLE_USER'])
    def create() {
        respond new Recipe(params)
    }

	@Secured(['ROLE_USER'])
    @Transactional
    def save(Recipe recipeInstance) {
        if (recipeInstance == null) {
            notFound()
            return
        }

        if (recipeInstance.hasErrors()) {
            respond recipeInstance.errors, view:'create'
            return
        }
		
		Recipe recipe = recipeService.createRecipe(recipeInstance.title, recipeInstance.description,
								   recipeInstance.recipe, recipeInstance.ingredients,
								   recipeInstance.picture, recipeInstance.nbPeople, 
								   recipeInstance.owner)

//		recipeInstance.save flush:true
		
        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'recipeInstance.label', default: 'Recipe'), recipe.id])
                redirect recipe
            }
            '*' { respond recipe, [status: CREATED] }
        }
    }

	@Secured(['ROLE_USER'])
    def edit(Recipe recipeInstance) {
        respond recipeInstance
    }

	@Secured(['ROLE_USER'])
    @Transactional
    def update(Recipe recipeInstance) {
        if (recipeInstance == null) {
            notFound()
            return
        }

        if (recipeInstance.hasErrors()) {
            respond recipeInstance.errors, view:'edit'
            return
        }

		recipeService.updateDescripstionRecipe(recipeInstance, recipeInstance.description)
		recipeService.updateIngredientsRecipe(recipeInstance, recipeInstance.ingredients)
		recipeService.updateNbPeopleRecipe(recipeInstance, recipeInstance.nbPeople)
		recipeService.updatePictureRecipe(recipeInstance, recipeInstance.picture)
		recipeService.updateRecipeRecipe(recipeInstance, recipeInstance.recipe)
		Recipe recipe = recipeService.updateTitleRecipe(recipeInstance, recipeInstance.title)
		
//        recipeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Recipe.label', default: 'Recipe'), recipe.id])
                redirect recipe
            }
            '*'{ respond recipe, [status: OK] }
        }
    }

	@Secured(['ROLE_USER'])
    @Transactional
    def delete(Recipe recipeInstance) {

        if (recipeInstance == null) {
            notFound()
            return
        }

		recipeService.deleteRecipe(recipeInstance, recipeInstance.owner)
//		recipeInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Recipe.label', default: 'Recipe'), recipeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipeInstance.label', default: 'Recipe'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
		
	@Secured(['ROLE_USER'])
	def addComment() {
		Recipe recipe = Recipe.get(params.id)
		recipeService.commentOnRecipe(recipe, params.comment, springSecurityService.getCurrentUser())
		redirect recipe
	}
	
	@Secured(['ROLE_USER'])
	def removeComment() {
		Recipe recipe = recipeService.deleteCommentOnRecipe(Recipe.get(params.id), Comment.get(params.commentId))
		redirect recipe
	}
}
