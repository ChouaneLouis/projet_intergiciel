package Hagent.Commun;

public interface ExceptionRecord extends java.io.Serializable {

    public Exception getException();

    public String getAgentName();

    public int getState();
}
