package simulator.thread.animal_Life_cycle;

import simulator.thread.animal_Life_cycle.hp_decrease;
import simulator.thread.animal_Life_cycle.hp_decrease;
import simulator.thread.animal_Life_cycle.Move;
import simulator.thread.animal_Life_cycle.Reproduction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Life_Cycle implements Runnable { // жизненный цикл животных
    private final hp_decrease hp_decrease;
    private final Reproduction reproduction;
    private final Eat eat;
    private final CountDownLatch latch;

    public Life_Cycle() {
        latch = new CountDownLatch(3);
        hp_decrease = new hp_decrease(latch);
        reproduction = new Reproduction(latch);
        eat = new Eat(latch);;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(eat); // животное ест
        executorService.submit(reproduction); // животное размножается
        executorService.submit(hp_decrease); // уменьшение здоровья
        try {
            latch.await(); // ожидаем, пока CountDownLatch не достигнет нуля
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.submit(new Move()); // животные двигаются на другие локации
    }

    public Reproduction getReproduction() {
        return reproduction;
    }

    public hp_decrease gethp_decrease() {
        return hp_decrease;
    }

    public Eat getEat() {
        return eat;
    }
}
