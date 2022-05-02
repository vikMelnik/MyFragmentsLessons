package come.geekbrains.myfragmentslessons.newer.domaincard;

import java.util.List;

public interface NotesCardRepository {

  void getAll(Callback<List<NoteCard>> callback);

  void addNote(String title, String message, Callback<NoteCard> callback);

  void removeNote(NoteCard note, Callback<Void> callback);

  void updateNote(NoteCard note, String title, String message, Callback<NoteCard> callback);
}
