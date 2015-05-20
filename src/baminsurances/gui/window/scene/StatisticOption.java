package baminsurances.gui.window.scene;

/**
 * Created by baljit on 19.05.2015.
 */
public enum StatisticOption {

    OPTION_ONE("Antall forsikeringer per kjønn"),
    OPTION_TWO("Antall forsikeringer per Type"),
    OPTION_THREE("Antall forsikeringer per aldersgruppe"),
    OPTION_FOUR("Antall forsikeringer per landsdel"),
    OPTION_FIVE("Antall forsikeringer per"),
    OPTION_SIX("Sum innbetalinger per år"),
    OPTION_SEVEN("Antall kunder per Alders gruppe"),
    OPTION_EIGHT("Antall forsikeringer per landsdel"),
    OPTION_NINE("Sum inn betaling per År");
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
