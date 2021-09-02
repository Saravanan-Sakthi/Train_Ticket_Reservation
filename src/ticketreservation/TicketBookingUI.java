package ticketreservation;

import ticketreservation.booking.TicketOffice;
import ticketreservation.details.Customer;
import ticketreservation.details.Ticket;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TicketBookingUI {
    private static final Scanner SCAN = new Scanner(System.in);
    public static void main(String[] args) {

        TicketOffice obj = new TicketOffice();
        obj.initialise();
        long bookingId = 0;
        input : while (true){
            try {
                System.out.print("1. Book Ticket\n2. Cancel Ticket\n3. Booking Summary\n4. Exit\nEnter the option : ");
                int option = SCAN.nextInt();
                SCAN.nextLine();
                switch (option){
                    case 1:
                        System.out.print("Enter your Mobile number : ");
                        long mobile = SCAN.nextLong();
                        SCAN.nextLine();
                        System.out.print("Enter your Email : ");
                        String email = SCAN.nextLine();
                        System.out.print("Enter Boarding Station : ");
                        String from = SCAN.nextLine();
                        System.out.print("Enter your Destination : ");
                        String to = SCAN.nextLine();
                        List<Customer> list = passengerData();
                        bookingId = obj.bookTickets(list , mobile , email , from , to);
                        System.out.println("Your Booking ID is : "+bookingId);
                        break;
                    case 2:
                        System.out.print("Enter the Booking ID : ");
                        bookingId = SCAN.nextLong();
                        obj.cancelTickets(bookingId);
                        break;
                    case 3:
                        System.out.print("Enter the Booking ID : ");
                        bookingId = SCAN.nextLong();
                        Ticket ticket = obj.getSummary(bookingId);
                        System.out.println(ticket);
                        break;
                    case 4:
                        break input;
                    default:
                        System.out.println("No such choice");
                }
            } catch (InputMismatchException e){
                SCAN.nextLine();
                System.out.println("Please enter valid input");
            }
        }
    }

    private static List<Customer> passengerData() throws  InputMismatchException{
        System.out.print("Enter the number of passengers : ");
        int number = SCAN.nextInt();
        SCAN.nextLine();
        List<Customer> list = new ArrayList<>();
        for(int i=0 ; i<number ; i++){
            System.out.println("Passenger no : "+(i+1));
            System.out.print("\nName : ");
            String name = SCAN.nextLine();
            System.out.print("Age : ");
            int age = SCAN.nextInt();
            SCAN.nextLine();
            String gender = "";
            genderSwitch : while (true) {
                System.out.print("Gender : 1. Male   2. Female\nEnter the option : ");
                int option = SCAN.nextInt();
                switch (option) {
                    case 1:
                        gender = "male";
                        break genderSwitch;
                    case 2:
                        gender = "female";
                        break genderSwitch;
                    default:
                        System.out.println("Please enter valid input");
                }
            }
            SCAN.nextLine();
            char bp =' ';
            birthSwitch: while (true) {
                System.out.print("Birth Preference : 1. Upper   2. Middle    3. Lower\nEnter the option : ");
                int option = SCAN.nextInt();
                switch (option) {
                    case 1:
                        bp = 'u';
                        break birthSwitch;
                    case 2:
                        bp = 'm';
                        break birthSwitch;
                    case 3:
                        bp = 'l';
                        break birthSwitch;
                    default:
                        System.out.println("Please enter valid input");
                }
            }
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
