package cookeat.recipe



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class RecipeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]
	
	RecipeService recipeService

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

	@Secured(['ROLE_USER'])
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
