package life_forms.animals;



import simulator.lifeCheck;
import island.Island;
import island.Location;

import life_forms.Lifeform;
import life_forms.plants.Plant;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Lifeform {
    private final int step;
    private final double maxHp; // Максимальное количество пищи для насыщения
    private double hp; // Количество здоровья животного

    public Animal(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, maxPopulation, name);
        this.step = step;
        this.maxHp = maxHp;
        this.hp = maxHp; // На старте максимальное количество здоровья
    }


    public boolean eat(Object food) { // проверка на сьедаемость
        double chanceToEat;
        Lifeform Lifeform = null;
        boolean animalEatFood;

        if (food instanceof Lifeform) {
            Lifeform = (Lifeform) food;
        } else {
            try {
                throw new lifeCheck("Объект не является животным/растением.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String foodName = Lifeform.getName();
        chanceToEat = getChanceToEat(foodName);

        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        if (animalEatFood) {
            setHp(Math.min((getHp() + Lifeform.getWeight()), getMaxHp())); // Показатель здоровья повышается после съедения
            Location location = Island.getInstance().getLocation(Lifeform.getRow(), Lifeform.getColumn()); // Животное/растение удаляется из списка обиталей локации после съедения
            if (Lifeform instanceof Animal animal) {
                if (location.getAnimals().contains(animal)) {
                    Island.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            } else {
                Plant plant = (Plant) Lifeform;
                if (location.getPlants().size() > 0) {
                    Island.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            }
        }
        return animalEatFood;
    }


    public abstract double getChanceToEat(String foodName); // шанс на сьедение


    public abstract void multiply(Animal partner); // размножение

    public void move() { // перемещает животное на случайное количество клеток в случайном направлении
        Random random = new Random();
        int randomCells = random.nextInt(getStep()) + 1;
        int direction = random.nextInt(4);
        int newRow = getRow();
        int newColumn = getColumn();
        switch (direction) {
            case 0 ->
                    newRow -= randomCells;
            case 1 ->
                    newRow += randomCells;
            case 2 ->
                    newColumn -= randomCells;
            case 3 ->
                    newColumn += randomCells;
        }
        // Проверяем, чтобы новые координаты не выходили за границы поля
        while (newRow < 0 || newRow >= Island.getInstance().getNumRows() || newColumn < 0 || newColumn >= Island.getInstance().getNumColumns()) {
            randomCells = random.nextInt(getStep()) + 1;
            direction = random.nextInt(4);

            newRow = getRow();
            newColumn = getColumn();
            switch (direction) {
                case 0 -> // Вверх
                        newRow -= randomCells;
                case 1 -> // Вниз
                        newRow += randomCells;
                case 2 -> // Влево
                        newColumn -= randomCells;
                case 3 -> // Вправо
                        newColumn += randomCells;
            }
        }
        Island.getInstance().removeAnimal(this, getRow(), getColumn());
        // Обновляем новые координаты
        setRow(newRow);
        setColumn(newColumn);
        Island.getInstance().addAnimal(this, newRow, newColumn);
    }

    public int getStep() {
        return step;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getMaxHp() {
        return maxHp;
    }
}