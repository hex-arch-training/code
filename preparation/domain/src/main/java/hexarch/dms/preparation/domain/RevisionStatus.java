package hexarch.dms.preparation.domain;

public enum RevisionStatus {

    EDITABLE("E"),
    LOCKED("L");

    private final String dbValue;

    RevisionStatus(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static RevisionStatus fromDb(String db) {
        return switch(db) {
            case "E" -> EDITABLE;
            case "L" -> LOCKED;
            default -> throw new RuntimeException("blee");
        };
    }

}
