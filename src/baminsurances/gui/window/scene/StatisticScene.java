package baminsurances.gui.window.scene;

import baminsurances.api.Searcher;
import baminsurances.data.Employee;
import baminsurances.gui.eventhandler.GuiEventHandler;
import baminsurances.gui.eventhandler.KeyPressHandler;
import baminsurances.gui.window.GuiConfig;
import baminsurances.logging.CustomLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.logging.Level;

/**
 * Created by baljit on 19.05.2015.
 */
public class StatisticScene extends GeneralScene{

    private VBox leftSide,rightSide;

    private VBox filters;
    private Image companyLogo;
    private ImageView companyLogoImageView;

    private Label factOne, factTwo, factThree, factFour;
    private TextField textField;
    private GridPane gridPane;
    private LineChart lineChart;
    private PieChart pieChart;
    private BarChart barChart;
    private BorderPane borderPane;
    private ObservableList<Employee> observableList;
    private ObservableList<PieChart.Data> pieChartData;
    private XYChart.Series series;
    private ArrayList<XYChart.Series> seriesArrayList;
    private XYChart.Series[] seriesArray;
    private ComboBox comboBox;
    private CustomLogger logger;
    private Axis xAxis;
    private Axis yAxis;
    private Axis cAxis;
    private boolean ifContaintsBarChart;
    
    public StatisticScene(GuiEventHandler guiEventHandler,KeyPressHandler keyPressHandler){
        super(guiEventHandler,keyPressHandler);
        logger = CustomLogger.getInstance();
        
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        cAxis = new CategoryAxis();
        /*XYChart.Series series = new XYChart.Series();
        this.setSeries(series);*/
        this.setComboBox();
        leftSide = new VBox(10);
        rightSide = new VBox(10);
        rightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 3 / 5);
        rightSide.getChildren().add(comboBox);
        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 3 / 5);
        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 3 / 5);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");
        rightSide.setAlignment(Pos.CENTER);
        borderPane = new BorderPane(leftSide,null,rightSide,footer,null);
        
        scene = new Scene(borderPane);
        
    }


    private void setComboBox(){
        comboBox = new ComboBox(FXCollections.observableArrayList(
                StatisticOption.OPTION_ONE.getDescription(),
                StatisticOption.OPTION_TWO.getDescription(),
                StatisticOption.OPTION_THREE.getDescription(),
                StatisticOption.OPTION_FOUR.getDescription(),
                StatisticOption.OPTION_FIVE.getDescription(),
                StatisticOption.OPTION_SIX.getDescription(),
                StatisticOption.OPTION_SEVEN.getDescription(),
                StatisticOption.OPTION_EIGHT.getDescription())
                
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
            } else if (comboBox.getValue() == StatisticOption.OPTION_SIX.getDescription()) {
                this.launchOptionSix(StatisticOption.OPTION_SIX);
            } else if (comboBox.getValue() == StatisticOption.OPTION_SEVEN.getDescription()) {
                this.launchOptionSeven(StatisticOption.OPTION_SEVEN);
            }  else if (comboBox.getValue() == StatisticOption.OPTION_EIGHT.getDescription()) {
                this.launchOptionEight(StatisticOption.OPTION_EIGHT);
            }  else {
                System.out.println("dafaq");
            }
        });
    }
    private void clearRightSide(){

        xAxis.setAutoRanging(true);
        rightSide.getChildren().removeAll(pieChart);
        ifContaintsBarChart =  rightSide.getChildren().removeAll(barChart);
        
        rightSide.getChildren().removeAll(lineChart);
    }
    
    
    private PieChart setPieChart(){
        pieChart = new PieChart(pieChartData);
        pieChart.setVisible(true);
        pieChart.getLabelsVisible();
        return pieChart;
    }
    
    private BarChart setBarchart(){
        barChart = new BarChart(cAxis,xAxis);
        barChart.getData().addAll(series);
        barChart.setAnimated(!ifContaintsBarChart);
        return barChart;
    }
    
    private LineChart setLineChart(){
        System.out.println("came in here");
        lineChart = new LineChart(xAxis,yAxis);
        System.out.println("new line chart made");
        lineChart.getData().addAll(series);
        System.out.println("series added");
        System.out.println("returning");
        return lineChart;
    }
    
    private BarChart setBarchartMulti(){
        barChart = new BarChart(cAxis,xAxis);
        for(XYChart.Series series : seriesArray){
            barChart.getData().add(series);  
        }
        return barChart;
    }




     /****************************************************************************
     *                                                                           *
     *                        Methods representing options                       *
     *                                                                           *
     ****************************************************************************/
    
    
    
    private void launchOptionOne(StatisticOption statisticOption ){
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setPieChartData(new Searcher().numInsurancesPerGender());
        rightSide.getChildren().add(this.setPieChart());
    }



    private void launchOptionTwo(StatisticOption statisticOption ){
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setBarChartData(new Searcher().numInsurancesPerType(), "Antall forsikeringer");
        cAxis.setLabel("Forsikeringer");
        xAxis.setLabel("Antall per År");
        barChart =  this.setBarchart();
        barChart.setTitle(statisticOption.getDescription());
        rightSide.getChildren().add(barChart);
    }

    private void launchOptionThree(StatisticOption statisticOption ){
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setBarChartData(new Searcher().numInsurancesPerAge(), "Antall forsikeringer");
        cAxis.setLabel("Forsikeringer");
        xAxis.setLabel("Antall per alder");
        barChart =  this.setBarchart();
        barChart.setTitle(statisticOption.getDescription());
        rightSide.getChildren().add(barChart);
    }

    private void launchOptionFour(StatisticOption statisticOption ){
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setBarChartData(new Searcher().numInsurancesPerRegion(), "Antall forsikeringer");
        cAxis.setLabel("Forsikeringer");
        xAxis.setLabel("Antall per landsdel");
        barChart =  this.setBarchart();
        barChart.setTitle(statisticOption.getDescription());
        rightSide.getChildren().add(barChart);
        this.setPieChartData(new Searcher().numInsurancesPerRegion());
        rightSide.getChildren().add(this.setPieChart());
    }

    private void launchOptionFive(StatisticOption statisticOption ){
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setBarChartData(new Searcher().numInsurancesPerZipCode(), "Antall forsikeringer");
        cAxis.setLabel("Forsikeringer");
        xAxis.setLabel("Antall per post nummer");
        barChart =  this.setBarchart();
        barChart.setTitle(statisticOption.getDescription());
        rightSide.getChildren().add(barChart);
    }

    private void launchOptionSix(StatisticOption statisticOption ){
        this.clearRightSide();
        xAxis = new NumberAxis(1997, 2016, 1);
        logger.log("Option one selected ", Level.FINE);
        this.setLineChartData(new Searcher().getSumPaymentsPerYear(), "Sum innbetalinger");
        rightSide.getChildren().add(this.setLineChart());
        
    }

    private void launchOptionSeven(StatisticOption statisticOption ){
        this.clearRightSide();
        logger.log("Option Seven selected ", Level.FINE);
        this.setBarChartData(new Searcher().numCustomersPerAgeGroup(), "Antall kunder");
        cAxis.setLabel("Kunder");
        xAxis.setLabel("Antall per alders gruppe");
        barChart =  this.setBarchart();
        barChart.setTitle(statisticOption.getDescription());
        rightSide.getChildren().add(barChart);
        this.setPieChartData(new Searcher().numInsurancesPerRegion());
        rightSide.getChildren().add(this.setPieChart());
    }

    private void launchOptionEight(StatisticOption statisticOption ){
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setMultiBarChartData(new Searcher().getInsuranceTypesPerRegion(), "Antall forsikeringer");
        cAxis.setLabel("Forsikeringer");
        xAxis.setLabel("Antall per landsdel");
        barChart =  this.setBarchartMulti();
        barChart.setTitle(statisticOption.getDescription());
        rightSide.getChildren().add(barChart);
    }
    
    

    public void addData(XYChart.Series series,String s,int i){

    }

    public <T>void setLineChartData(Map<T,Integer> map,String seriesNavn){
        series = new XYChart.Series();
        series.setName(seriesNavn);
        T key;
        int i;
        for(Map.Entry<T,Integer> entrySet : map.entrySet()){
            key = entrySet.getKey();
            i = entrySet.getValue();
            series.getData().add(new XYChart.Data<>(key,i));
        }
    }
    
    public <T> void setPieChartData(Map<T ,Integer> map){
        pieChartData = FXCollections.observableArrayList();
        for(Map.Entry<T,Integer> entrySet : map.entrySet()){
            T key = entrySet.getKey();
            int value = entrySet.getValue();
            pieChartData.add(new PieChart.Data(key.toString() + " : " + value, value));
        }
    }
    
    public <T>void setBarChartData(Map<T,Integer> map,String seriesName){
        series = new XYChart.Series();
        series.setName(seriesName);
        T key;
        int i;
        for(Map.Entry<T,Integer> entrySet : map.entrySet()){
            key = entrySet.getKey();
            i = entrySet.getValue();
            System.out.println(key+" : "+i);
            series.getData().add(new XYChart.Data<>(key.toString(),i));
        }
    }

    

    public <T>void setMultiBarChartData(Map<T,TreeMap<T,Integer>> map,String ChartName){
        int numOfSeries = map.values().iterator().next().size();
        seriesArray = new XYChart.Series[numOfSeries];
        int counter = 0;
        T key;
        T innerKey;
        int i;
        while(counter < numOfSeries){
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
        for(Map.Entry<T,TreeMap<T,Integer>> entrySet : map.entrySet()){
            key = entrySet.getKey();
            
            for(Map.Entry<T,Integer> innerEntrySet : entrySet.getValue().entrySet()){
                innerKey = innerEntrySet.getKey();
                i = innerEntrySet.getValue();
                System.out.println(seriesArray[counter].getData());
                seriesArray[counter].getData().add(new XYChart.Data<>(key,i));
                counter++;
            }
            counter = 0;
        }
    }
}
