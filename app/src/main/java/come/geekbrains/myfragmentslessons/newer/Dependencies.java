package come.geekbrains.myfragmentslessons.newer;

import come.geekbrains.myfragmentslessons.newer.domaincard.InMemoryCardNotesRepository;
import come.geekbrains.myfragmentslessons.newer.domaincard.NotesCardRepository;

public class Dependencies {

  public static final NotesCardRepository NOTES_CARD_REPOSITORY = new InMemoryCardNotesRepository();
}
