package environment;

import application.PestAttackAction;
import io.GardenLogger;
import plant.Plant;
import plant.PlantGroup;

public class PestAttackEvent extends Event {
    final private PlantGroup plantGroup;
    private int plotIndex;
    private int numOfPest;
    private String pest;
    private PestAttackAction pestAttackAction;
    public PestAttackEvent(PlantGroup plantGroup, int plotIndex, int numOfPest, String pest) {
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
            plantGroup.setPest(pest, numOfPest);

            GardenLogger.log("Event", numOfPest + " " + pest + " attack to " + plantGroup.getName() + " in plot " + (plotIndex + 1));
            // UI change for pest attack
            if (pestAttackAction != null) {
                pestAttackAction.run(plotIndex, pest);
            }
        } else {
            GardenLogger.log("Event", numOfPest + " " + pest + " attack to the garden, but no plant is attacked");
        }
    }
    public void setOnPestAttack(PestAttackAction action) {
        this.pestAttackAction = action;
    }
}
