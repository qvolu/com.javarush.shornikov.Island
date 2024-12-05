package life_forms.animals.herbivores;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Deer extends Herbivore {

    public Deer() {
        super(300, 4, 50, 20, "Deer");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Deer){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Deer(), location.getRow(), location.getColumn());
        }
    }
}