package ticketreservation.details;

import java.util.List;

public class Ticket {
    private long bookingID;
    private long mobile;
    private String email;
    private List<Customer> passengers;
    private String from;
    private String to;

    public long getBookingID() {
        return bookingID;
    }

    public void setBookingID(long bookingID) {
        this.bookingID = bookingID;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "\nBooking ID : " + bookingID +'\n'+
                "Mobile : " + mobile +'\n'+
                "Email : " + email + '\n' +
                "Passengers :\n" + passengers +'\n'+
                "From : " + from + '\n' +
                "To : " + to + '\n' ;
    }
}
