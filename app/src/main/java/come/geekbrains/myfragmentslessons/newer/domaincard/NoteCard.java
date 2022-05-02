package come.geekbrains.myfragmentslessons.newer.domaincard;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import java.util.Date;
import java.util.Objects;

public class NoteCard implements Parcelable {

  private String id;

  private String title; // заголовок

  private String description; // описание

  private final @DrawableRes
  int picture; // изображение

  private Date creationDate; // дата создания


  public NoteCard(String id, String title, String description, int picture, Date creationDate) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.picture = picture;
    this.creationDate = creationDate;
  }


  protected NoteCard(Parcel in) {
    id = in.readString();
    title = in.readString();
    description = in.readString();
    picture = in.readInt();
  }

  public static final Creator<NoteCard> CREATOR = new Creator<NoteCard>() {
    @Override
    public NoteCard createFromParcel(Parcel in) {
      return new NoteCard(in);
    }

    @Override
    public NoteCard[] newArray(int size) {
      return new NoteCard[size];
    }
  };

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public int getPicture() {
    return picture;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(id);
    parcel.writeString(title);
    parcel.writeString(description);
    parcel.writeInt(picture);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NoteCard noteCard = (NoteCard) o;
    return picture == noteCard.picture && Objects.equals(id, noteCard.id) && Objects.equals(title, noteCard.title) && Objects.equals(description, noteCard.description) && Objects.equals(creationDate, noteCard.creationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, picture, creationDate);
  }
}
