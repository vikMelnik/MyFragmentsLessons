package come.geekbrains.myfragmentslessons.newer.domaincard;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import java.util.Date;

public class NoteCard implements Parcelable {

  private String id;

  private final @StringRes
  int title; // заголовок

  private final @StringRes
  int description; // описание

  private final @DrawableRes
  int picture; // изображение

  private Date creationDate; // дата создания


  public NoteCard(String id, int title, int description, int picture, Date creationDate) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.picture = picture;
    this.creationDate = creationDate;
  }


  protected NoteCard(Parcel in) {
    id = in.readString();
    title = in.readInt();
    description = in.readInt();
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

  public int getTitle() {
    return title;
  }

  public int getDescription() {
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
    parcel.writeInt(title);
    parcel.writeInt(description);
    parcel.writeInt(picture);
  }
}
