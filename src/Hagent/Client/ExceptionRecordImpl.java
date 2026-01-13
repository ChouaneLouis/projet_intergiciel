package Hagent.Client;

public class ExceptionRecordImpl implements Hagent.Commun.ExceptionRecord {
    private Exception exception;
    private String agentName;
    private int state;

    public ExceptionRecordImpl(Exception exception, String agentName, int state) {
        this.exception = exception;
        this.agentName = agentName;
        this.state = state;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getAgentName() {
        return agentName;
    }

    @Override
    public int getState() {
        return state;
    }
}
