package environment;

import plant.Plant;

import java.util.List;

public class PestAttackEvent extends Event {
    final private List<Plant> plantGroup;
    private int plotIndex;
    private int numOfPest;
    private String pest;
    public PestAttackEvent(List<Plant> plantGroup, int plotIndex, int numOfPest, String pest) {
        super("PestAttackEvent");
        this.plantGroup = plantGroup;
        this.plotIndex = plotIndex;
        this.numOfPest = numOfPest;
    }

    /**
     * Trigger pest attack event, set the target plant to be under attack.
     * If there is no plant in the garden, print out the message accordingly
     */
    public void trigger() {
        if (plantGroup != null) {
            for (Plant plant: plantGroup) {
                plant.setUnderAttack(true);
            }
            System.out.println("Event: " + numOfPest + " " + pest + "attack to " + plantGroup.get(0).getName() + "in plot " + plotIndex);
        } else {
            System.out.println("Event: " + numOfPest + " " + pest + "attack to the garden, but no plant is attacked");
        }
    }
}
