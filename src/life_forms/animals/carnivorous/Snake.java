package life_forms.animals.carnivorous;



import island.Island;
import island.Location;
import life_forms.animals.Animal;

public class Snake extends Carnivorous {
    public Snake() {
        super(15, 1, 3, 30, "Snake");
    }

    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Fox" -> 0.15;
            case "Rabbit" -> 0.2;
            case "Mouse" -> 0.4;
            default -> 0;
        };
    }


    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Snake) {
            Location location = Island.getInstance().getLocation(partner.getRow(), partner.getColumn());
            Island.getInstance().addAnimal(new Snake(), location.getRow(), location.getColumn());
        }
    }
}
