package DataModels;

public class Company {
    private String name;
    private String bs;
    private String catchPhrase;

    public Company(String name, String bs, String catchPhrase) {
        this.name = name;
        this.bs = bs;
        this.catchPhrase = catchPhrase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }
}
