package eventBus;

public class FavEvent {
    Boolean fav = false;
    Integer id = 0;

    public FavEvent(Boolean fav, Integer id) {
        this.fav = fav;
        this.id = id;
    }

    public Boolean getFav() {
        return fav;
    }

    public Integer getId() {
        return id;
    }
}
