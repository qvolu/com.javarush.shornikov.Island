package life_forms.animals.carnivorous;

import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Eagle extends Carnivorous {

    public Eagle() {
        super(6, 3, 1, 20, "Eagle");
    }


    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.4;
            case "Duck" -> 0.6;
            case "Rabbit" -> 0.7;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Eagle){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Eagle(), location.getRow(), location.getColumn());
        }
    }
}