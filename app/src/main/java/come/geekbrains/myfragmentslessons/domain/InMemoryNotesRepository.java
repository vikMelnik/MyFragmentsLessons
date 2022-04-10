package come.geekbrains.myfragmentslessons.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import come.geekbrains.myfragmentslessons.R;

public class InMemoryNotesRepository implements NotesRepository{

  private static NotesRepository INSTANCE;

  public static NotesRepository getInstance(Context context){

    if (INSTANCE == null){
      INSTANCE = new InMemoryNotesRepository(context);
    }
    return INSTANCE;
  }

  private Context context;

  public InMemoryNotesRepository(Context context) {
    this.context = context;
  }

  @Override
  public List<Note> getAll() {
    ArrayList<Note> result = new ArrayList<>();

    result.add(new Note(context.getString(R.string.note1), context.getString(R.string.description1), context.getString(R.string.date1)));
    result.add(new Note(context.getString(R.string.note2), context.getString(R.string.description2), context.getString(R.string.date2)));
    result.add(new Note(context.getString(R.string.note3), context.getString(R.string.description3), context.getString(R.string.date3)));
    result.add(new Note(context.getString(R.string.note4), context.getString(R.string.description4), context.getString(R.string.date4)));
    result.add(new Note(context.getString(R.string.note5), context.getString(R.string.description5), context.getString(R.string.date5)));
    result.add(new Note(context.getString(R.string.note6), context.getString(R.string.description6), context.getString(R.string.date6)));

    return result;
  }

  @Override
  public void add(Note note) {
  }
}
