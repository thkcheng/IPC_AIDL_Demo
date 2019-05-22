package app.demo.aidl_library;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public int userId;

    public String userName;

    public String userSex;

    public User() {
    }

    protected User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        userSex = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeString(userSex);
    }

    public void readFromParcel(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        userSex = in.readString();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                '}';
    }
}
