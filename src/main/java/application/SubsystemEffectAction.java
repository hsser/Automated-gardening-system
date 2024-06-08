package application;

public interface SubsystemEffectAction {
    void run(String type); // Can be "sprinkler", "heater", or "cooler".
}
