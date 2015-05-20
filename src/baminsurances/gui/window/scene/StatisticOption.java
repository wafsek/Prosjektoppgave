package baminsurances.gui.window.scene;

/**
 * Created by baljit on 19.05.2015.
 */
public enum StatisticOption {

    OPTION_ONE("Antall forsikringer per kj�nn"),
    OPTION_TWO("Antall forsikringer per type"),
    OPTION_THREE("Antall forsikringer per landsdel"),
    OPTION_FOUR("Sum innbetalinger per �r"),
    OPTION_FIVE("Antall kunder per aldersgruppe"),
    OPTION_SIX("Sum innbetalinger per �r fordelt p� forsikringstype");
    private final String description;

    /**
     * Returns the description of this 
     * @param description
     */
    StatisticOption(String description) {
        this.description = description;
    }

    /**
     * Returns a description of this DataControl?
     *
     * @return a description of this DataControl?
     */
    public String getDescription() {
        return this.description;
    }
}
