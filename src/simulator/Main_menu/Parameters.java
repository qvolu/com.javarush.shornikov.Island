package simulator.Main_menu;

import island.Island;
import simulator.Island_Simulator;

import java.util.Scanner;


public class Parameters {

    public void changeParameters() {
        changeIslandSize();
        int countCarnivorous = changecarnivorousSize();
        int countHerbivores = changeHerbivoresSize();
        int countPlants = changePlantsSize();

        if (countHerbivores == 0) {
            countHerbivores = Island_Simulator.getInstance().getCountHerbivores();
        }
        if (countCarnivorous == 0) {
            countCarnivorous = Island_Simulator.getInstance().getCountCarnivorous();
        }
        if (countPlants == 0) {
            countPlants = Island_Simulator.getInstance().getCountPlants();
        }

        Island_Simulator.getInstance().createIslandModel(countHerbivores, countCarnivorous, countPlants);
    }


    private void changeIslandSize() {  // изменения размера острова
        System.out.println("Изменить размеры острова?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Введите номер режима: ");
        int answer = takeInt(1, 2);
        if (answer == 1) {
            System.out.println("Новые размеры острова:");
            System.out.println("Количество строк:");
            int rows = takeInt(100, 1000);
            System.out.println("Количество столбцов:");
            int columns = takeInt(100, 1000);
            Island.getInstance().initializeLocations(rows, columns);
        } else {
            Island.getInstance().initializeLocations();
        }
    }


    private int changecarnivorousSize() {
        System.out.println("Изменить количество хищников?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Введите номер режима: ");
        int countCarnivorous = 0;
        int answer = takeInt(1, 2);
        if (answer == 1) {
            System.out.println("Введите новое количество от 10 до 1000!");
            System.out.println("Количество хищников:");
            countCarnivorous = takeInt(10, 1000);
        }
        return countCarnivorous;
    }

    private int changeHerbivoresSize() {
        System.out.println("Изменить количество травоядных?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Введите номер режима: ");
        int countHerbivores = 0;
        int answer = takeInt(1, 2);

        if (answer == 1) {
            System.out.println("Введите новое количество от 20 до 1000!");
            System.out.println("Количество травоядных:");
            countHerbivores = takeInt(20, 1000);
        }
        return countHerbivores;
    }


    private int changePlantsSize() {
        System.out.println("Изменить количество растений?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Введите номер режима: ");
        int countPlants = 0;
        int answer = takeInt(1, 2);

        if (answer == 1) {
            System.out.println("Новое количество от 1 до 500!");
            System.out.println("Количество растений:");
            countPlants = takeInt(1, 500);
        }
        return countPlants;
    }


    public int takeInt(int lowNum, int highNum) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number >= lowNum && number <= highNum) {
                    return number;
                } else {
                    System.out.println("Ошибка! Введенное число не находится в заданном диапазоне. Попробуйте еще раз:");
                }
            } else {
                scanner.next();
                System.out.println("Ошибка! Введено некорректное значение. Попробуйте еще раз:");
            }
        }
    }
}
