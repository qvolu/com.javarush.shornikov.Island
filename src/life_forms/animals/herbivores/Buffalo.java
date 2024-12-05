package life_forms.animals.herbivores;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Buffalo extends Herbivore {

    public Buffalo() {
        super(700, 3, 100, 10, "Buffalo");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Buffalo){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Buffalo(), location.getRow(), location.getColumn());
        }
    }
}
