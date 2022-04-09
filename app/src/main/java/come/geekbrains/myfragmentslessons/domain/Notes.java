package come.geekbrains.myfragmentslessons.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {

  private String title;
  private String description;
  private int date_creation;

  public Notes(String title) {
    this.title = title;
  }

  public Notes(String title, String description, int date_creation) {
    this.title = title;
    this.description = description;
    this.date_creation = date_creation;
  }

  protected Notes(Parcel in) {
    title = in.readString();
    description = in.readString();
    date_creation = in.readInt();
  }

  public static final Creator<Notes> CREATOR = new Creator<Notes>() {
    @Override
    public Notes createFromParcel(Parcel in) {
      return new Notes(in);
    }

    @Override
    public Notes[] newArray(int size) {
      return new Notes[size];
    }
  };

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public int getDate_creation() {
    return date_creation;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(title);
    parcel.writeString(description);
    parcel.writeInt(date_creation);
  }
}
