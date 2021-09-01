package ticketreservation.details;

import java.util.*;

public enum Record {
    INSTANCE;
    private int availableTickets = 63;
    private int racCount = 18;
    private int waitingCount =10;
    private Map <Long , Ticket> bookedTickets = new HashMap<>();
    private Map<String , List<Customer>> bookedBirths = new HashMap<>();
    public Map<String , Set<Integer>> availableBirths = new HashMap<>();
    private Queue<Customer> waitingList = new LinkedList<>();
    public Queue<Customer> reservationAgainstCancellation = new LinkedList<>();

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void decrementTickets(){
        INSTANCE.availableTickets--;
    }

    public int getRacCount(){
        return racCount;
    }

    public void decrementRacCount(){
        racCount--;
    }

    public int getWaitingCount() {
        return waitingCount;
    }

    public void decrementWaitingCount(){
        waitingCount--;
    }

    public void addTicket(Ticket ticket){
        bookedTickets.put(ticket.getBookingID() , ticket);
    }

    public List<Customer> getBookedBirths(String birth){
        if(! bookedBirths.containsKey(birth)) {
            bookedBirths.put(birth , new ArrayList<>());
        }
        List<Customer> births = bookedBirths.get(birth);
        return births;
    }

    public Set<Integer> getAvailableBirths(String birth){
        if(! availableBirths.containsKey(birth)) {
            availableBirths.put(birth , new TreeSet<>());
        }
        Set<Integer> births = availableBirths.get(birth);
        return births;
    }

    public void setAvailableBirths(String birth , Set<Integer> availableBirths) {
        this.availableBirths.put("upper" , availableBirths);
    }
}
