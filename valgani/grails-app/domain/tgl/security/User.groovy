package tgl.security

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String email
    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    String nomeCompleto
    String endereco
    String cidade
    String uf

    

    Set<Role> getAuthorities() {
        this.perfil.permissoes
        // (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static belongsTo = [
        perfil:Perfil
    ]

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        email nullable:true, blank:true, maxSize:128, unique:true, email:true
        perfil nullable:false
        nomeCompleto nullable:true, maxSize:128, blank:true
        endereco nullable:true, maxSize:255, blank:true
        cidade nullable:true, maxSize:128, blank:true
        uf nullable:true, maxSize:128, blank:true
    }

    static mapping = {
	    password column: '`password`'
    }
}
