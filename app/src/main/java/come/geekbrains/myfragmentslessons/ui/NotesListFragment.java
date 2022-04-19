package come.geekbrains.myfragmentslessons.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.util.List;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.domain.InMemoryNotesRepository;
import come.geekbrains.myfragmentslessons.domain.Note;
import come.geekbrains.myfragmentslessons.domain.ToolbarHolder;


public class NotesListFragment extends Fragment {

  public static final String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
  public static final String SELECTED_NOTE = "SELECTED_NOTE";

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_notes_list, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Toolbar toolbar = view.findViewById(R.id.toolbar);
    if (requireActivity() instanceof ToolbarHolder) {
      ((ToolbarHolder) requireActivity()).setToolbar(toolbar);
    }
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.action_search:
            Toast.makeText(requireContext(), "search", Toast.LENGTH_SHORT).show();
            return true;
          case R.id.action_sorted:
            Toast.makeText(requireContext(), "sorted", Toast.LENGTH_SHORT).show();
            return true;
          case R.id.action_add:
            Toast.makeText(requireContext(), "add", Toast.LENGTH_SHORT).show();
            return true;
          case R.id.action_share:
            Toast.makeText(requireContext(), "share", Toast.LENGTH_SHORT).show();
            return true;
          case R.id.action_exit:
            MyDialogFragment dialogFragment = new MyDialogFragment(requireContext());
            dialogFragment.createDialog(requireContext());
        }
        return false;
      }
    });
    List<Note> notes = InMemoryNotesRepository.getInstance(requireContext()).getAll();
    LinearLayout container = view.findViewById(R.id.container);
    for (Note note : notes) {
      View itemView = getLayoutInflater().inflate(R.layout.item_note, container, false);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(SELECTED_NOTE, note);
            getParentFragmentManager()
                    .setFragmentResult(NOTES_CLICKED_KEY, bundle);
          } else {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, NoteDetailFragment.newInstance(note))
                    .addToBackStack("details")
                    .commit();
          }
        }
      });
      TextView title = itemView.findViewById(R.id.title);
      title.setText(note.getTitle());
      container.addView(itemView);
    }
  }
}
