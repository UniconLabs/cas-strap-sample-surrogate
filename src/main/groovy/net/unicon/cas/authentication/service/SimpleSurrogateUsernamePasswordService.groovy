package net.unicon.cas.authentication.service

import edu.berkeley.cas.authentication.service.SurrogateUsernamePasswordService
import org.springframework.ldap.core.AttributesMapper
import org.springframework.ldap.core.ContextMapper
import org.springframework.ldap.core.ContextSource
import org.springframework.ldap.core.DirContextAdapter
import org.springframework.ldap.core.DirContextOperations
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.filter.AndFilter
import org.springframework.ldap.filter.EqualsFilter

import javax.naming.NamingException
import javax.naming.directory.Attributes
import javax.naming.directory.DirContext
import javax.naming.directory.SearchControls
import javax.validation.constraints.NotNull

class SimpleSurrogateUsernamePasswordService implements SurrogateUsernamePasswordService {
    @NotNull
    ContextSource contextSource

    String baseDN = ""
    String userFilter = "(uid=%u)"

    def perms = [
            "jj": ["test1", "test2"],
            "pony": ["horse1", "horse2"]
    ]
    @Override
    boolean canAuthenticateAs(String username, String surrogate) {
        surrogate == username || getSurrogateAccounts(surrogate)?.contains(username)
    }

    @Override
    Collection<String> getSurrogateAccounts(String s) {
        def ldapTemplate = new LdapTemplate(this.contextSource)
        def nFilter = userFilter.replaceAll("%u", s)
        def userDn = ldapTemplate.search(baseDN, nFilter, SearchControls.SUBTREE_SCOPE, new ContextMapper() {
            @Override
            Object mapFromContext(Object o) {
                if (o instanceof DirContextOperations) {
                    return o.getDn().toString()
                }
                throw IllegalStateException("wrong type")
            }
        })[0]
        def list = [s]
        if (perms[s]) {
            list += perms[s]
        }
        return list
    }
}
