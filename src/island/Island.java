package island;

import life_forms.animals.Animal;
import life_forms.plants.Plant;
import java.util.ArrayList;
import java.util.List;

public class Island {
    private Location[][] locations; // Двумерный массив состоящий из локаций(ячеек)
    private final int numRows = 100;
    private final int numColumns = 20;
    private static volatile Island instance;

    private Island() { // для создания одного обьекта
    }


    public static Island getInstance() { // получение экземпляра класса
        if (instance == null) {
            synchronized (Island.class) {
                if (instance == null) {
                    instance = new Island();
                }
            }
        }
        return instance;
    }


    public void initializeLocations(int numRows, int numColumns) { // инициализация локаций на острове.
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    public void initializeLocations() {
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }


    public synchronized Location getLocation(int row, int column) {// получение локации (ячейки) по заданным координатам
        return locations[row][column];
    }


    public void addAnimal(Animal animal, int row, int column) { // добавить животного в указанную локацию
        Location location = getLocation(row, column);
        location.addAnimal(animal);
    }


    public void removeAnimal(Animal animal, int row, int column) {// удалить животного в указанную локацию
        Location location = getLocation(row, column);
        location.removeAnimal(animal);
    }


    public void addPlant(Plant plant, int row, int column) { // добавить растения в указанную локацию
        Location location = getLocation(row, column);
        location.addPlant(plant);
    }


    public void removePlant(Plant plant, int row, int column) { // удалить растения в указанную локацию
        Location location = getLocation(row, column);
        location.removePlant(plant);
    }


    public synchronized List<Animal> getAllAnimals() {  // получить список всех животных на острове
        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allAnimals.addAll(location.getAnimals());
            }
        }
        return allAnimals;
    }


    public List<Plant> getAllPlants() {                // получить список всех растений на острове
        List<Plant> allPlants = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allPlants.addAll(location.getPlants());
            }
        }
        return allPlants;
    }
    public int getNumRows() {
        return numRows;
    }
    public int getNumColumns() {
        return numColumns;
    }
}
