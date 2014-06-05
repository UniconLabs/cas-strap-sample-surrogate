package net.unicon.cas.authentication.service

import edu.berkeley.cas.authentication.service.SurrogateUsernamePasswordService

class SimpleSurrogateUsernamePasswordService implements SurrogateUsernamePasswordService {
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
        def list = [s]
        if (perms[s]) {
            list += perms[s]
        }
        return list
    }
}
