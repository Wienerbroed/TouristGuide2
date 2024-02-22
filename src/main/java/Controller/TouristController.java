package Controller;

import Model.TouristAttraction;
import Service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "attraction")
public class TouristController {

    private final TouristService service;


    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/attraction")
    public String showAttractionsPage(Model model) {
        List<TouristAttraction> attractions = service.getAllTourisAttractions();
        model.addAttribute("attractions", attractions);
        return "attraction";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("attraction", new TouristAttraction("", "")); // Provide default values for the constructor
        return "add";
    }

    @PostMapping("/add")
    public String addTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        service.addTouristAttraction(touristAttraction);
        return "redirect:/attraction/show";
    }

    @GetMapping("/update/{name}")
    public String showUpdateForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.getAllTouristAttractionsByName(name);
        model.addAttribute("attraction", attraction);
        return "update";
    }

    @PostMapping("/update/{name}")
    public String updateTouristAttraction(@PathVariable String name, @ModelAttribute TouristAttraction updatedAttraction) {
        service.updateTouristAttraction(name, updatedAttraction);
        return "redirect:/attraction/show";
    }

    @PostMapping("/delete/{name}")
    public String deleteTouristAttraction(@PathVariable String name) {
        service.deleteTouristAttraction(name);
        return "redirect:/attraction/show";
    }

    @GetMapping("/search")
    public ResponseEntity<List<TouristAttraction>> searchAttractionsPage(@RequestParam(name = "query") String name) {
        List<TouristAttraction> searchResults = service.searchTouristAttractions(name);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
}
