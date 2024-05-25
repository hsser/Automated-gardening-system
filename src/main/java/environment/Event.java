package environment;

public abstract class Event {
    private String name;

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void trigger();
}
