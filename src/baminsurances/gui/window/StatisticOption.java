package baminsurances.gui.window;

/**
 * Created by baljit on 18.05.2015.
 */
public enum StatisticOption {
    OPTION_ONE("OPTION_ONE"),
    OPTION_TWO("OPTION_TWO"),
    OPTION_THREE("OPTION_THREE"),
    OPTION_FOUR("OPTION_FOUR"),
    OPTION_FIVE("OPTION_FIVE"),
    OPTION_SIX("OPTION_SIX");
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
