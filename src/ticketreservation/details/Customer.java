package ticketreservation.details;

public class Customer {
    private String name;
    private int age;
    private String gender;
    private long mobile;
    private String email;
    private char birthPreference;
    private String ticketStatus;
    private int seatNumber;
    private char birth;

    public char getBirth() {
        return birth;
    }

    public void setBirth(char birth) {
        this.birth = birth;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public char getBirthPreference() {
        return birthPreference;
    }

    public void setBirthPreference(char birthPreference) {
        this.birthPreference = birthPreference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
