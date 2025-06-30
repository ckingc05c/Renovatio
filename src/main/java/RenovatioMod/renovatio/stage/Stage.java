package RenovatioMod.renovatio.stage;

public enum Stage {
    NOVICE(1, "Novice"),
    NORMAL(2, "Normal"),
    ADEPT(3, "Adept"),
    ADVANCED(4, "Advanced"),
    ELITE(5, "Elite"),
    VETERAN(6, "Veteran"),
    EXPERT(7, "Expert"),
    CHAMPION(8, "Champion"),
    MASTER(9, "Master"),
    ASCENDANT(10, "Ascendant");

    private final int id;
    private final String displayName;

    Stage(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Stage fromId(int id) {
        for (Stage stage : values()) {
            if (stage.id == id) return stage;
        }
        return NOVICE; // default fallback
    }

    public static Stage next(Stage current) {
        int nextId = current.id + 1;
        return fromId(nextId);
    }
}
