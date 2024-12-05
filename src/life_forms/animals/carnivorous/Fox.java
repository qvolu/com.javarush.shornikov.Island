
package life_forms.animals.carnivorous;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Fox extends Carnivorous {

    public Fox() {
        super(8, 2, 2, 30, "Fox");
    }


    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 1;
            case "Rabbit" -> 0.7;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Fox){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Fox(), location.getRow(), location.getColumn());
        }
    }
}
