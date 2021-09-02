package ticketreservation.details;

import java.util.*;

public enum Record {
    INSTANCE;
    private int availableTickets = 7;
    private int racSeatCount = 2;
    private int waitingSeatCount =3;
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
    public void incrementTickets(){
        INSTANCE.availableTickets++;
    }

    public int getRacSeatCount(){
        return racSeatCount;
    }

    public void decrementRacCount(){
        racSeatCount--;
    }
    public void incrementRacCount(){
        racSeatCount++;
    }

    public int getWaitingSeatCount() {
        return waitingSeatCount;
    }

    public void decrementWaitingCount(){
        waitingSeatCount--;
    }
    public void incrementWaitingCount(){
        waitingSeatCount++;
    }

    public void addTicket(Ticket ticket){
        bookedTickets.put(ticket.getBookingID() , ticket);
    }

    public Ticket getTicket(long bookingID){
        return bookedTickets.get(bookingID);
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
        this.availableBirths.put(birth , availableBirths);
    }

    public Queue<Customer> getWaitingList() {
        return waitingList;
    }

    public Queue<Customer> getReservationAgainstCancellation() {
        return reservationAgainstCancellation;
    }
}
