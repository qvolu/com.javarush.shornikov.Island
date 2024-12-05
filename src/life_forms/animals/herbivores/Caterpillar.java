package life_forms.animals.herbivores;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Caterpillar extends Herbivore {

    public Caterpillar() {
        super(0.01, 0, 0, 1000, "Caterpillar");
    }

    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Caterpillar){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Caterpillar(), location.getRow(), location.getColumn());
        }
    }
}
