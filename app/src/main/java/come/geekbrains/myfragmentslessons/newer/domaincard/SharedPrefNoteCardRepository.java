package come.geekbrains.myfragmentslessons.newer.domaincard;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import come.geekbrains.myfragmentslessons.R;

public class SharedPrefNoteCardRepository implements NotesCardRepository {

  private static final String KEY_SAVED_NOTES_CARD = "KEY_SAVED_NOTES_CARD";

  private SharedPreferences sharedPreferences;

  Gson gson = new Gson();
  ;

  public SharedPrefNoteCardRepository(Context context) {
    sharedPreferences = context.getSharedPreferences("NOTES", Context.MODE_PRIVATE);

  }

  @Override
  public void getAll(Callback<List<NoteCard>> callback) {
    String savedData = sharedPreferences.getString(KEY_SAVED_NOTES_CARD, "[]");
    Type type = new TypeToken<ArrayList<NoteCard>>() {
    }.getType();
    List<NoteCard> savedNoteCard = gson.fromJson(savedData, type);
    callback.onSuccess(savedNoteCard);


  }

  @Override
  public void addNote(String title, String message, Callback<NoteCard> callback) {
    NoteCard noteCard = new NoteCard(UUID.randomUUID().toString(), title, message,
            R.drawable.cartoon, new Date());
    String savedData = sharedPreferences.getString(KEY_SAVED_NOTES_CARD, "[]");
    Type type = new TypeToken<ArrayList<NoteCard>>() {
    }.getType();
    List<NoteCard> savedNoteCard = gson.fromJson(savedData, type);
    savedNoteCard.add(noteCard);
    String toWrite = gson.toJson(savedNoteCard, type);
    sharedPreferences
            .edit()
            .putString(KEY_SAVED_NOTES_CARD, toWrite)
            .apply();
    callback.onSuccess(noteCard);
  }

  @Override
  public void removeNote(NoteCard note, Callback<Void> callback) {
    String savedData = sharedPreferences.getString(KEY_SAVED_NOTES_CARD, "[]");
    Type type = new TypeToken<ArrayList<NoteCard>>() {
    }.getType();
    List<NoteCard> savedNoteCard = gson.fromJson(savedData, type);
    savedNoteCard.remove(note);
    String toWrite = gson.toJson(savedNoteCard, type);
    sharedPreferences
            .edit()
            .putString(KEY_SAVED_NOTES_CARD, toWrite)
            .apply();
    callback.onSuccess(null);

  }

  @Override
  public void updateNote(NoteCard note, String title, String message, Callback<NoteCard> callback) {
    NoteCard newNoteCard = new NoteCard(note.getId(), title, message,
            R.drawable.img5, note.getCreationDate());
    String savedData = sharedPreferences.getString(KEY_SAVED_NOTES_CARD, "[]");
    Type type = new TypeToken<ArrayList<NoteCard>>() {
    }.getType();
    List<NoteCard> savedNoteCard = gson.fromJson(savedData, type);
    int index = savedNoteCard.indexOf(note);
    savedNoteCard.set(index, newNoteCard);
    String toWrite = gson.toJson(savedNoteCard, type);
    sharedPreferences
            .edit()
            .putString(KEY_SAVED_NOTES_CARD, toWrite)
            .apply();
    callback.onSuccess(newNoteCard);
  }

}
