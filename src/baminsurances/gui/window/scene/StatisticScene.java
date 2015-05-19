package baminsurances.gui.window.scene;

import baminsurances.api.Searcher;
import baminsurances.data.DataBank;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.logging.Level;

/**
 * Created by baljit on 19.05.2015.
 */
public class StatisticScene extends GeneralScene{

    private VBox leftSide,rightSide;

    private VBox filters;
    private Image companyLogo;
    private ImageView companyLogoImageView;

    private Label usernameLabel, passwordLabel, logoLabel;
    private TextField usernameField;
    private GridPane gridPane;
    private LineChart lineChart;
    private PieChart pieChart;
    private BarChart barChart;

    private Button loginButton;
    private BorderPane borderPane;
    private GridPane fieldContainer;
    private Map<Double, Character> memory;
    private ObservableList<Employee> observableList;
    private ObservableList<PieChart.Data> pieChartData;
    private ComboBox comboBox;
    private CustomLogger logger;
    
    
    public StatisticScene(GuiEventHandler guiEventHandler,KeyPressHandler keyPressHandler){
        super(guiEventHandler,keyPressHandler);

        logger = CustomLogger.getInstance();
        //handyData.getChildren().add(pieChart);


        Axis xAxis = new NumberAxis();
        Axis yAxis = new NumberAxis();
        Axis cAxis = new CategoryAxis();

        XYChart.Series series = new XYChart.Series();


        this.setSeries(series);

        lineChart = new LineChart(xAxis,yAxis);
        lineChart.setPrefWidth(700);

        barChart = new BarChart(cAxis,yAxis);
        barChart.getData().addAll(series);

        barChart.setPrefWidth(700);
        barChart.setMaxHeight(400);


        this.setComboBox();
        leftSide = new VBox(10);
        rightSide = new VBox(10);
        rightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        rightSide.getChildren().add(comboBox);

        footerRightSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footerLeftSide.setPrefWidth(GuiConfig.PRIMARY_WIDTH * 1 / 2);
        footer = new HBox(0, footerLeftSide, footerRightSide);
        footer.setStyle("-fx-border-color: gray;");


        rightSide.setAlignment(Pos.CENTER);
        borderPane = new BorderPane(leftSide,null,rightSide,footer,null);

        scene = new Scene(borderPane);
        
    }


    private void setComboBox(){
        comboBox = new ComboBox(FXCollections.observableArrayList(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6")
        );
        comboBox.setOnAction(e -> {
            if (comboBox.getValue() == "1") {
                this.launchOptionOne();
            } else if (comboBox.getValue() == "2") {
                this.launchOptionTwo();
            } else if (comboBox.getValue() == "3") {
                this.launchOptionThree();
            } else if (comboBox.getValue() == "4") {
                this.launchOptionFour();
            } else if (comboBox.getValue() == "5") {
                this.launchOptionFive();
            } else if (comboBox.getValue() == "6") {
                this.launchOptionSix();
            } else {
                System.out.println("dafaq");
            }
        });
    }
    private void clearRightSide(){
        rightSide.getChildren().removeAll(pieChart);
    }

    private void launchOptionOne(){
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setPieChartData(new Searcher().numInsurancesPerType());
        pieChart = new PieChart(pieChartData);
        pieChart.setVisible(true);
        pieChart.getLabelsVisible();
        rightSide.getChildren().add(pieChart);
    }



    private void launchOptionTwo(){
        this.clearRightSide();
        logger.log("Option one selected ", Level.FINE);
        this.setPieChartData(new Searcher().numInsurancesPerType());
        //barChart = new BarChart();
        pieChart.setVisible(true);
        pieChart.getLabelsVisible();
        rightSide.getChildren().add(pieChart);
    }

    private void launchOptionThree(){
        logger.log("Option three selected ", Level.FINE);
    }

    private void launchOptionFour(){
        logger.log("Option four selected ", Level.FINE);
    }

    private void launchOptionFive(){
        logger.log("Option five selected ", Level.FINE);
    }

    private void launchOptionSix(){
        logger.log("Option six selected ", Level.FINE);
    }

    public <T> void setPieChartData(Map<T ,Integer> map){
        pieChartData = FXCollections.observableArrayList();
        for(Map.Entry<T,Integer> entrySet : map.entrySet()){
            T key = entrySet.getKey();
            int value = entrySet.getValue();
            pieChartData.add(new PieChart.Data(key.toString()+" : "+value,value));
        }
    }



    public void setSeries(XYChart.Series series1){
        for (Employee employee: DataBank.getInstance().getEmployeeList()){
            this.addData(series1,employee.getFirstName(),employee.getAge());
        }
    }


    public void addData(XYChart.Series series,String s,int i){

    }

    public <T>void setBarChartData(Map<T,Integer> map){
        XYChart.Series series = new XYChart.Series();
        T key;
        int i;
        for(Map.Entry<T,Integer> entrySet : map.entrySet()){
            key = entrySet.getKey();
            i = entrySet.getValue();
            series.getData().add(new XYChart.Data<>(key,i));
        }
    }
}