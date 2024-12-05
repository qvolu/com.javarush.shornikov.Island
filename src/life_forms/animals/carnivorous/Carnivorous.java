package life_forms.animals.carnivorous;

import life_forms.animals.Animal;

public abstract class Carnivorous extends Animal {


    public Carnivorous(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }
}