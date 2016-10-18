package beans;


import java.time.LocalDate;

public class FriendShip {

    private String friend ;
    private LocalDate timestamp ;

    public FriendShip(String friend, LocalDate timestamp) {
        this.friend = friend;
        this.timestamp = timestamp;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
