
package life_forms.animals.herbivores;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Horse extends Herbivore {

    public Horse() {
        super(400, 4, 60, 20, "Horse");
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Horse){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Horse(), location.getRow(), location.getColumn());
        }
    }
}
