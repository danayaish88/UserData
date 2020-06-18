package DataModels;

import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable {
    private String name;
    private String bs;
    private String catchPhrase;

    public Company(String name, String bs, String catchPhrase) {
        this.name = name;
        this.bs = bs;
        this.catchPhrase = catchPhrase;
    }

    protected Company(Parcel in) {
        name = in.readString();
        bs = in.readString();
        catchPhrase = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    @Override
    public String toString() {
        return  name + '\n' +
                catchPhrase + '\n' +
                 bs ;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(bs);
        parcel.writeString(catchPhrase);
    }
}
