package Repository;

import Model.TouristAttraction;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> touristAttractions = new ArrayList<>();


    public TouristRepository() {
        touristAttractions.add(new TouristAttraction("Tivoli", "Most known amusement park in Denmark"));
        touristAttractions.add(new TouristAttraction("Bakken", "Oldest amusement park in Denmark"));
        touristAttractions.add(new TouristAttraction("Zoo", "An animal park in Copenhagen, Denmark"));
        touristAttractions.add(new TouristAttraction("Christiania", "A place of independence in the middle of Copenhagen"));
        touristAttractions.add(new TouristAttraction("Rundetårn", "An old tower built for a king in Copenhagen"));
    }


    public List<TouristAttraction> getTouristAttractions() {
        return touristAttractions;
    }

    public TouristAttraction getTouristAttractionByName(String name) {
        for (TouristAttraction attraction:touristAttractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristAttractions.add(touristAttraction);
    }


    public void deleteAttraction(String name) {
        int attractionIndex = -1;

        for (int i = 0; i < touristAttractions.size(); i++) {
            if (name.equalsIgnoreCase(touristAttractions.get(i).getName())) {
                attractionIndex = i;
                break;
            }
        }
        if (attractionIndex != -1) {
            touristAttractions.remove(attractionIndex);
        }
    }

    public void updateTouristAttraction(String name, TouristAttraction updatedAttraction){
        for (int i = 0; i < touristAttractions.size(); i++) {
            if (touristAttractions.get(i).getName().equalsIgnoreCase(name)) {
                touristAttractions.set(i, updatedAttraction);
                return;
            }

        }
    }

    public List<TouristAttraction> searchAttractions(String name) {
        List<TouristAttraction> allAttractions = getTouristAttractions();
        List<TouristAttraction> searchResults = new ArrayList<>();

        for (TouristAttraction attraction : allAttractions) {
            if (attraction.getName().toLowerCase().contains(name.toLowerCase())) {
                searchResults.add(attraction);
            }
        }

        return searchResults;
    }
}
