package Organism;

public abstract class Organism {
    private String name; // name of the organism
    private double health; // health of the organism
    private double maxHealth = 100; // maximum health of the organism

    public Organism(String name) {
        this.name = name;
        this.health = maxHealth;
    }

    /**
     * Getters for the organism's name, health, and alive status.
     */
    public String getName() { return name; }
    public double getHealth() { return health; }
    public boolean isAlive() { return health > 0; }

    /**
     * Getters for the organism's status as a predator or beneficial organism.
     * @return true if the organism is a predator, false otherwise.
     * @return true if the organism is beneficial, false otherwise.
     *
     * Organisms that are predators or not beneficial should override these methods.
     */
    public boolean isPredator() { return false; }
    public boolean isBeneficial() { return true; }

    /**
     * Decreases the health of the organism. If the health drops to zero or below, the organism dies.
     * @param damage the amount of health to decrease.
     */
    public void decreaseHealth(double damage) {
        if (!this.isAlive())
            return;

        if (this.health > damage) {
            this.health -= damage;
        }
        else {
            this.die();
        }
    }

    /**
     * Increases the health of the organism, not exceeding the maximum health.
     * @param amount the amount of health to increase.
     */
    public void increaseHealth(double amount) {
        if(this.isAlive() && this.health < this.maxHealth ) {
            this.health += amount;
            if (this.health > maxHealth) {
                this.health = maxHealth;
            }
        }
    }

    /**
     * Marks the organism as dead and sets health to zero.
     */
    public void die() {
        this.health = 0;
        System.out.println(name + " has died.");
    }
}
