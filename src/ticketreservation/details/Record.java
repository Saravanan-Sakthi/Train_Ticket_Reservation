package ticketreservation.details;

import java.util.*;

public enum Record {
    INSTANCE;
    private int availableTickets = 63;
    private int waitingCount;

    public int getAvailableTickets() {
        return availableTickets;
    }

    public int getWaitingCount() {
        return waitingCount;
    }

    private Map <Long , Ticket> bookedTickets = new HashMap<>();
    private Map<String , List<Customer>> bookedBirths = new HashMap<>();
    private Queue<Customer> waitingList = new LinkedList<>();

    public void decrementTickets(){
        INSTANCE.availableTickets--;
    }

    public void addTicket(Ticket ticket){
        bookedTickets.put(ticket.getBookingID() , ticket);
    }

    public List<Customer> getLowerBirths(){
        if(! bookedBirths.containsKey("lower")) {
            bookedBirths.put("lower" , new ArrayList<Customer>());
        }
        List<Customer> lowerBirths = bookedBirths.get("lower");
        return lowerBirths;
    }

    public List<Customer> getUpperBirths(){
        if(! bookedBirths.containsKey("upper")) {
            bookedBirths.put("upper" , new ArrayList<Customer>());
        }
        List<Customer> upperBirths = bookedBirths.get("upper");
        return upperBirths;
    }

    public List<Customer> getMiddleBirths(){
        if(! bookedBirths.containsKey("middle")) {
            bookedBirths.put("middle" , new ArrayList<Customer>());
        }
        List<Customer> middleBirths = bookedBirths.get("middle");
        return middleBirths;
    }

}
