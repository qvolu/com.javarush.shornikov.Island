package life_forms.animals.carnivorous;



import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Wolf extends Carnivorous {
    public Wolf() {
        super(50, 3, 8, 30, "Wolf");
    }


    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Horse", "Buffalo" -> 0.1;
            case "Deer", "WildBoar" -> 0.15;
            case "Duck" -> 0.4;
            case "Goat", "Rabbit" -> 0.6;
            case "Sheep" -> 0.7;
            case "Mouse" -> 0.8;
            default -> 0;
        };
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Wolf) {
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Wolf(), location.getRow(), location.getColumn());
        }
    }
}
