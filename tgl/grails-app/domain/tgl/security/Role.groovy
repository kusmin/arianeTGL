package tgl.security

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Role implements Serializable {

	private static final long serialVersionUID = 1

	String authority
	String description


	static constraints = {
		authority nullable: false, blank: false, unique: true, maxSize:128
		description nullable:false, blank:false, maxSize:128
	}

	static mapping = {
		cache true
	}
}
