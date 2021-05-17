package be.vdab.beveiligd2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("offertes")
class OffertesController {
    @GetMapping
    public String offertes() {
        return "offertes";
    }
}
