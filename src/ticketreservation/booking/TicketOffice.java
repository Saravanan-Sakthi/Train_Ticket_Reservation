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

    public long bookTickets(List<Customer> passengers , String from , String to){

        /*if (Record.INSTANCE.getWaitingCount() + passengers.size()>10){
            System.out.println("no tickets");
            return 0;
        }*/
        Ticket ticket = new Ticket();
        ticket.setBookingID(++bookingID);
        ticket.setPassengers(passengers);
        ticket.setFrom(from);
        ticket.setTo(to);
        Record.INSTANCE.addTicket(ticket);
        for (Customer customerInfo : passengers){
            if(Record.INSTANCE.getAvailableTickets() == 0){
                if(Record.INSTANCE.getRacCount() == 0){
                    if(Record.INSTANCE.getWaitingCount() == 0){
                        customerInfo.setTicketStatus("Failed");
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
                        seatNumber = allotLower(customerInfo);
                        if (seatNumber == 0) {
                            seatNumber = allotMiddle(customerInfo);
                            if (seatNumber == 0) {
                                seatNumber = allotUpper(customerInfo);
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
                        seatNumber = allotMiddle(customerInfo);
                        if (seatNumber == 0) {
                            seatNumber = allotLower(customerInfo);
                            if (seatNumber == 0) {
                                seatNumber = allotUpper(customerInfo);
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
                        seatNumber = allotUpper(customerInfo);
                        if (seatNumber == 0) {
                            seatNumber = allotMiddle(customerInfo);
                            if (seatNumber == 0) {
                                seatNumber = allotLower(customerInfo);
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
        }
        return bookingID;
    }

    private void waitingList(Customer customerInfo) {
    }

    private int allotUpper(Customer customerInfo) {
        ArrayList<Customer> upperBirth = (ArrayList<Customer>) Record.INSTANCE.getBookedBirths("upper");
        /*TreeSet<Integer> upperCancelled = (TreeSet<Integer>) Record.INSTANCE.upperCancelled;
        Integer seat = upperCancelled.pollFirst();
        if(seat != null){
            System.out.println("u "+seat);
            return seat;
        }
        int upperCount = upperBirth.size();
        System.out.println("Upper present : "+upperCount);
        if(upperCount == 2){
            return 0;
        }
        int nth = upperCount+1;
        int lastSeat = (upperCount*3) - (upperCount/3);
        if(nth % 3 == 0){
            seatNumber = lastSeat + 2;
        } else {
            seatNumber = lastSeat + 3;
        }*/


        TreeSet<Integer> availableUpper = (TreeSet<Integer>) Record.INSTANCE.getAvailableBirths("upper");
        Integer seat = availableUpper.pollFirst();
        if(seat != null){
            availableUpper.remove(seat);
        } else {
            return 0;
        }

        customerInfo.setSeatNumber(seat);
        customerInfo.setBirth('u');
        customerInfo.setTicketStatus("Confirmed");
        upperBirth.add(customerInfo);
        System.out.println("u "+seat);
        Record.INSTANCE.decrementTickets();
        return seat;
    }

    private int allotMiddle(Customer customerInfo) {
        ArrayList<Customer> middleBirth = (ArrayList<Customer>) Record.INSTANCE.getBookedBirths("middle");
        /*int middleCount = middleBirth.size();
        System.out.println("Middle present : "+middleCount);
        if(middleCount == 2){
            return 0;
        }
        if (middleCount % 2 != 0) {
            int nth = middleCount +1;
            seatNumber = (nth*3)+ middleCount -2;
        } else {
            int lastSeat = (middleCount *3)+(middleCount/2)-1;
            if(lastSeat <= 0){
                seatNumber = 2;
            }
            else{
                seatNumber = lastSeat+5;
            }
        }*/


        TreeSet<Integer> availableMiddle = (TreeSet<Integer>) Record.INSTANCE.getAvailableBirths("middle");
        Integer seat = availableMiddle.pollFirst();
        if(seat != null){
            availableMiddle.remove(seat);
        } else {
            return 0;
        }


        customerInfo.setSeatNumber(seat);
        customerInfo.setBirth('m');
        customerInfo.setTicketStatus("Confirmed");
        middleBirth.add(customerInfo);
        System.out.println("m "+seat);
        Record.INSTANCE.decrementTickets();
        return seat;
    }

    private int allotLower(Customer customerInfo) {
        ArrayList<Customer> lowerBirth = (ArrayList<Customer>) Record.INSTANCE.getBookedBirths("lower");
        /*int lowerCount = lowerBirth.size();
        System.out.println("Lower present : "+lowerCount);
        if(lowerCount == 2){
            return 0;
        }
        if (lowerCount % 2 != 0) {
            int nth = lowerCount +1;
            seatNumber = (nth*3)+ lowerCount -3;
        } else {
            int lastSeat = (lowerCount *3)+(lowerCount /2)-2;
            if(lastSeat <= 0){
                seatNumber = 1;
            }
            else{
                seatNumber = lastSeat+5;
            }
        }*/


        TreeSet<Integer> availableLower = (TreeSet<Integer>) Record.INSTANCE.getAvailableBirths("lower");
        Integer seat = availableLower.pollFirst();
        if(seat != null){
            availableLower.remove(seat);
        } else {
            return 0;
        }


        customerInfo.setSeatNumber(seat);
        customerInfo.setBirth('l');
        customerInfo.setTicketStatus("Confirmed");
        lowerBirth.add(customerInfo);
        System.out.println("l "+seat);
        Record.INSTANCE.decrementTickets();
        return seat;
    }

    private int allotAny(Customer customerInfo) {
        int seatNumber = allotUpper(customerInfo);
        if(seatNumber == 0){
            seatNumber = allotMiddle(customerInfo);
        }
        if(seatNumber == 0){
            seatNumber = allotLower(customerInfo);
        }
        System.out.println(seatNumber);
        return seatNumber;
    }

    private int allotRc(Customer customerInfo){
        LinkedList<Customer> bookedRc = (LinkedList<Customer>) Record.INSTANCE.reservationAgainstCancellation;
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
        System.out.println("r "+seat);
        Record.INSTANCE.decrementRacCount();
        return seat;
    }

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

}
