package come.geekbrains.myfragmentslessons.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.domain.Note;
import come.geekbrains.myfragmentslessons.domain.ToolbarHolder;

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

  private TextView creationDate;
  private TextView title;
  private TextView description;

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Toolbar toolbar = view.findViewById(R.id.toolbar);


    creationDate = view.findViewById(R.id.date_creation);
    title = view.findViewById(R.id.title);
    description = view.findViewById(R.id.description);

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getParentFragmentManager()
                .popBackStack();
      }
    });
    creationDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        newCreateFragment(new DateFragment());
      }
    });

    description.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        newCreateFragment(new DescriptionFragment());

      }

    });
    getParentFragmentManager()
            .setFragmentResultListener(NotesListFragment.NOTES_CLICKED_KEY, getViewLifecycleOwner()
                    , new FragmentResultListener() {
                      @Override
                      public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = result.getParcelable(NotesListFragment.SELECTED_NOTE);
                        show(note);
                      }
                    });
    if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {
      Note note = getArguments().getParcelable(ARG_NOTE);
      show(note);
    }
  }

  private void show(Note note) {
    title.setText(note.getTitle());
    creationDate.setText(note.getCreationDate());
    description.setText(note.getDescription());
  }

  private void newCreateFragment(Fragment fragment) {
    getParentFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("details")
            .commit();
  }
}
