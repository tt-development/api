package ttdev.api.utility;

public class LockableAction {

    private Type type;
    private Object action;

    public LockableAction(Type type, Object action) {
        this.type = type;
        this.action = action;
    }

    public Type getType() {
        return type;
    }

    public Object getAction() {
        return action;
    }

    public enum Type {
        COMMAND, VARIABLE
    }
}
