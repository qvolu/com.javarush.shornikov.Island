package life_forms.animals.herbivores;

import island.Island;
import island.Location;
import life_forms.animals.Animal;


public class Duck extends Herbivore {

    public Duck() {
        super(1, 4, 0.15, 200, "Duck");
    }


    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Duck){
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Duck(), location.getRow(), location.getColumn());
        }
    }
}
