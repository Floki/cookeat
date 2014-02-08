package cookeat.user

import cookeat.recipe.Comment
import cookeat.recipe.Recipe
import cookeat.recipe.Vote

import java.awt.Image

/**
 * User Domain class
 * @author Exasky, Smail, Skaboy, Floki
 *
 */
class User {

	transient springSecurityService

	String username
	String password
	String firstname
	String lastname
	String description
	String email
	byte[] avatar
		
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	/*
	 * A user has shared recipe, has favorited recipes, can post comments and votes, and have friends
	 */
	static hasMany = [ownerRecipes:Recipe, favoritesRecipes:Recipe, votes:Vote, comments:Comment, friends:User]
	static transients = ['springSecurityService']

	/*
	 * A user must have an Username, a Password and an Email
	 */
	static constraints = {
		username blank: false, unique: true
		password blank: false
		email blank: false, unique: true, email: true
		avatar nullable: true
		description nullable: true, blank: true
		firstname nullable: true, blank: true
		lastname nullable: true, blank: true
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
