package life_forms.animals.carnivorous;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Bear extends Carnivorous {

    public Bear() {
        super(500, 2, 80, 5, "Bear");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Buffalo" -> 0.2;
            case "Horse" -> 0.4;
            case "WildBoar" -> 0.5;
            case "Goat", "Sheep" -> 0.7;
            case "Deer", "Rabbit", "Snake" -> 0.8;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Bear){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Bear(), location.getRow(), location.getColumn());
        }
    }
}

