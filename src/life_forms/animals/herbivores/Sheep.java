package life_forms.animals.herbivores;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Sheep extends Herbivore {

    public Sheep() {
        super(70, 3, 15, 140, "Sheep");
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Sheep){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Sheep(), location.getRow(), location.getColumn());
        }
    }
}
