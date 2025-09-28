package ckingc05c.renovatio.stage;

/**
 * Represents the different stages of the game.
 * Each stage has an ID and a display name.
 */
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

    /**
     * Constructs a new Stage.
     * @param id The ID of the stage.
     * @param displayName The display name of the stage.
     */
    Stage(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    /**
     * Gets the ID of the stage.
     * @return The ID of the stage.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the display name of the stage.
     * @return The display name of the stage.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets a stage from its ID.
     * @param id The ID of the stage to get.
     * @return The stage with the given ID, or NOVICE if no stage has that ID.
     */
    public static Stage fromId(int id) {
        for (Stage stage : values()) {
            if (stage.id == id) return stage;
        }
        return NOVICE; // default fallback
    }

    /**
     * Gets the next stage after the given stage.
     * @param current The current stage.
     * @return The next stage.
     */
    public static Stage next(Stage current) {
        int nextId = current.id + 1;
        return fromId(nextId);
    }
}
