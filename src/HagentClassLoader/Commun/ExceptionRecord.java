package HagentClassLoader.Commun;

public class ExceptionRecord implements java.io.Serializable {
    private Exception exception;
    private String agentName;
    private int state;

    public ExceptionRecord(Exception exception, String agentName, int state) {
        this.exception = exception;
        this.agentName = agentName;
        this.state = state;
    }

    public Exception getException() {
        return exception;
    }

    public String getAgentName() {
        return agentName;
    }

    public int getState() {
        return state;
    }
}
