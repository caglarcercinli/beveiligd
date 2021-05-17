package be.vdab.beveiligd2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("werknemers")
class WerknemersController {
    @GetMapping
    public String werknemers() {
        return "werknemers";
    }
}
