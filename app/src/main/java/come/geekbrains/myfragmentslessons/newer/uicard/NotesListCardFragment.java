package come.geekbrains.myfragmentslessons.newer.uicard;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.newer.Dependencies;
import come.geekbrains.myfragmentslessons.newer.domaincard.InMemoryCardNotesRepository;
import come.geekbrains.myfragmentslessons.newer.domaincard.NoteCard;


public class NotesListCardFragment extends Fragment {


  public NotesListCardFragment() {
    super(R.layout.fragment_notes_list_recycler);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    RecyclerView recyclerView = view.findViewById(R.id.notes_list_rc);

    recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

    NotesCardAdapter notesCardAdapter = new NotesCardAdapter();

    notesCardAdapter.setNoteClicked(new NotesCardAdapter.OnNoteCardClicked() {
      @Override
      public void onNoteCardClicked(NoteCard noteCard) {
        Toast.makeText(requireContext(), noteCard.getTitle(), Toast.LENGTH_SHORT).show();
      }
    });

    recyclerView.setAdapter(notesCardAdapter);

    List<NoteCard> notes = Dependencies.NOTES_CARD_REPOSITORY.getAll();

    notesCardAdapter.setData(notes);

    notesCardAdapter.notifyDataSetChanged();


  }
}
