package example;

public interface Animal {
    public boolean getStatus();

    public String getName();

    public Colors getColor();

    public StrategyEat feed(Double amount);

    public StrategyRestroom restroom();

    //public void drink(Double amount);

    public StrategyVoice voice();

    public void cloneAnimal();

    public double getMaxWeight();

    public double getWeight();

    public double getMinWeight();

    public boolean getIsClone();
}
