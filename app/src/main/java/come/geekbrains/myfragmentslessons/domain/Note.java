package come.geekbrains.myfragmentslessons.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

  private String title;
  private String description;
  private String creationDate;

  public Note(String title, String description, String creationDate) {
    this.title = title;
    this.description = description;
    this.creationDate = creationDate;
  }

  protected Note(Parcel in) {
    title = in.readString();
    description = in.readString();
    creationDate = in.readString();
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

  public String getCreationDate() {
    return creationDate;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(title);
    parcel.writeString(description);
    parcel.writeString(creationDate);
  }
}
