package ticketreservation.booking;

import ticketreservation.details.Customer;
import ticketreservation.details.Record;
import ticketreservation.details.Ticket;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class TicketOffice {
    private static long bookingID;

    public void initialise(){
        TreeSet<Integer> upper = new TreeSet<>();
        TreeSet<Integer> middle = new TreeSet<>();
        TreeSet<Integer> lower = new TreeSet<>();
        TreeSet<Integer> sideLower = new TreeSet<>();
        for (int i=0 ; i<1 ; i++){
            int upn = (8*i)+3;
            int min = (8*i)+2;
            int lon = (8*i)+1;
            int son = (8*i)+7;
            upper.add(upn);
            upper.add(upn + 3);
            upper.add(upn +5);
            middle.add(min);
            middle.add(min + 3);
            lower.add(lon);
            lower.add(lon + 3);
            sideLower.add(son);
            sideLower.add(-son);
        }

        Record.INSTANCE.setAvailableBirths("upper" , upper);
        Record.INSTANCE.setAvailableBirths("middle" , middle);
        Record.INSTANCE.setAvailableBirths("lower" , lower);
        Record.INSTANCE.setAvailableBirths("sideLower" , sideLower);

    }

    public long bookTickets(List<Customer> passengers, long mobile, String email, String from, String to){

        /*if (Record.INSTANCE.getWaitingSeatCount() + passengers.size()>10){
            System.out.println("no tickets");
            return 0;
        }*/
        Ticket ticket = new Ticket();
        ticket.setBookingID(++bookingID);
        ticket.setPassengers(passengers);
        ticket.setMobile(mobile);
        ticket.setEmail(email);
        ticket.setFrom(from);
        ticket.setTo(to);
        Record.INSTANCE.addTicket(ticket);
        for (Customer customerInfo : passengers){
            /*System.out.println("\nAvailable tickets : " + Record.INSTANCE.getAvailableTickets());
            System.out.println("Available RAC : " + Record.INSTANCE.getRacSeatCount());
            System.out.println("Available waiting : " + Record.INSTANCE.getWaitingSeatCount()+"\n");*/
            if(customerInfo.getAge()<=5){
                customerInfo.setTicketStatus("No seat allotted");
                customerInfo.setSeatNumber(0);
                continue;
            }
            if(Record.INSTANCE.getAvailableTickets() == 0){
                if(Record.INSTANCE.getRacSeatCount() == 0){
                    if(Record.INSTANCE.getWaitingSeatCount() == 0){
                        customerInfo.setTicketStatus("Failed");
                        System.out.println("Failed");
                    } else {
                        waitingList(customerInfo);
                    }
                } else {
                    allotRc(customerInfo);
                }
            } else {
                switch (customerInfo.getBirthPreference()) {
                    case 'n':
                        int seatNumber = allotAny(customerInfo);
                        if (seatNumber == 0) {
                            System.out.println("No tickets");
                        }
                        break;
                    case 'l':
                        seatNumber = allotBirth("lower" , customerInfo);
                        if (seatNumber == 0) {
                            seatNumber = allotBirth("middle" , customerInfo);
                            if (seatNumber == 0) {
                                allotBirth("upper" , customerInfo);
                                /*if (seatNumber == 0) {
                                    seatNumber = allotRc(customerInfo);
                                    if (seatNumber == 0) {
                                        System.out.println("no seats");
                                    }
                                }*/
                            }
                        }
                        break;
                    case 'm':
                        seatNumber = allotBirth("middle" , customerInfo);
                        if (seatNumber == 0) {
                            seatNumber = allotBirth("lower" , customerInfo);
                            if (seatNumber == 0) {
                                allotBirth("upper" , customerInfo);
                                /*if (seatNumber == 0) {
                                    seatNumber = allotRc(customerInfo);
                                    if (seatNumber == 0) {
                                        System.out.println("no seats");
                                    }
                                }*/
                            }
                        }
                        break;
                    case 'u':
                        seatNumber = allotBirth("upper" , customerInfo);
                        if (seatNumber == 0) {
                            seatNumber = allotBirth("middle" , customerInfo);
                            if (seatNumber == 0) {
                                allotBirth("lower" , customerInfo);
                                /*if (seatNumber == 0) {
                                    seatNumber = allotRc(customerInfo);
                                    if (seatNumber == 0) {
                                        System.out.println("no seats");
                                    }
                                }*/
                            }
                        }
                        break;
                }
            }
//            System.out.println(customerInfo);
        }
        return bookingID;
    }

    private void waitingList(Customer customerInfo) {
        LinkedList<Customer> waitingList = (LinkedList<Customer>) Record.INSTANCE.getWaitingList();
        waitingList.addLast(customerInfo);
        customerInfo.setTicketStatus("waiting");
        Record.INSTANCE.decrementWaitingCount();
    }

    private int allotBirth(String pref , Customer customerInfo){
        ArrayList<Customer> birth = (ArrayList<Customer>) Record.INSTANCE.getBookedBirths(pref);

        TreeSet<Integer> availableBirth = (TreeSet<Integer>) Record.INSTANCE.getAvailableBirths(pref);
        Integer seat = availableBirth.pollFirst();
        if(seat != null){
            availableBirth.remove(seat);
        } else {
            return 0;
        }

        customerInfo.setSeatNumber(seat);
        customerInfo.setBirth(pref.charAt(0));
        customerInfo.setTicketStatus("Confirmed");
        birth.add(customerInfo);
//        System.out.println(pref.charAt(0) + " " + seat);
        Record.INSTANCE.decrementTickets();
        return seat;
    }

    private int allotAny(Customer customerInfo) {
        if(customerInfo.getAge() >= 50){
            int seatNumber = allotBirth("lower" , customerInfo);
            if(seatNumber == 0){
                seatNumber = allotBirth("middle" , customerInfo);
            }
            if(seatNumber == 0){
                seatNumber = allotBirth("upper" , customerInfo);
            }
            System.out.println(seatNumber);
            return seatNumber;
        }
        int seatNumber = allotBirth("upper" , customerInfo);
        if(seatNumber == 0){
            seatNumber = allotBirth("middle" , customerInfo);
        }
        if(seatNumber == 0){
            seatNumber = allotBirth("lower" , customerInfo);
        }
        System.out.println(seatNumber);
        return seatNumber;
    }

    private int allotRc(Customer customerInfo){
        LinkedList<Customer> bookedRc = (LinkedList<Customer>) Record.INSTANCE.getReservationAgainstCancellation();
        TreeSet<Integer> availableRc = (TreeSet<Integer>) Record.INSTANCE.getAvailableBirths("sideLower");
        Integer seat = availableRc.pollFirst();
        if(seat != null){
            availableRc.remove(seat);
        } else {
            return 0;
        }
        customerInfo.setSeatNumber(seat);
        customerInfo.setBirth('r');
        customerInfo.setTicketStatus("RAC");
        bookedRc.add(customerInfo);
//        System.out.println("r "+seat);
        Record.INSTANCE.decrementRacCount();
        return seat;
    }

    public long cancelTickets (long bookingID){
        Ticket ticket = Record.INSTANCE.getTicket(bookingID);
        if(ticket == null){
            return 0;
        }
        ArrayList<Customer> passengers = (ArrayList<Customer>) ticket.getPassengers();
        for (Customer customerInfo : passengers){
            switch (customerInfo.getTicketStatus()){
                case "Confirmed":
                    cancelBirth(customerInfo);
                    break;
                case "RAC":
                    cancelRAC(customerInfo);
                    break;
                case "waiting":
                    cancelWaiting(customerInfo);
                    break;
                case "No seat allotted":
                    passengers.remove(customerInfo);
                    break;
            }
        }
        return bookingID;
    }

    private void cancelWaiting(Customer customerInfo) {
        LinkedList<Customer> waitingList = (LinkedList<Customer>) Record.INSTANCE.getWaitingList();
        waitingList.remove(customerInfo);
        Record.INSTANCE.incrementWaitingCount();
    }

    private void cancelRAC(Customer customerInfo) {
        LinkedList<Customer> bookedRc = (LinkedList<Customer>) Record.INSTANCE.getReservationAgainstCancellation();
        TreeSet<Integer> availableRc = (TreeSet<Integer>) Record.INSTANCE.getAvailableBirths("sideLower");
        bookedRc.remove(customerInfo);
        availableRc.add(customerInfo.getSeatNumber());
        Record.INSTANCE.incrementRacCount();
        if(Record.INSTANCE.getWaitingSeatCount() < 5){
            LinkedList<Customer> waitingList = (LinkedList<Customer>) Record.INSTANCE.getWaitingList();
            Customer wait1 = waitingList.pollFirst();
            waitingList.remove(wait1);
            Record.INSTANCE.incrementWaitingCount();
            wait1.setSeatNumber(availableRc.pollFirst());
            availableRc.remove(wait1.getSeatNumber());
            bookedRc.add(wait1);
            wait1.setTicketStatus("RAC");
            Record.INSTANCE.decrementRacCount();
        }
    }

    private void cancelBirth(Customer customerInfo) {
        String allottedBirth;
        if(customerInfo.getBirth() == 'l') {
            allottedBirth = "lower";
        } else if(customerInfo.getBirth() == 'm') {
            allottedBirth = "middle";
        } else {
            allottedBirth = "upper";
        }
        ArrayList<Customer> birth = (ArrayList<Customer>) Record.INSTANCE.getBookedBirths(allottedBirth);
        birth.remove(customerInfo);
        customerInfo.setTicketStatus("cancelled");
        Record.INSTANCE.incrementTickets();

        if(Record.INSTANCE.getRacSeatCount() < 2){

            LinkedList<Customer> bookedRc = (LinkedList<Customer>) Record.INSTANCE.getReservationAgainstCancellation();
            TreeSet<Integer> availableRc = (TreeSet<Integer>) Record.INSTANCE.getAvailableBirths("sideLower");
            Customer rac1 = bookedRc.pollFirst();
            bookedRc.remove(rac1);
            availableRc.add(rac1.getSeatNumber());
            Record.INSTANCE.incrementRacCount();
            birth.add(rac1);
            rac1.setTicketStatus("confirmed");
            Record.INSTANCE.decrementTickets();

            if(Record.INSTANCE.getWaitingSeatCount() < 5){
                LinkedList<Customer> waitingList = (LinkedList<Customer>) Record.INSTANCE.getWaitingList();
                Customer wait1 = waitingList.pollFirst();
                waitingList.remove(wait1);
                Record.INSTANCE.incrementWaitingCount();
                wait1.setSeatNumber(availableRc.pollFirst());
                availableRc.remove(wait1.getSeatNumber());
                bookedRc.add(wait1);
                wait1.setTicketStatus("RAC");
                Record.INSTANCE.decrementRacCount();
            }
        } else {
            TreeSet<Integer> availableBirthSeats = (TreeSet<Integer>) Record.INSTANCE.getAvailableBirths(allottedBirth);
            availableBirthSeats.add(customerInfo.getSeatNumber());
        }

    }

    public Ticket getSummary(long bookingId) {
        return Record.INSTANCE.getTicket(bookingId);
    }
}
