package come.geekbrains.myfragmentslessons.newer.uicard;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.domain.ToolbarHolder;
import come.geekbrains.myfragmentslessons.newer.Dependencies;
import come.geekbrains.myfragmentslessons.newer.domaincard.NoteCard;


public class NotesListCardFragment extends Fragment {

  public static final String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
  public static final String SELECTED_NOTE = "SELECTED_NOTE";


  public NotesListCardFragment() {
    super(R.layout.fragment_notes_list_recycler);
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
    RecyclerView recyclerView = view.findViewById(R.id.notes_list_rc);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(layoutManager);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
    dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_divider));
    recyclerView.addItemDecoration(dividerItemDecoration);
    NotesCardAdapter notesCardAdapter = new NotesCardAdapter();
    notesCardAdapter.setNoteClicked(new NotesCardAdapter.OnNoteCardClicked() {
      @Override
      public void onNoteCardClicked(NoteCard noteCard) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
          Bundle bundle = new Bundle();
          bundle.putParcelable(SELECTED_NOTE, noteCard);
          getParentFragmentManager()
                  .setFragmentResult(NOTES_CLICKED_KEY, bundle);
        } else {
          getParentFragmentManager()
                  .beginTransaction()
                  .replace(R.id.fragment_container, NoteDetailFragment.newInstance(noteCard))
                  .addToBackStack("details")
                  .commit();
        }

      }
    });
    recyclerView.setAdapter(notesCardAdapter);
    List<NoteCard> notes = Dependencies.NOTES_CARD_REPOSITORY.getAll();
    notesCardAdapter.setData(notes);
    notesCardAdapter.notifyDataSetChanged();


  }
}
