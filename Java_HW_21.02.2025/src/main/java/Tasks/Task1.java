package Tasks;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


class Passenger {
    private final long arrivalTime;

    public Passenger(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }
}

class Boat {
    private final boolean isFinalStop;
    private final int capacity;

    public Boat(boolean isFinalStop, int capacity) {
        this.isFinalStop = isFinalStop;
        this.capacity = capacity;
    }

    public boolean isFinalStop() {
        return isFinalStop;
    }

    public int getCapacity() {
        return capacity;
    }
}

class BoatDockSimulation {
    private final Queue<Passenger> dockQueue = new LinkedList<>();
    private final Random random = new Random();

    private final int maxPeopleOnDock;
    private final int avgPassengerInterval;
    private final int avgBoatInterval;

    public BoatDockSimulation(int maxPeopleOnDock, int avgPassengerInterval, int avgBoatInterval) {
        this.maxPeopleOnDock = maxPeopleOnDock;
        this.avgPassengerInterval = avgPassengerInterval;
        this.avgBoatInterval = avgBoatInterval;
    }

    public void runSimulation(int simulationDuration) {
        long currentTime = 0;
        int totalWaitTime = 0;
        int totalPassengers = 0;

        while (currentTime < simulationDuration) {
            System.out.println("Час: " + currentTime + " | Пасажирів на причалі: " + dockQueue.size());

            if (random.nextInt(avgPassengerInterval) == 0) {
                dockQueue.add(new Passenger(currentTime));
                System.out.println("+ Новий пасажир прибув на причал.");
            }

            if (random.nextInt(avgBoatInterval) == 0) {
                int boatCapacity = random.nextInt(20) + 1;
                Boat boat = new Boat(random.nextBoolean(), boatCapacity);
                System.out.println(" * Катер прибув. Місткість: " + boatCapacity);

                int boardedPassengers = 0;
                while (!dockQueue.isEmpty() && boardedPassengers < boat.getCapacity()) {
                    Passenger passenger = dockQueue.poll();
                    totalWaitTime += (currentTime - passenger.getArrivalTime());
                    totalPassengers++;
                    boardedPassengers++;
                }
                System.out.println( boardedPassengers + " пасажирів сіли в катер.");
            }

            currentTime++;
        }

        if (totalPassengers > 0) {
            System.out.println("\n-- Середній час перебування людини на зупинці: " + (totalWaitTime / totalPassengers) + " хвилин.");
        } else {
            System.out.println("\n-- Немає достатньо пасажирів для обчислення середнього часу.");
        }
        System.out.println("-- Людей, що залишилися на зупинці: " + dockQueue.size());
    }
}

public class Task1 {
    public static void main(String[] args) {
        BoatDockSimulation simulation = new BoatDockSimulation(50, 5, 15);
        simulation.runSimulation(1000);
    }
}

