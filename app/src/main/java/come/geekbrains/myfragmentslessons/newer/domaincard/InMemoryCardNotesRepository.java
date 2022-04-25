package come.geekbrains.myfragmentslessons.newer.domaincard;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import come.geekbrains.myfragmentslessons.R;

public class InMemoryCardNotesRepository implements NotesCardRepository{

  private ArrayList<NoteCard> data = new ArrayList<>();



  public InMemoryCardNotesRepository(){
    data.add(new NoteCard(UUID.randomUUID().toString(),(R.string.title1),(R.string.description1),
            R.drawable.birds, new Date()));
    data.add(new NoteCard(UUID.randomUUID().toString(),(R.string.title2),(R.string.description2),
            R.drawable.boats, new Date()));
    data.add(new NoteCard(UUID.randomUUID().toString(),(R.string.title3),(R.string.description3),
            R.drawable.cartoon, new Date()));
    data.add(new NoteCard(UUID.randomUUID().toString(),(R.string.title4),(R.string.description4),
            R.drawable.flowers, new Date()));
    data.add(new NoteCard(UUID.randomUUID().toString(),(R.string.title5),(R.string.description5),
            R.drawable.oceans, new Date()));
    data.add(new NoteCard(UUID.randomUUID().toString(),(R.string.title6),(R.string.description6),
            R.drawable.space, new Date()));
    for (int i = 0; i < 100; i ++){
      data.add(new NoteCard(UUID.randomUUID().toString(),(R.string.title3),(R.string.description3),
              R.drawable.cartoon, new Date()));
    }

  }
  @Override
  public List<NoteCard> getAll() {
    return data;
  }
}
