package life_forms.animals.herbivores;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Wildboar extends Herbivore {

    public Wildboar() {
        super(400, 2, 50, 50, "WildBoar");
    }


    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Mouse" -> 0.5;
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Wildboar){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Wildboar(), location.getRow(), location.getColumn());
        }
    }
}
