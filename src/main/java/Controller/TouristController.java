package Controller;


import Model.TouristAttraction;
import Service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {


    private final TouristService service;

    @Autowired
    public TouristController(TouristService service) {
        this.service = service;
    }

    public List<TouristAttraction> searchAttractions(String name) {
        return service.searchTouristAttractions(name);
    }


    @GetMapping
    public String showAttractionsPage(Model model) {
        List<TouristAttraction> attractions = service.getAllTourisAttractions();
        model.addAttribute("attractions", attractions);
        return "attractions"; // returning the name of the Thymeleaf template
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getTouristAttractionName(@PathVariable String name) {
        TouristAttraction touristAttraction = service.getAllTouristAttractionsByName(name);
        if (touristAttraction != null){
            return new ResponseEntity<>(touristAttraction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tourist attraction not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<List<TouristAttraction>> addTouristAttraction(@RequestBody TouristAttraction touristAttraction) {
        service.addTouristAttraction(touristAttraction);
        List<TouristAttraction> attractions = service.getAllTourisAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

    @PostMapping("/update/{name}")
    public ResponseEntity<List<TouristAttraction>> updateTouristAttraction(@PathVariable String name, @RequestBody TouristAttraction updatedTouristAttraction) {
        service.updateTouristAttraction(name, updatedTouristAttraction);
        List<TouristAttraction> attractions = service.getAllTourisAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<List<TouristAttraction>> deleteTouristAttraction(@PathVariable String name) {
        service.deleteTouristAttraction(name);
        List<TouristAttraction> attractions = service.getAllTourisAttractions();
        return new ResponseEntity<>(attractions,HttpStatus.OK);
    }

    @GetMapping("/attractions/search")
    public ResponseEntity<List<TouristAttraction>> searchAttractionsPage(@RequestParam(name = "query") String name) {
        List<TouristAttraction> searchResults = service.searchTouristAttractions(name);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
}
