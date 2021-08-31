package ticketreservation.details;

import java.util.List;

public class Ticket {
    private long bookingID;
    private List<Customer> passengers;
    private String from;
    private String to;

    public long getBookingID() {
        return bookingID;
    }

    public void setBookingID(long bookingID) {
        this.bookingID = bookingID;
    }

    public List<Customer> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Customer> passengers) {
        this.passengers = passengers;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
