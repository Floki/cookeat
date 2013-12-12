import cookeat.user.User

class BootStrap {

    def init = { servletContext ->
		User user = new User(username:"blup",password:"blup",email:"blup@blup.blup")
		user.save(failOnError : true)
    }
    def destroy = {
    }
}
