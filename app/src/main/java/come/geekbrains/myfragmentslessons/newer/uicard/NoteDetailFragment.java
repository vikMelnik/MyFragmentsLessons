package come.geekbrains.myfragmentslessons.newer.uicard;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import java.text.SimpleDateFormat;
import java.util.Locale;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.newer.domaincard.NoteCard;
import come.geekbrains.myfragmentslessons.ui.DescriptionFragment;

public class NoteDetailFragment extends Fragment {

  private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd:MM, HH:mm", Locale.getDefault());
  private static final String ARG_NOTE = "ARG_NOTE";

  public static NoteDetailFragment newInstance(NoteCard noteCard) {
    Bundle args = new Bundle();
    args.putParcelable(ARG_NOTE, noteCard);
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
        Toast.makeText(requireContext(), "Data", Toast.LENGTH_SHORT).show();

      }
    });
    description.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        newCreateFragment(new DescriptionFragment());

      }

    });
    getParentFragmentManager()
            .setFragmentResultListener(NotesListCardFragment.NOTES_CLICKED_KEY, getViewLifecycleOwner()
                    , new FragmentResultListener() {
                      @Override
                      public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        NoteCard noteCard = result.getParcelable(NotesListCardFragment.SELECTED_NOTE);
                        show(noteCard);
                      }
                    });
    if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {
      NoteCard noteCard = getArguments().getParcelable(ARG_NOTE);
      show(noteCard);
    }
  }

  private void show(NoteCard noteCard) {
    title.setText(noteCard.getTitle());
    creationDate.setText(mSimpleDateFormat.format(noteCard.getCreationDate()));
    description.setText(noteCard.getDescription());
  }

  private void newCreateFragment(Fragment fragment) {
    getParentFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("details")
            .commit();
  }
}
