package come.geekbrains.myfragmentslessons.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

  private String title;
  private String description;
  private String date_creation;

  public Note(String title, String description, String date_creation) {
    this.title = title;
    this.description = description;
    this.date_creation = date_creation;
  }

  protected Note(Parcel in) {
    title = in.readString();
    description = in.readString();
    date_creation = in.readString();
  }

  public static final Creator<Note> CREATOR = new Creator<Note>() {
    @Override
    public Note createFromParcel(Parcel in) {
      return new Note(in);
    }

    @Override
    public Note[] newArray(int size) {
      return new Note[size];
    }
  };

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getDate_creation() {
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
    parcel.writeString(date_creation);
  }
}
