package net.unicon.cas.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WhoAmIController {
    @RequestMapping(value = "whoami")
    @ResponseBody
    def whoAmId() {
        "here I am"
    }
}
