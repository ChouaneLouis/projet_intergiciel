package src.Hagent;

public class ExceptionRecord {
    private String name;
    private String callstack;

    public ExceptionRecord(String n, String cs) {
        this.name = n;
        this.callstack = cs;
    }
}
