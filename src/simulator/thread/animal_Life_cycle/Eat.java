package simulator.thread.animal_Life_cycle;

import island.Island;
import island.Location;
import life_forms.Lifeform;
import life_forms.animals.Animal;
import life_forms.plants.Plant;
import simulator.Island_Simulator;
import simulator.thread.Statistic;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class Eat implements Runnable {
    private final CountDownLatch latch;
    private int animalsEaten;


    public Eat(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        animalsEaten = 0;
        List<Animal> animals = Island.getInstance().getAllAnimals();
        List<Lifeform> LifeformsEaten = new ArrayList<>();
        int countAnimalsStart = animals.size();

        if (countAnimalsStart > 0 && animals.stream().filter(c -> !(c.getName().equals("Caterpillar"))).toList().size() > 0) {
            Iterator<Animal> iterator = animals.iterator();

            while (iterator.hasNext()) {
                Animal currentAnimal = iterator.next();
                if (currentAnimal.getMaxHp() > 0) {
                    Location location = Island.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());
                    List<Lifeform> locationLifeforms = location.getLifeforms();

                    if (!locationLifeforms.isEmpty()) {
                        for (Lifeform lifeform : locationLifeforms) {
                            if (currentAnimal.getChanceToEat(lifeform.getName()) > 0 && !(LifeformsEaten.contains(lifeform))) {
                                boolean isEaten = currentAnimal.eat(lifeform);

                                if (isEaten) {
                                    if (lifeform instanceof Animal animalEaten) {
                                        if (location.getAnimals().contains(animalEaten)) {
                                            Island.getInstance().removeAnimal(animalEaten, location.getRow(), location.getColumn());
                                        }
                                        LifeformsEaten.add(animalEaten);
                                        animalsEaten++;
                                    } else {
                                        Plant plant = (Plant) lifeform;
                                        if (location.getPlants().size() > 0) {
                                            Island.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                iterator.remove();
            }
        } else if (countAnimalsStart == 0) {
            System.out.printf("На %d день все живое погибло!", Statistic.getCurrentDay());
            Island_Simulator.getInstance().getExecutorService().shutdown();
            System.exit(0);
        } else {
            System.out.printf("На %d день в живых остались только гусеницы!", Statistic.getCurrentDay());
            Island_Simulator.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
        latch.countDown();
    }

    public int getAnimalsEaten() {
        return animalsEaten;
    }
}
