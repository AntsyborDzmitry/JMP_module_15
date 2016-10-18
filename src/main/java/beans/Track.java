package beans;


public class Track {

    private  String title ;
    private  int likes ;

    public Track(String title, int likes) {
        this.title = title;
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
