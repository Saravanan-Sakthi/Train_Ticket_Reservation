package ticketreservation.booking;

import ticketreservation.details.Customer;
import ticketreservation.details.Record;
import ticketreservation.details.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketOffice {
    private static long bookingID;

    public long bookTickets(List<Customer> passengers , String from , String to){
        if (Record.INSTANCE.getWaitingCount() + passengers.size()>10){
            return 0;
        }
        Ticket ticket = new Ticket();
        ticket.setBookingID(++bookingID);
        ticket.setPassengers(passengers);
        ticket.setFrom(from);
        ticket.setTo(to);
        Record.INSTANCE.addTicket(ticket);
        for (Customer customerInfo : passengers){
            switch (customerInfo.getBirthPreference()){
                case 'n':
                    allotAny(customerInfo);
                    break;
                case 'l':
                    allotLower(customerInfo);
                    break;
                case 'm':
                    allotMiddle(customerInfo);
                    break;
                case 'u':
                    allotUpper(customerInfo);
                    break;
            }
        }
        return bookingID;
    }

    private int allotUpper(Customer customerInfo) {
        ArrayList<Customer> upperBirth = (ArrayList<Customer>) Record.INSTANCE.getUpperBirths();
        int upperCount = upperBirth.size();
        if(upperCount >= 24){
            return 0;
        }
        int seatNumber;
        if (upperCount % 2 != 0) {
            int nth = upperCount+1;
            seatNumber = (nth*3)+upperCount-1;
        } else {
            int lastSeat = (upperCount*3)+(upperCount/2);
            if(lastSeat == 0){
                seatNumber = 3;
            }
            else{
                seatNumber = lastSeat+5;
            }
        }
        customerInfo.setSeatNumber(seatNumber);
        customerInfo.setBirth('u');
        return seatNumber;
    }

    private int allotMiddle(Customer customerInfo) {
        ArrayList<Customer> middleBirth = (ArrayList<Customer>) Record.INSTANCE.getMiddleBirths();
        int middleCount = middleBirth.size();
        if(middleCount >= 24){
            return 0;
        }
        int seatNumber;
        if (middleCount % 2 != 0) {
            int nth = middleCount +1;
            seatNumber = (nth*3)+ middleCount -2;
        } else {
            int lastSeat = (middleCount *3)+(middleCount/2)-1;
            if(lastSeat == 0){
                seatNumber = 2;
            }
            else{
                seatNumber = lastSeat+5;
            }
        }
        customerInfo.setSeatNumber(seatNumber);
        customerInfo.setBirth('m');
        return seatNumber;
    }

    private int allotLower(Customer customerInfo) {
        ArrayList<Customer> lowerBirth = (ArrayList<Customer>) Record.INSTANCE.getLowerBirths();
        int lowerCount = lowerBirth.size();
        if(lowerCount >= 24){
            return 0;
        }
        int seatNumber;
        if (lowerCount % 2 != 0) {
            int nth = lowerCount +1;
            seatNumber = (nth*3)+ lowerCount -3;
        } else {
            int lastSeat = (lowerCount *3)+(lowerCount /2)-2;
            if(lastSeat == 0){
                seatNumber = 1;
            }
            else{
                seatNumber = lastSeat+5;
            }
        }
        customerInfo.setSeatNumber(seatNumber);
        customerInfo.setBirth('l');
        return seatNumber;
    }

    private int allotAny(Customer customerInfo) {
        int seatNumber = allotUpper(customerInfo);
        if(seatNumber == 0){
            seatNumber = allotMiddle(customerInfo);
        }
        if(seatNumber == 0){
            seatNumber = allotLower(customerInfo);
        }
        return seatNumber;
    }

}
