package come.geekbrains.myfragmentslessons.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.domain.Note;

public class NoteDetailFragment extends Fragment {

  private static final String ARG_NOTE = "ARG_NOTE";

  public static NoteDetailFragment newInstance(Note note) {
    Bundle args = new Bundle();
    args.putParcelable(ARG_NOTE, note);
    NoteDetailFragment fragment = new NoteDetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public NoteDetailFragment() {
    super(R.layout.fragment_note_detail);
  }

  private EditText date_creation;
  private TextView title;
  private EditText description;

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    date_creation = view.findViewById(R.id.date_creation);
    title = view.findViewById(R.id.title);
    description = view.findViewById(R.id.description);
    getParentFragmentManager()
            .setFragmentResultListener(NotesListFragment.NOTES_CLICKED_KEY, getViewLifecycleOwner()
                    , new FragmentResultListener() {
                      @Override
                      public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = result.getParcelable(NotesListFragment.SELECTED_NOTE);
                        show(note);
                      }


                    });
    if (getArguments() != null && getArguments().containsKey(ARG_NOTE)){

      Note note = getArguments().getParcelable(ARG_NOTE);

      show(note);
    }

  }
  private void show(Note note) {

    title.setText(note.getTitle());
    date_creation.setText(note.getDate_creation());
    description.setText(note.getDescription());
  }

}
