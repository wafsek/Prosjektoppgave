package baminsurances.gui.window.scene;

import baminsurances.api.Searcher;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import baminsurances.logging.CustomLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;

/**
 * The scene that shows statistics of the data.
 * 
 * @author Baljit Sarai
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class StatisticScene extends GeneralScene {
    private VBox leftSide,rightSide;
    private VBox topBar;
    private Label topLabel;
    private LineChart lineChart;
    private PieChart pieChart;
    private BarChart barChart;
    private BorderPane borderPane;
    private ObservableList<PieChart.Data> pieChartData;
    private XYChart.Series series;
    private XYChart.Series[] seriesArray;
    private ComboBox comboBox;
    private ComboBox animationComboBox;
    private CustomLogger logger;
    private Axis xAxis;
    private Axis yAxis;
    private Axis cAxis;
    
    /**
     * Creates a new statistics scene.
     * 
     * @param guiEventHandler the GUI event handler
     * @param keyPressHandler the key press handler
     */
    public StatisticScene(GuiEventHandler guiEventHandler,
            KeyPressHandler keyPressHandler) {
        super(guiEventHandler,keyPressHandler);
        logger = CustomLogger.getInstance();

        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        cAxis = new CategoryAxis();
        leftSide = new VBox(10);
        rightSide = new VBox(10);
        topBar = new VBox(10);
        lineChart = new LineChart(xAxis,yAxis);
        barChart = new BarChart(cAxis,yAxis);
        pieChartData = FXCollections.observableArrayList();
        pieChart = new PieChart(pieChartData);
        
        this.setComboBox();
        comboBox.setValue(StatisticOption.OPTION_ONE.getDescription());
        this.setAnimationComboBox();
        animationComboBox.setValue("Animasjon PÅ");
        
        topLabel = new Label("Statistikk");
        topLabel.setStyle("-fx-font:50px times;");
        topBar.setAlignment(Pos.CENTER);
        rightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 9 / 10);
        topBar.getChildren().add(topLabel);
        topBar.getChildren().add(comboBox);
        topBar.getChildren().add(animationComboBox);
        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1);
        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");
        rightSide.setAlignment(Pos.CENTER);
        borderPane = new BorderPane(leftSide,topBar,rightSide,footer,null);
        borderPane.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.web("D7EBE6"),
                        CornerRadii.EMPTY, Insets.EMPTY)));

        scene = new Scene(borderPane);
        this.launchOptionOne(StatisticOption.OPTION_ONE);
    }

    /**
     * Sets the animation combo box.
     */
    private void setAnimationComboBox() {
        animationComboBox = new ComboBox(FXCollections.observableArrayList(
                "Animasjon PÅ",
                "Animasjon AV"
        ));

        animationComboBox.setOnAction(e -> {
            if (animationComboBox.getValue() == "Animasjon PÅ") {
                xAxis = new NumberAxis();
                yAxis = new NumberAxis();
                cAxis = new CategoryAxis();
                barChart.setAnimated(true);
                pieChart.setAnimated(true);
                lineChart.setAnimated(true);
            } else if (animationComboBox.getValue() == "Animasjon AV") {
                barChart.setAnimated(false);
                pieChart.setAnimated(false);
                lineChart.setAnimated(false);
            }
        });
    }
    
    /**
     * Sets the combo box with statistics options.
     */
    private void setComboBox() {
        comboBox = new ComboBox(FXCollections.observableArrayList(
                StatisticOption.OPTION_ONE.getDescription(),
                StatisticOption.OPTION_TWO.getDescription(),
                StatisticOption.OPTION_THREE.getDescription(),
                StatisticOption.OPTION_FOUR.getDescription(),
                StatisticOption.OPTION_FIVE.getDescription(),
                StatisticOption.OPTION_SIX.getDescription())

        );
        comboBox.setOnAction(e -> {
            if (comboBox.getValue() == StatisticOption.OPTION_ONE.getDescription()) {
                this.launchOptionOne(StatisticOption.OPTION_ONE);
            } else if (comboBox.getValue() == StatisticOption.OPTION_TWO.getDescription()) {
                this.launchOptionTwo(StatisticOption.OPTION_TWO);
            } else if (comboBox.getValue() == StatisticOption.OPTION_THREE.getDescription()) {
                this.launchOptionThree(StatisticOption.OPTION_THREE);
            } else if (comboBox.getValue() == StatisticOption.OPTION_FOUR.getDescription()) {
                this.launchOptionFour(StatisticOption.OPTION_FOUR);
            } else if (comboBox.getValue() == StatisticOption.OPTION_FIVE.getDescription()) {
                this.launchOptionFive(StatisticOption.OPTION_FIVE);
            }  else if (comboBox.getValue() == StatisticOption.OPTION_SIX.getDescription()) {
                this.launchOptionSix(StatisticOption.OPTION_SIX);
            }
        });
    }
    
    /**
     * Clears the right side of the scene.
     */
    private void clearRightSide() {
        xAxis.setAutoRanging(true);
        rightSide.getChildren().removeAll(pieChart);
        rightSide.getChildren().removeAll(barChart);
        rightSide.getChildren().removeAll(lineChart);
        leftSide.getChildren().removeAll(pieChart);
    }
    
    /**
     * Sets the pie chart, and returns it.
     * 
     * @return the set pie chart
     */
    private PieChart setPieChart() {
        pieChart = new PieChart(pieChartData);
        pieChart.setVisible(true);
        pieChart.getLabelsVisible();
        return pieChart;
    }
    
    /**
     * Sets the bar chart, and returns it.
     * 
     * @return the set bar chart
     */
    private BarChart setBarchart() {
        barChart = new BarChart(cAxis,yAxis);
        barChart.getData().addAll(series);
        barChart.applyCss();
        barChart.setCategoryGap(30);
        cAxis = new CategoryAxis();
        return barChart;
    }
    
    /**
     * Sets the line chart, and returns it.
     * 
     * @return the set line chart
     */
    private LineChart setLineChart() {
        lineChart = new LineChart(xAxis, yAxis);
        lineChart.getData().addAll(series);
        return lineChart;
    }
    
    /**
     * Sets the multi bar chart, and returns it.
     * 
     * @return the set multi bar chart
     */
    private BarChart setMultiBarChart(){
        barChart = new BarChart(cAxis,yAxis);
        for(XYChart.Series series : seriesArray){
            barChart.getData().add(series);
        }
        barChart.setCategoryGap(50);
        return barChart;
    }

    /**
     * Sets the multiline chart, and returns it.
     * 
     * @return the set multiline chart
     */
    private LineChart setMultiLineChart(){
        lineChart = new LineChart<>(xAxis,yAxis);
        for(XYChart.Series series : seriesArray){
            lineChart.getData().add(series);
        }
        return lineChart;
    }




     /****************************************************************************
     *                                                                           *
     *                        Methods representing options                       *
     *                                                                           *
     ****************************************************************************/
    
    /**
     * Shows statistic option one.
     * 
     * @param statisticOption the statistic option
     */
    private void launchOptionOne(StatisticOption statisticOption ) {
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setMultiBarChartData(new Searcher().numInsuranceTypesPerGender(),
                "Antall forsikringer");
        cAxis.setLabel("Kjønn");
        yAxis.setLabel("Antall forsikringer");
        rightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 2 / 3);
        barChart = this.setMultiBarChart();
        barChart.setTitle("Antall forsikringer per kjønn fordelt på forsikringstype");
        rightSide.getChildren().add(barChart);
        this.setPieChartData(new Searcher().numInsurancesPerGender());
        pieChart = this.setPieChart();
        pieChart.setTitle("Totalt antall forsikringer per kjønn");
        leftSide.setAlignment(Pos.CENTER);
        leftSide.getChildren().add(pieChart);

    }

    /**
     * Shows statistic option two.
     * 
     * @param statisticOption the statistic option
     */
    private void launchOptionTwo(StatisticOption statisticOption ) {
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setBarChartData(new Searcher().numInsurancesPerType(),
                "Antall forsikringer");
        cAxis.setLabel("Forsikringstyper");
        yAxis.setLabel("Antall per år");
        rightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        barChart = this.setBarchart();
        barChart.setTitle("Totalt antall forsikringer per type");
        barChart.setTitle(statisticOption.getDescription());
        rightSide.getChildren().add(barChart);
        this.setPieChartData(new Searcher().numInsurancesPerType());
        pieChart = this.setPieChart();
        pieChart.setTitle("Antall forsikringer per type");
        leftSide.setAlignment(Pos.CENTER);
        leftSide.getChildren().add(pieChart);
    }

    /**
     * Shows statistic option three.
     * 
     * @param statisticOption the statistic option
     */
    private void launchOptionThree(StatisticOption statisticOption) {
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setMultiBarChartData(new Searcher().getInsuranceTypesPerRegion(),
                "Antall forsikringer");
        cAxis.setLabel("Landsdel");
        yAxis.setLabel("Antall forsikringer");
        rightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 5 / 8);
        barChart =  this.setMultiBarChart();
        barChart.setTitle(statisticOption.getDescription()+" fordelt på forsikringstype");
        rightSide.getChildren().add(barChart);
        this.setPieChartData(new Searcher().numInsurancesPerRegion());
        pieChart = this.setPieChart();
        pieChart.setTitle("Total antall forsikringer per type");
        leftSide.getChildren().add(pieChart);
    }
    
    /**
     * Shows statistic option four.
     * 
     * @param statisticOption the statistic option
     */
    private void launchOptionFour(StatisticOption statisticOption) {
        this.clearRightSide();
        xAxis = new NumberAxis(1997, LocalDate.now().getYear()-1, 1);
        logger.log("Option one selected ", Level.FINE);
        this.setLineChartData(new Searcher().getSumPaymentsPerYear(),
                "Sum innbetalinger");
        xAxis.setLabel("År");
        yAxis.setLabel("Sum innbetalinger");
        rightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 9/10);
        lineChart = this.setLineChart();
        lineChart.setTitle(statisticOption.getDescription());
        rightSide.getChildren().add(lineChart);
    }
    
    /**
     * Shows statistic option five.
     * 
     * @param statisticOption the statistic option
     */
    private void launchOptionFive(StatisticOption statisticOption){
        this.clearRightSide();
        logger.log("Option Seven selected ", Level.FINE);
        this.setBarChartData(new Searcher().numCustomersPerAgeGroup(), "Antall kunder");
        cAxis.setLabel("Aldersgupper");
        yAxis.setLabel("Antall kunder");
        rightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 5 / 8);
        barChart =  this.setBarchart();
        barChart.setTitle(statisticOption.getDescription());
        rightSide.getChildren().add(barChart);
        this.setPieChartData(new Searcher().numInsurancesPerRegion());
        pieChart = this.setPieChart();
        pieChart.setTitle(statisticOption.getDescription());
        leftSide.getChildren().add(pieChart);
    }
    
    /**
     * Shows statistic option six.
     * 
     * @param statisticOption the statistic option
     */
    private void launchOptionSix(StatisticOption statisticOption){
        this.clearRightSide();
        xAxis = new NumberAxis(1997, LocalDate.now().getYear()-1, 1);
        logger.log("Option one selected ", Level.FINE);
        this.setMultiLineChartData(new Searcher().getPaymentsPerInsuranceTypePerYear(),
                "Sum innbetalinger");
        xAxis.setLabel("År");
        yAxis.setLabel("Sum innbetalinger");
        rightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 9/10);
        lineChart = this.setMultiLineChart();
        lineChart.setTitle(statisticOption.getDescription());
        rightSide.getChildren().add(lineChart);
    }

    /**#######################        END OF OPTIONS           ######################################*/


    /****************************************************************************
     *                                                                           *
     *                     Setting charts with single series                     *
     *                                                                           *
     ****************************************************************************/
    
    /**
     * Sets the line chart data with the given map.
     * 
     * @param map the map to represent as a line chart
     * @param seriesName the name of the series 
     */
    public <T>void setLineChartData(Map<T,Integer> map,
            String seriesName){
        series = new XYChart.Series();
        series.setName(seriesName);
        T key;
        int i;
        for(Map.Entry<T,Integer> entrySet : map.entrySet()) {
            key = entrySet.getKey();
            i = entrySet.getValue();
            series.getData().add(new XYChart.Data<>(key,i));
        }
    }
    
    /**
     * Sets the pie chart data with the given map.
     * 
     * @param map the map to represent as a pie chart
     */
    public <T> void setPieChartData(Map<T ,Integer> map){
        pieChartData = FXCollections.observableArrayList();
        for(Map.Entry<T,Integer> entrySet : map.entrySet()){
            T key = entrySet.getKey();
            int value = entrySet.getValue();
            pieChartData.add(new PieChart.Data(key.toString() + " : " + value, value));
        }
    }
    
    /**
     * Sets the bar chart with the given map.
     * 
     * @param map the map to represent as a bar chart
     * @param seriesName the name of the series
     */
    public <T>void setBarChartData(Map<T,Integer> map,String seriesName){
        series = new XYChart.Series();
        series.setName(seriesName);
        T key;
        int i;
        for(Map.Entry<T,Integer> entrySet : map.entrySet()){
            key = entrySet.getKey();
            i = entrySet.getValue();
            series.getData().add(new XYChart.Data<>(key.toString(), i));
        }
    }

    /**#######################    END of Setting charts with single series   ######################################*/



    /****************************************************************************
     *                                                                           *
     *                     Setting charts with multiple series                   *
     *                                                                           *
     ****************************************************************************/
    
    /**
     * Sets a multi bar chart with the given double map.
     * 
     * @param map the double map
     * @param chartName the name of the chart
     */
    public <T>void setMultiBarChartData(Map<T, TreeMap<T, Integer>> map,
            String chartName) {
        int numOfSeries = map.values().iterator().next().size();
        seriesArray = new XYChart.Series[numOfSeries];
        int counter = 0;
        while (counter < numOfSeries) {
            XYChart.Series series = new XYChart.Series();
            seriesArray[counter] = series;
            counter++;
        }
        counter = 0;
        for (TreeMap<T, Integer> t : map.values()) {
            for (T x : t.keySet()) {
               seriesArray[counter].setName(x.toString());
               counter++;
            }
            break;
        }
        counter = 0;
        for (Map.Entry<T,TreeMap<T,Integer>> entrySet : map.entrySet()) {
            T key = entrySet.getKey();
            
            for (Map.Entry<T,Integer> innerEntrySet :
                    entrySet.getValue().entrySet()) {
                int i = innerEntrySet.getValue();

                seriesArray[counter].getData().add(new XYChart.Data<>(key,i));
                counter++;
            }
            counter = 0;
        }
    }

    /**
     * Sets the multi line chart with the given double map.
     * 
     * @param map the double map
     * @param chartName the name of the chart
     */
    public <T,U>void setMultiLineChartData(Map<T,TreeMap<U,Integer>> map,String chartName) {
        int numOfSeries = map.values().iterator().next().size();
        seriesArray = new XYChart.Series[numOfSeries];
        int counter = 0;
        while(counter < numOfSeries){
            XYChart.Series series = new XYChart.Series();
            seriesArray[counter] = series;
            counter++;
        }
        counter = 0;
        for (TreeMap<U, Integer> t : map.values()) {
            for (U x : t.keySet()) {
                seriesArray[counter].setName(x.toString());
                counter++;
            }
            break;
        }
        counter = 0;
        for (Map.Entry<T,TreeMap<U,Integer>> entrySet : map.entrySet()) {
            T key = entrySet.getKey();

            for(Map.Entry<U,Integer> innerEntrySet : entrySet.getValue().entrySet()) {
                int i = innerEntrySet.getValue();
                seriesArray[counter].getData().add(new XYChart.Data<>(key,i));
                counter++;
            }
            counter = 0;
        }
    }

    /**#######################    END of Setting charts with multiple series   ######################################*/
}
