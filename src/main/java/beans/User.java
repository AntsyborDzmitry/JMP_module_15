package beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private String name ;
    private List<FriendShip> friends ;
    private List<Message> messages ;
    private int age ;

    public User() {
        this.messages = new ArrayList<Message>();
        this.friends = new ArrayList<FriendShip>();
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.messages = new ArrayList<Message>();
        this.friends = new ArrayList<FriendShip>();
    }

    public User(String name, List<FriendShip> friends, int age) {
        this.name = name;
        this.friends = friends;
        this.messages = new ArrayList<Message>();
        this.age = age;
    }

    public User(String name, List<FriendShip> friends, List<Message> messages, int age) {
        this.name = name;
        this.friends = friends;
        this.messages = messages;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<FriendShip>  getFriends() {
        return friends;
    }

    public void setFriends(String friendName) {
        if(friendName != null) {
            FriendShip fr = new FriendShip(friendName, LocalDate.now());
            this.friends.add(fr);
        }
    }
    public void setFriends(List<FriendShip> friends) {
        this.friends = friends;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessage(String message) {
        if(message != null) {
            Message ms = new Message(message, LocalDate.now());
            this.messages.add(ms);
        }
    }
}
