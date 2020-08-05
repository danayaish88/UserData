package eventBus;

public class FavEvent {
    Boolean fav = false;

    public FavEvent(Boolean fav) {
        this.fav = fav;
    }

    public Boolean getFav() {
        return fav;
    }
}
