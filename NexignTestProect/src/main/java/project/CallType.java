package project;

public enum CallType {
    OUTGOING("01"),
    INCOMING("02");
    private String code;
    CallType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
