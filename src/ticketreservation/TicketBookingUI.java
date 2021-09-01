package ticketreservation;

import ticketreservation.booking.TicketOffice;
import ticketreservation.details.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketBookingUI {
    private static final Scanner SCAN = new Scanner(System.in);
    public static void main(String[] args) {

        TicketOffice obj = new TicketOffice();
        obj.initialise();
        input : while (true){
            System.out.print("1. Book Ticket\n2. Exit\nEnter the option : ");
            int option = SCAN. nextInt();
            switch (option){
                case 1:
                    List<Customer> list = passengerData();
                    obj.bookTickets(list , "Karaikudi" , "Tiruchendur");
                    break;
                case 2:
                    break input;
            }
        }
    }

    private static List<Customer> passengerData() {
        System.out.print("Enter the number of passengers : ");
        int number = SCAN.nextInt();
        SCAN.nextLine();
        List<Customer> list = new ArrayList<>();
        for(int i=0 ; i<number ; i++){
            System.out.print("Name : ");
            String name = SCAN.nextLine();
            System.out.print("Age : ");
            int age = SCAN.nextInt();
            SCAN.nextLine();
            System.out.print("Gender : ");
            String gender = SCAN.next();
            SCAN.nextLine();
            System.out.print("Birth Preference : ");
            char bp = SCAN.next().charAt(0);
            SCAN.nextLine();
            Customer customerInfo = createCustomer(name , age , gender, bp);
            list.add(customerInfo);
        }
        return list;
    }

    private static Customer createCustomer(String name, int age, String gender, char bp) {
        Customer customerInfo = new Customer();
        customerInfo.setName(name);
        customerInfo.setAge(age);
        customerInfo.setGender(gender);
        customerInfo.setBirthPreference(bp);
        return customerInfo;
    }
}
