package fi.haagahelia.modelandview.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fi.haagahelia.modelandview.domain.Student;

@Controller
public class StudentController {

    @GetMapping("/student")
    public String naytaOpiskelijat(Model malli) {
        List<Student> opiskelijat = new ArrayList<>();
        opiskelijat.add(new Student("Jorma", "Jamsa"));
        opiskelijat.add(new Student("Jari", "Kuusinen"));  
        opiskelijat.add(new Student("Sari", "Tamminen"));

        malli.addAttribute("opiskelijat", opiskelijat);

        return "opiskelijat";
    }
}