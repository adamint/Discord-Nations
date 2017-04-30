package execution;

public abstract class Command {
    private String[] aliases;
    private String description;
    private String usage;
    public Command(String... aliases) {
        this.aliases = aliases;
    }
    public Command setUsage(String s) {
        usage = s;
        return this;
    }
    public Command setDescription(String s) {
        description = s;
        return this;
    }
    public boolean containsAlias(String s) {

    }
}
