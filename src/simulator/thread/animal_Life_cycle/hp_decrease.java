package simulator.thread.animal_Life_cycle;

import island.Island;
import island.Location;
import life_forms.animals.Animal;
import simulator.Island_Simulator;

import java.util.List;
import java.util.concurrent.CountDownLatch;


public class hp_decrease implements Runnable {  // уменьшение здоровья животных
    private double percentOfHpToDecrease = 15;
    private final CountDownLatch latch;
    private int animalsDiedByHungry;


    public hp_decrease(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsDiedByHungry = 0;
        List<Animal> animals = Island.getInstance().getAllAnimals().stream().filter(c -> c.getMaxHp() > 0).toList();
        if (Island_Simulator.getInstance().getTimeNow() / 60 >= 3) {
            percentOfHpToDecrease = percentOfHpToDecrease * 2;
        }
        for (Animal animal : animals) {
            double hpToDecrease = animal.getMaxHp() * percentOfHpToDecrease / 100.0;
            if (animal.getHp() - hpToDecrease > 0) {
                animal.setHp(animal.getHp() - hpToDecrease);
            } else {
                Location location = Island.getInstance().getLocation(animal.getRow(), animal.getColumn());
                Island.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                animalsDiedByHungry++;
            }
        }
        latch.countDown();
    }
    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;
    }
}
