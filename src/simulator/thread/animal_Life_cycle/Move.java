package simulator.thread.animal_Life_cycle;

import island.Island;
import life_forms.animals.Animal;

import java.util.List;


public class Move implements Runnable {
    @Override
    public void run() {
        List<Animal> animals = Island.getInstance().getAllAnimals().stream().filter(c -> c.getStep() > 0).toList();
        for (Animal animal : animals) {
            animal.move();
        }
    }
}
