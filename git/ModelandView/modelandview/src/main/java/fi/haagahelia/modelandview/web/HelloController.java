package fi.haagahelia.modelandview.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String tervehdys(@RequestParam String nimi, @RequestParam int ika, Model malli) {
        malli.addAttribute("nimi", nimi);
        malli.addAttribute("ika", ika);
        return "tervehdys";
    }
}