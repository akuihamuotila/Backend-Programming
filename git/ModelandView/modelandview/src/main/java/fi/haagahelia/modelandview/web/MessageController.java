package fi.haagahelia.modelandview.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.modelandview.domain.Message;

@Controller
public class MessageController {

    private List<Message> viestit = new ArrayList<>();

    @GetMapping("/message")
    public String naytaLomake(Model malli) {
        malli.addAttribute("viestit", viestit);
        return "lomake";
    }

    @PostMapping("/message")
    public String kasitteleLomake(@RequestParam String otsikko, @RequestParam String sisalto) {
        viestit.add(new Message(otsikko, sisalto));
        return "redirect:/message";
    }
}