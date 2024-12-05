package simulator.thread;

import simulator.Island_Simulator;



public class PlantsGrowth implements Runnable {
    @Override
    public void run() {
        int countPlants = 20;
        if (Island_Simulator.getInstance().getTimeNow() >= 2) {
            Island_Simulator.getInstance().placePlants(countPlants / 2);
        } else {
            Island_Simulator.getInstance().placePlants(countPlants);
        }
    }
}
