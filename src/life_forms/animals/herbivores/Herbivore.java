package life_forms.animals.herbivores;

import life_forms.animals.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }

    @Override
    public double getChanceToEat(String foodName) { // вероятность поедания пищи
        return switch (foodName) {
            case "Plant" -> 1;
            default -> 0;
        };
    }
}