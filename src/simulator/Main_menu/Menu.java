package simulator.Main_menu;

import island.Island;
import simulator.Island_Simulator;


public class Menu {
    private final Parameters parameters;


    public Menu() {
        parameters = new Parameters();
    }


    public void startSimulation() {
        System.out.println("-".repeat(70));
        System.out.println(" ".repeat(30) + "ОСТРОВ\n"+ ("-".repeat(70)) + "\n Размер острова - 100x20 | Хищников - 25| Травоядные - 30|Растения - 10| \n Изменить настройки острова?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        System.out.print("Введите номер режима: ");
        int answer = parameters.takeInt(1, 2);

        if (answer == 1) {
            parameters.changeParameters();
        } else {
            Island.getInstance().initializeLocations();
            Island_Simulator.getInstance().createIslandModel();
        }
        System.out.println("-".repeat(35));
        System.out.println("Загрузка острова...");
        System.out.println("-".repeat(35));

    }
}
