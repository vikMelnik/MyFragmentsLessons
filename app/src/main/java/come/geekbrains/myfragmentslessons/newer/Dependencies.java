package come.geekbrains.myfragmentslessons.newer;

import android.content.Context;

import come.geekbrains.myfragmentslessons.newer.domaincard.InMemoryCardNotesRepository;
import come.geekbrains.myfragmentslessons.newer.domaincard.NotesCardRepository;
import come.geekbrains.myfragmentslessons.newer.domaincard.SharedPrefNoteCardRepository;

public class Dependencies {

  private static NotesCardRepository sNotesCardRepository;

  public static NotesCardRepository getNotesCardRepository(Context context) {

    if (sNotesCardRepository == null){
      sNotesCardRepository = new SharedPrefNoteCardRepository(context);
    }
    return sNotesCardRepository;
  }
}
