package Service;


import Model.TouristAttraction;
import Repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository repository;


    @Autowired
    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public List<TouristAttraction> getAllTourisAttractions() {
        return repository.getTouristAttractions();
    }

    public TouristAttraction getAllTouristAttractionsByName(String name) {
        return repository.getTouristAttractionByName(name);
    }

    public void addTouristAttraction (TouristAttraction touristAttraction) {
        repository.addTouristAttraction(touristAttraction);
    }

    public void deleteTouristAttraction (String name) {
        repository.deleteAttraction(name);
    }

    public void updateTouristAttraction (String name, TouristAttraction updatedtTouristAttraction) {
        repository.updateTouristAttraction(name, updatedtTouristAttraction);
    }
    public List<TouristAttraction> searchTouristAttractions (String name) {
        return repository.searchAttractions(name);
    }
}
