import grails.plugins.springsecurity.*

beans = {

    //springSecurityService(SpringSecurityService)
    springConfig.addAlias "springSecurityService", "springSecurityCoreSpringSecurityService"

}