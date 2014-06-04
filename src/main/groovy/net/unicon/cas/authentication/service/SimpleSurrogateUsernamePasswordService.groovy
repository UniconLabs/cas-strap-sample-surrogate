package net.unicon.cas.authentication.service

import edu.berkeley.cas.authentication.service.SurrogateUsernamePasswordService

class SimpleSurrogateUsernamePasswordService implements SurrogateUsernamePasswordService {
    @Override
    boolean canAuthenticateAs(String username, String surrogate) {
        return true
    }
}
