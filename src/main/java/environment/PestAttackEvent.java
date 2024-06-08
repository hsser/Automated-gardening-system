package environment;

import application.PestAttackAction;
import plant.Plant;

import java.util.List;

public class PestAttackEvent extends Event {
    final private List<Plant> plantGroup;
    private int plotIndex;
    private int numOfPest;
    private String pest;
    private PestAttackAction pestAttackAction;
    public PestAttackEvent(List<Plant> plantGroup, int plotIndex, int numOfPest, String pest) {
        super("PestAttackEvent");
        this.plantGroup = plantGroup;
        this.plotIndex = plotIndex;
        this.numOfPest = numOfPest;
        this.pest = pest;
    }

    /**
     * Trigger pest attack event, set the target plant to be under attack.
     * If there is no plant in the garden, print out the message accordingly
     */
    public void trigger() {
        if (plantGroup != null) {
            for (Plant plant: plantGroup) {
                plant.setPest(pest, numOfPest);
            }
            System.out.println("Event: " + numOfPest + " " + pest + " attack to " + plantGroup.get(0).getName() + " in plot " + plotIndex);
            // UI change for pest attack
            if (pestAttackAction != null) {
                pestAttackAction.run(plotIndex, pest);
            }
        } else {
            System.out.println("Event: " + numOfPest + " " + pest + " attack to the garden, but no plant is attacked");
        }
    }
    public void setOnPestAttack(PestAttackAction action) {
        this.pestAttackAction = action;
    }
}
