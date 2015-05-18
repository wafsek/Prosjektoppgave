package baminsurances.gui.window;

import baminsurances.controller.StatisticController;
import baminsurances.data.Customer;
import baminsurances.data.DataBank;
import baminsurances.data.Employee;
import baminsurances.data.Person;
import baminsurances.gui.eventhandler.GuiEventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.awt.Color.*;
import java.util.*;

import javafx.stage.Stage;
import javafx.scene.chart.*;
import sun.invoke.empty.Empty;

/**
 * Created by baljit on 17.05.2015.
 */
public class StatisticWindow {

    private Stage stage;
    private Scene scene;
    
    
    private HBox top, interactivePart, handyData;
    private VBox wrapperContainer;
    
    private VBox filters;
    private Image companyLogo;
    private ImageView companyLogoImageView;

    private Label usernameLabel, passwordLabel, logoLabel;
    private TextField usernameField;
    
    private LineChart lineChart;
    private PieChart pieChart;
    private BarChart barChart;

    private Button loginButton;

    private GridPane fieldContainer;
    private Map<Double, Character> memory;
    private ObservableList<Employee> observableList;
    private ObservableList<PieChart.Data> pieChartData;
    
    
    
    
    private GuiEventHandler handler;
    
    
    public StatisticWindow(){
        
        companyLogo = new Image(
                this.getClass().getResourceAsStream("../img/temp_logo.png"));
        stage = new Stage();
        stage.setTitle("Statistic Window");
        stage.getIcons().add(companyLogo);
       
        
        
        
        
        top = new HBox();
        top.setPrefHeight(100);
        handyData = new HBox();
        handyData.setPrefHeight(200);
        
        filters = new VBox();
        filters.setPrefHeight(400);
        filters.setPrefWidth(400);
        
        this.setPieChartData(StatisticController.numInsurancesPerType());
        pieChart = new PieChart(pieChartData);
        pieChart.setVisible(true);
        pieChart.getLabelsVisible();
        handyData.getChildren().add(pieChart);
        
        
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
        
        interactivePart = new HBox();
        interactivePart.setMinHeight(400);
        interactivePart.getChildren().add(barChart);
        interactivePart.getChildren().add(lineChart);
        
        
        this.createWrapperContainer();
        scene = new Scene(wrapperContainer);
        stage.setScene(scene);
    }
    
    
    
    public void show(){
        stage.show();
    }
    
    public <T> void setPieChartData(Map<T ,Integer> map){
        pieChartData = FXCollections.observableArrayList();
        for(Map.Entry<T,Integer> entrySet : map.entrySet()){
            System.out.println("fadfasdf");
            T key = entrySet.getKey();
            int value = entrySet.getValue();
            pieChartData.add(new PieChart.Data(key.getClass().getTypeName(),value));
        }
    }
    
    private void createWrapperContainer(){
        wrapperContainer = new VBox();
        wrapperContainer.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.rgb(31, 31, 31),
                        CornerRadii.EMPTY, Insets.EMPTY)));
        wrapperContainer.getChildren().add(top);
        wrapperContainer.getChildren().add(interactivePart);
        wrapperContainer.getChildren().add(handyData);
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
