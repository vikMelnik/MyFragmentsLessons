package come.geekbrains.myfragmentslessons.domain;

import java.util.List;

public interface NotesRepository {

  List<Notes> getAll();

  void add(Notes notes);
}
