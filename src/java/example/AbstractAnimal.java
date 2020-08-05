package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/****
 *
 * перевести на строителя или нет, или да, нид подумать
 *
 */

public abstract class AbstractAnimal implements Animal {

    protected static List<Animal> animalsList = new ArrayList<Animal>();

    protected String name;

    protected StrategyVoice voice;
    protected StrategyEat eat;
    protected StrategyRestroom restroom;

    protected boolean isClone = false;

    //    private double originWeight;
    protected double weight;

    protected Colors colors;
    protected static final double MIN_WEIGHT = 800;
    protected double maxWeight;


    public static List<Animal> getAnimalsList() {
        return animalsList;
    }

    public static void createRandomAnimal() {

        //тут должна быть логика рандома животного и прочее,
        //но это как нибудь в другой раз, так что просто рандом котов

        String name = String.valueOf((int) (1000 * Math.random()));
        double weight = (1000 + 9000 * Math.random());
        Colors[] colors = Colors.values();

        Random random = new Random();

        //псевдорандом раскраса
        int i = random.nextInt(colors.length);
        Cat cat = new Cat(weight, name, colors[i]);

        animalsList.add(cat);
    }

    public static void createCat(String catNameStr, double catWeight, Colors colors) {
        Cat cat = new Cat(catWeight, catNameStr, colors);
        animalsList.add(cat);
    }

    // реализовать через геттеры и сеттеры
    void cloneAnimal(AbstractAnimal animal) {
        animal.name = this.name;
        animal.voice = this.voice;
        animal.eat = this.eat;
        animal.restroom = this.restroom;
        animal.weight = this.weight;
        animal.colors = this.colors;
        animal.maxWeight = this.maxWeight;
        animal.isClone = true;
        animalsList.add(animal);
    }


    public abstract StrategyVoice voice();/* {
        weight = weight - 1;
        System.out.println("Meow");
    }*/

    public abstract StrategyEat feed(Double amount);
    /*
    public void drink(Double amount) {
        weight = weight + amount;
    }*/


    public abstract StrategyRestroom restroom();

    public double getWeight() {
        return weight;
    }

    public boolean getStatus() {
        if (weight < MIN_WEIGHT) {
            return false;
        } else if (weight > maxWeight) {
            return false;
        }/* else if (weight > originWeight) {
            return "Sleeping";
        }  else {
            return "Playing";
        }*/
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Colors getColor() {
        return colors;
    }

    @Override
    public double getMaxWeight() {
        return maxWeight;
    }

    @Override
    public double getMinWeight() {
        return MIN_WEIGHT;
    }

    @Override
    public boolean getIsClone() {
        return isClone;
    }
}
