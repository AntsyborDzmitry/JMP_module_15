package beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private String name ;
    private List<FriendShip> friends ;
    private List<Message> messages ;
    private int friendsCount ;
    private int age ;
    private int viewedMovies ;
    private int listenTracks ;

    public User() {
        this.messages = new ArrayList<Message>();
        this.friends = new ArrayList<FriendShip>();
    }

    public User(String name, int age, int viewedMovies, int listenTracks) {
        this.name = name;
        this.age = age;
        this.viewedMovies = viewedMovies;
        this.listenTracks = listenTracks;
        this.messages = new ArrayList<Message>();
        this.friends = new ArrayList<FriendShip>();
        this.friendsCount = this.friends.size();
    }

    public User(String name, List<FriendShip> friends, int age, int viewedMovies, int listenTracks) {
        this.name = name;
        this.friends = friends;
        this.viewedMovies = viewedMovies;
        this.listenTracks = listenTracks;
        this.messages = new ArrayList<Message>();
        this.age = age;
        this.friendsCount = this.friends.size();
    }

    public User(String name, List<FriendShip> friends, List<Message> messages, int age, int viewedMovies, int listenTracks) {
        this.name = name;
        this.friends = friends;
        this.messages = messages;
        this.viewedMovies = viewedMovies;
        this.listenTracks = listenTracks;
        this.age = age;
        this.friendsCount = this.friends.size();
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
        this.friendsCount = this.friends.size();
    }
    public void setFriends(List<FriendShip> friends) {
        this.friends = friends;
        this.friendsCount = this.friends.size();
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
    public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

    public int getViewedMovies() {
        return viewedMovies;
    }

    public void setViewedMovies(int viewedMovies) {
        this.viewedMovies = viewedMovies;
    }

    public int getListedTracks() {
        return listenTracks;
    }

    public void setListenTracks(int listenTracks) {
        this.listenTracks = listenTracks;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }
}
