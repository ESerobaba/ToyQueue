import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

class Toy {
    private int id;
    private String name;
    private int frequency;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public int getFrequency() {
        return frequency;
    }

    public String toString() {
        return "Toy -> id=" + id + ", name='" + name + "', frequency=" + frequency;
    }
}

public class ToyQueue {

    private PriorityQueue<Toy> toyQueue = new PriorityQueue<>((t1, t2) -> t2.getFrequency() - t1.getFrequency());

    public void put(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length == 3) {
            try {
                int id = Integer.parseInt(tokens[0]);
                int frequency = Integer.parseInt(tokens[1]);

                Toy toy = new Toy(id, tokens[2], frequency);
                toyQueue.offer(toy);
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формар: " + input);
            }
        } else {
            System.out.println("Неправильный формар: " + input);
        }
    }

    public int get() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;

        if (randomNumber <= 20) {
            return 1;
        } else if (randomNumber <= 40) {
            return 2;
        } else {
            return 3;
        }
    }

    public void writeResultsToFile() {
        try (FileWriter writer = new FileWriter("results.txt")) {
            for (int i = 0; i < 10; i++) {
                int result = get();
                Toy selectedToy = null;

                for (Toy toy : toyQueue) {
                    if (toy.getId() == result) {
                        selectedToy = toy;
                        break;
                    }
                }

                if (selectedToy != null) {
                    writer.write(selectedToy.toString() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ToyQueue toyQueue = new ToyQueue();

        toyQueue.put("1 2 конструктор");
        toyQueue.put("2 2 робот");
        toyQueue.put("3 6 кукла");

        toyQueue.writeResultsToFile();
    }
}
