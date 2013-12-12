package cookeat.user

import cookeat.recipe.Comment
import cookeat.recipe.Recipe
import cookeat.recipe.Vote
import java.awt.Image


class User {

	transient springSecurityService

	String username
	String password
	String Firstname
	String Lastname
	String description
	String email
	Image avatar
	
	static hasMany = [ownerRecipes:Recipe, favoritesRecipes:Recipe, votes:Vote, comments:Comment, friends:User]
	
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email blank: false, unique: true, email: true	
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
