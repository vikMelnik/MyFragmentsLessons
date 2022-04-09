package come.geekbrains.myfragmentslessons.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.domain.InMemoryNotesRepository;
import come.geekbrains.myfragmentslessons.domain.Note;

public class NotesListFragment extends Fragment {



  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_notes_list, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    List<Note> notes = InMemoryNotesRepository.getInstance(requireContext()).getAll();
    LinearLayout container = view.findViewById(R.id.container);

    for (Note note: notes){

      View itemView = getLayoutInflater().inflate(R.layout.item_note, container,false);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
      });




      TextView title = itemView.findViewById(R.id.title);
      title.setText(note.getTitle());

      container.addView(itemView);


    }


  }
}
