package es.projectalpha.ac.jobs;

public class Car extends Job {

    public Car(int level){
        super("Car Wash", 720, level);

        setProductionReward(540);
        setProductionTime(6);

        setCoefficient(1.14);

        setUpgradeBase(getCost());
    }
}