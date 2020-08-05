package example;

public class Cat extends AbstractAnimal {

    public Cat(double weight, String name, Colors colors) {
        this.weight = weight;
        this.name = name;
        this.colors = colors;
        maxWeight = weight * 1.5;
        voice = new StrategyVoiceMeow();
        eat = new StrategyEatCat();
        restroom = new StrategyRestroomCat();
    }

    public Cat() {

    }


    @Override
    public StrategyVoice voice() {
        weight = weight - 1;
        return voice;
    }

    @Override
    public void cloneAnimal() {
        super.cloneAnimal(new Cat());
    }

    @Override
    public StrategyEat feed(Double amount) {
        weight = weight + amount;
        return eat;
    }

    @Override
    public StrategyRestroom restroom() {
        return restroom;
    }
}