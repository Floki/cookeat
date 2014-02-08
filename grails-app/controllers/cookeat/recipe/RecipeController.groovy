package cookeat.recipe



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class RecipeController {

	static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

	RecipeService recipeService
	CommentService commentService
	SpringSecurityService springSecurityService

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Recipe.list(params), model:[recipeInstanceCount: Recipe.count()]
	}

	def show(Recipe recipeInstance) {
		respond recipeInstance
	}

	def create() {
		respond new Recipe(params)
	}

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

		// TODO Here we should use the recipeService but too heavy
		recipeInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'recipeInstance.label', default: 'Recipe'), recipeInstance.id])
				redirect recipeInstance
			}
			'*' { respond recipeInstance, [status: CREATED] }
		}
	}

	def edit(Recipe recipeInstance) {
		respond recipeInstance
	}

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

		//TODO Here we should use the recipeService but too heavy
		recipeInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'Recipe.label', default: 'Recipe'), recipeInstance.id])
				redirect recipeInstance
			}
			'*'{ respond recipeInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Recipe recipeInstance) {

		if (recipeInstance == null) {
			notFound()
			return
		}

		recipeInstance.delete flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Recipe.label', default: 'Recipe'), recipeInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	def addComment() {
		Recipe recipe = Recipe.get(params.id)
		recipeService.commentOnRecipe(recipe, params.comment, springSecurityService.getCurrentUser())
		redirect recipe
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
}
