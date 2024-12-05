package life_forms.animals.herbivores;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Rabbit extends Herbivore {

    public Rabbit() {
        super(2, 2, 0.45, 150, "Rabbit");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Rabbit){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Rabbit(), location.getRow(), location.getColumn());
        }
    }
}
