package simulator;

import island.Island;
import island.Location;
import life_forms.animals.herbivores.*;
import life_forms.animals.carnivorous.*;
import life_forms.plants.Plant;
import simulator.thread.PlantsGrowth;
import simulator.thread.Statistic;
import simulator.thread.animal_Life_cycle.Life_Cycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class Island_Simulator {
    private final long startTime;
    private final int countHerbivores = 35;
    private final int countPlants = 40;
    private final int countCarnivorous = 20;
    private static volatile Island_Simulator instance;
    private volatile ScheduledExecutorService executorService;

    private Island_Simulator() {
        startTime = System.currentTimeMillis();
    }


    public static Island_Simulator getInstance() {
        if (instance == null) {
            synchronized (Island_Simulator.class) {
                if (instance == null) {
                    instance = new Island_Simulator();
                }
            }
        }
        return instance;
    }


    public void createIslandModel(int countHerbivores, int countCarnivorous, int countPlants) { // создать остров с заданными параметрами
        placeHerbivores(countHerbivores);
        placeCarnivorous(countCarnivorous);
        placePlants(countPlants);

        runIslandModel();
    }


    public void createIslandModel() { // создать остров с параметрами по умолчанию
        placeHerbivores(countHerbivores);
        placeCarnivorous(countCarnivorous);
        placePlants(countPlants);

        runIslandModel();
    }


    private void runIslandModel() {  // запустить остров
        executorService = Executors.newScheduledThreadPool(3);

        Life_Cycle animalLifecycle = new Life_Cycle();
        PlantsGrowth plantsGrowth = new PlantsGrowth();
        Statistic statistic = new Statistic(animalLifecycle.gethp_decrease(), animalLifecycle.getEat(), animalLifecycle.getReproduction());

        executorService.scheduleAtFixedRate(animalLifecycle, 1, 8, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(plantsGrowth, 40, 30, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(statistic, 0, 8, TimeUnit.SECONDS);
    }


    private List<Herbivore> createHerbivores(int countHerbivores) { // создать список травоядных с заданным количеством
        List<Herbivore> herbivores = new ArrayList<>();
        Random random = new Random();

        // создаем по одному животному каждого вида
        herbivores.add(new Buffalo());
        herbivores.add(new Caterpillar());
        herbivores.add(new Deer());
        herbivores.add(new Duck());
        herbivores.add(new Goat());
        herbivores.add(new Horse());
        herbivores.add(new Mouse());
        herbivores.add(new Rabbit());
        herbivores.add(new Sheep());
        herbivores.add(new Wildboar());

        // генерируем случайное количество животных каждого вида, не менее 1
        int remainingCount = countHerbivores - herbivores.size();
        for (int i = 0; i < remainingCount; i++) {
            // генерируем случайный индекс для выбора вида животного
            int randomIndex = random.nextInt(herbivores.size());
            Herbivore randomHerbivore = herbivores.get(randomIndex);
            try {
                // создаем экземпляр животного через рефлексию
                Herbivore newHerbivore = randomHerbivore.getClass().newInstance();
                herbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return herbivores;
    }

    private List<Carnivorous> createCarnivorous(int countCarnivorous) { // создать список хищников с заданным количеством
        List<Carnivorous> carnivorous = new ArrayList<>();
        Random random = new Random();

        // создаем по одному животному каждого вида
        carnivorous.add(new Bear());
        carnivorous.add(new Eagle());
        carnivorous.add(new Fox());
        carnivorous.add(new Snake());
        carnivorous.add(new Wolf());

        // генерируем случайное количество животных каждого вида, не менее 1
        int remainingCount = countCarnivorous - carnivorous.size();
        for (int i = 0; i < remainingCount; i++) {
            // генерируем случайный индекс для выбора вида животного
            int randomIndex = random.nextInt(carnivorous.size());
            Carnivorous randomCarnivorous = carnivorous.get(randomIndex);
            try {
                // создаем экземпляр животного через рефлексию
                Carnivorous newCarnivorous = randomCarnivorous.getClass().newInstance();
                carnivorous.add(newCarnivorous);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return carnivorous;
    }


    private List<Plant> createPlants(int countPlants) { // создать список растений с заданным количеством
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant());
        }
        return plants;
    }


    public void placeHerbivores(int countHerbivores) { // разместить травоядных на острове
        List<Herbivore> herbivores = createHerbivores(countHerbivores);
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(Island.getInstance().getNumRows());
                int column = random.nextInt(Island.getInstance().getNumColumns());
                Location location = Island.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(herbivore.getName())).toList().size() <= herbivore.getMaxPopulation()) {
                    Island.getInstance().addAnimal(herbivore, row, column);
                    placed = true;
                }
            }
        }
    }


    public void placeCarnivorous(int countCarnivorous) {  // разместить хищников на острове
        List<Carnivorous> carnivorous = createCarnivorous(countCarnivorous);

        Random random = ThreadLocalRandom.current();
        for (Carnivorous carnivorous1 : carnivorous) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(Island.getInstance().getNumRows());
                int column = random.nextInt(Island.getInstance().getNumColumns());
                Location location = Island.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(carnivorous1.getName())).toList().size() <= carnivorous1.getMaxPopulation()) {
                    Island.getInstance().addAnimal(carnivorous1, row, column);
                    placed = true;
                }
            }
        }
    }


    public void placePlants(int countPlants) {  // разместить растения на острове
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(Island.getInstance().getNumRows());
                int column = random.nextInt(Island.getInstance().getNumColumns());
                Location location = Island.getInstance().getLocation(row, column);
                if (location.getPlants().size() <= plant.getMaxPopulation()) {
                    Island.getInstance().addPlant(plant, row, column);
                    placed = true;
                }
            }
        }
    }


    public long getTimeNow() { // получить текущее время симуляции
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public int getCountHerbivores() {
        return countHerbivores;
    }

    public int getCountPlants() {
        return countPlants;
    }

    public int getCountCarnivorous() {return countCarnivorous;
    }
}
