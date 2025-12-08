package src.Hagent;

public class ExceptionRecord {
    private String name;
    private StackTraceElement[] callstack;

    public ExceptionRecord(String n, StackTraceElement[] cs) {
        this.name = n;
        this.callstack = cs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StackTraceElement[] getCallstack() {
        return callstack;
    }

    public void setCallstack(StackTraceElement[] callstack) {
        this.callstack = callstack;
    }

}
