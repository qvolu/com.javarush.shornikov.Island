package life_forms.animals.herbivores;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Goat extends Herbivore {

    public Goat() {
        super(60, 3, 10, 140, "Goat");
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Goat){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Goat(), location.getRow(), location.getColumn());
        }
    }
}
