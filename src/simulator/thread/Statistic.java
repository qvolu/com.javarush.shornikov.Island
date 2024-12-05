package simulator.thread;

import island.Island;
import simulator.Island_Simulator;
import simulator.thread.animal_Life_cycle.hp_decrease;
import simulator.thread.animal_Life_cycle.Eat;
import simulator.thread.animal_Life_cycle.Reproduction;




public class Statistic implements Runnable {
    private boolean isTimeOver;
    private int babies;
    private int animalsEaten;
    private int animalsDiedByHungry;
    private int countAnimalsEnd;
    private int countPlants;
    private static int currentDay = 0;
    private final Reproduction reproduction;
    private final hp_decrease hpDecrease;
    private final Eat eat;

    public Statistic(hp_decrease hpDecrease, Eat Eat, Reproduction reproduction) {
        this.hpDecrease = hpDecrease;
        this.eat = Eat;
        this.reproduction = reproduction;
    }

    @Override
    public void run() {
        long timeNow = Island_Simulator.getInstance().getTimeNow();
        isTimeOver = checkTime(timeNow);

        babies = reproduction.getBabies();
        countAnimalsEnd = Island.getInstance().getAllAnimals().size(); // кол-во животных на острове
        animalsEaten = eat.getAnimalsEaten(); // кол-во животных съедено
        animalsDiedByHungry = hpDecrease.getAnimalsDiedByHungry(); // кол-во животных умерло
        countPlants = Island.getInstance().getAllPlants().size();
        printStats();

        if (isTimeOver) {
            Island_Simulator.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
    }


    private boolean checkTime(long timeNow) {
        return timeNow / 60 >= 5;
    }



    private void printStats() {  //   вывод статистики
        if (isTimeOver) {
            System.out.println("Финальная статистика по истечению времени");
            System.out.println("--".repeat(20));
        } else {
            System.out.printf("--- ДЕНЬ %d --- \n", currentDay);
            currentDay++;

        }

        System.out.println("\n СТАТИСТИКА \n");


        System.out.print("Всего животных: ");
        System.out.println(countAnimalsEnd);

        System.out.print("Всего растений: ");
        System.out.println(countPlants);


        System.out.print("Животных умерло от голода: ");
        System.out.println(animalsDiedByHungry);

        System.out.print("Животных съедено: ");
        System.out.println(animalsEaten);

        System.out.print("Новых животных родилось:");
        System.out.println(babies);


        System.out.println("-".repeat(30));
        System.out.println();
    }

    public static int getCurrentDay() {
        return currentDay;
    }
}
