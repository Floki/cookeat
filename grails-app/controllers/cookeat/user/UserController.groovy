package cookeat.user



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
class UserController {

	static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]
	
	@Secured(['ROLE_USER'])
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond User.list(params), model:[userInstanceCount: User.count()]
	}

	@Secured(['ROLE_USER'])
	def show(User userInstance) {
		respond userInstance
	}

	@Secured(['permitAll'])
	def create() {
		respond new User(params)
	}

	@Secured(['permitAll'])
	@Transactional
	def save(User userInstance) {
		if (userInstance == null) {
			notFound()
			return
		}
		
		if (userInstance.hasErrors()) {
			respond userInstance.errors, view:'create'
			return
		}
		
		userInstance.save flush:true
		def roleUser = Role.findByAuthority('ROLE_USER')
		UserRole.create(userInstance, roleUser)

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'userInstance.label', default: 'User'),
					userInstance.id
				])
				redirect userInstance
			}
			'*' { respond userInstance, [status: CREATED] }
		}
	}

	@Secured(['ROLE_USER'])
	def edit(User userInstance) {
		respond userInstance
	}

	@Secured(['ROLE_USER'])
	@Transactional
	def update(User userInstance) {
		if (userInstance == null) {
			notFound()
			return
		}

		if (userInstance.hasErrors()) {
			respond userInstance.errors, view:'edit'
			return
		}

		userInstance.save flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'User.label', default: 'User'),
					userInstance.id
				])
				redirect userInstance
			}
			'*'{ respond userInstance, [status: OK] }
		}
	}

	@Secured(['ROLE_USER'])
	@Transactional
	def delete(User userInstance) {

		if (userInstance == null) {
			notFound()
			return
		}

		userInstance.delete flush:true

		request.withFormat {
			form {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'User.label', default: 'User'),
					userInstance.id
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'userInstance.label', default: 'User'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
