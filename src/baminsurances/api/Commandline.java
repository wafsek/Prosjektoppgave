package baminsurances.api;

import baminsurances.data.*;
import baminsurances.security.Authorization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Created by baljit on 12.05.2015.
 */

public class Commandline extends Thread{
    Scanner scanner = new Scanner(System.in);
    int userInput;
    Employee employee = new Employee("05038437102","Jobbern","Jobbernsen",
            "55555555","ansatt@online.no","1443","seier veien 9",
            "05038437102", "Jobbernsen", Authorization.USER);
    Person owner = new Person("05038437102","Arne","Hansen",
            "99999999","eier@online.no","1445","Heer veien 11");
    CustomerServiceManager manager = new CustomerServiceManager();
    Searcher searcher = new Searcher();
    List<Predicate<Customer>> predicates = new ArrayList<>();
    @Override
    public void  run() {
        //this.login();
        do {
            System.out.println("1) Register new customer\n" +
                    "2) Find Customer\n" +
                    "3) Register new insurance\n" +
                    "4) Cancel en insurance\n" +
                    "5) Register new Claim Advice\n"+
                    "6) Show all Insurances"+
                    "\n\nChoose option From 1-5");
            userInput = Integer.parseInt(scanner.nextLine());
            switch (userInput) {
                case 1:
                    this.registerCustomer();
                    break;
                case 2:
                    this.findCustomer();
                    break;
                case 3:
                    this.registerInsurance();
                    break;
                case 4:
                    this.cancelInsurance();
                    break;
                case 5:
                    this.registerClaimAdvice();
                    break;
                case 6:
                    this.showAllInsurances();
                default:
                    System.out.println("Welcome to our service");
            }
            DataBank.saveDataBank();
        } while (userInput != 99);
    }
    
    private void clearScreen(){
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e)
            {
                //  Handle any exceptions.
            }
    }
    
    private void login() {
        this.clearScreen();
        System.out.println("first name");
        String  firstName = scanner.nextLine();

        System.out.println("last name: ");
        String  lastName = scanner.nextLine();

        System.out.println("address: ");
        String  address= scanner.nextLine();

        System.out.println("zip code; ");
        String  zipCode = scanner.nextLine();

        System.out.println("birthNo: ");
        String birthNo= scanner.nextLine();

        System.out.println("tlf: ");
        String  tlf = scanner.nextLine();

        System.out.println("email: ");
        String  email = scanner.nextLine();
        
        employee = new Employee(birthNo,firstName,
                lastName,tlf,email,zipCode,address, birthNo, lastName,
                Authorization.USER);
    }
    private void registerCustomer(){
        this.clearScreen();
        System.out.println("first name");
        String  firstName = scanner.nextLine();

        System.out.println("last name: ");
        String  lastName = scanner.nextLine();

        System.out.println("address: ");
        String  address= scanner.nextLine();

        System.out.println("zip code; ");
        String  zipCode = scanner.nextLine();

        System.out.println("birthNo: ");
        String birthNo= scanner.nextLine();

        System.out.println("tlf: ");
        String  tlf = scanner.nextLine();

        System.out.println("email: ");
        String  email = scanner.nextLine();

        manager.registerCustomer(new Customer(birthNo,
                firstName, lastName, tlf, email, zipCode, address,
                zipCode, address));
        
        for(Customer customer : 
                DataBank.getInstance().getCustomerList()){
            System.out.println(customer.toString());
        }
        System.out.println(searcher.findCustomers(predicates).toString());
    }
    
    private void findCustomer(){
        this.clearScreen();
        List<Customer> customerList = new ArrayList<>();
        System.out.println("Type in birthNo");
        String  birthNo = scanner.nextLine();
        predicates.add(Searcher.birthNoStartsWith(birthNo));
        customerList = searcher.findCustomers(predicates);
        if(customerList.isEmpty())
        {
            System.out.println("Did not find any");
        }
        for(Customer customer :customerList){
            System.out.println(customer.toString());
        }
    }
    
    private void registerInsurance(){
        this.clearScreen();
            System.out.println("1) Car\n" +
                    "2) Boat\n" +
                    "3) home\n" +
                    "4) holiday home\n" +
                    "5) travel\n"+
                    "\n\nChoose option from 1-5\n" +
                    "To exit type 99");
            userInput = Integer.parseInt(scanner.nextLine());
            switch (userInput) {
                case 1:
                    this.registerCarInsurance();
                    break;
                case 2:
                    //this.registerBoatInsurance();
                    break;
                case 3:
                    //this.registerHomeInsurance();
                    break;
                case 4:
                    //this.registerHolidayHomeInsurance();
                    break;
                case 5:
                    //this.registerTravelInsurance();
                    break;
                default:
                    System.out.println("Welcome to our service");
                    break;
            }
    }
    
    private void cancelInsurance(){
        this.clearScreen();
        System.out.println("insurance number: ");
        int insuranceNo = Integer.parseInt(scanner.nextLine());
        System.out.println(manager.cancelInsurance(insuranceNo));
    }
    
    private  void registerClaimAdvice(){
        
    }
    
    

    private void registerCarInsurance(){
        this.clearScreen();
        System.out.println("Birth number of the person");
        String  birthNo = (scanner.nextLine());
        
        System.out.println("Premium");
        int  premium = Integer.parseInt(scanner.nextLine());

        System.out.println("Amount: ");
        int  amount = Integer.parseInt(scanner.nextLine());

        System.out.println("Terms: ");
        String  terms = scanner.nextLine();

        System.out.println("Registration No: ");
        String  registrationNo = scanner.nextLine();

        System.out.println("Brand: ");
        String brand= scanner.nextLine();

        System.out.println("Model: ");
        String  model = scanner.nextLine();

        System.out.println("Registration Year: ");
        int  rYear = Integer.parseInt(scanner.nextLine());

        System.out.println("Yearly milage: ");
        int  ymilage = Integer.parseInt(scanner.nextLine());

        System.out.println("Price per Km: ");
        double  pPKm = Double.parseDouble(scanner.nextLine());

        System.out.println("Bonus Percentage: ");
        int  bP = Integer.parseInt(scanner.nextLine());
        
        CarInsurance carInsurance = new CarInsurance(this.employee,
                premium,amount,PaymentFrequency.ANNUALLY,terms,
                this.owner,registrationNo,CarType.SEDAN,brand,model,rYear,
                ymilage,pPKm,bP);
        
        System.out.println(manager.addInsurance(carInsurance,birthNo));
    }

    
    
    private void showAllInsurances(){
        for(Customer customer: DataBank.getInstance().getCustomerList()) {
            for (Insurance insurance : customer.getInsurances()){
                System.out.println("\n#####################################" +
                        "\nInsurance No : "+insurance.getInsuranceNo()+
                        "\nType : "+insurance.getClass().getSimpleName()+
                        "\nActive status: "+insurance.isActive()+
                        "\nInsurance made by: "+insurance.getEmployee().getFirstName()+
                        " "+insurance.getEmployee().getLastName()+
                        "\n#####################################\n");
            }
        }
    }
    
}
