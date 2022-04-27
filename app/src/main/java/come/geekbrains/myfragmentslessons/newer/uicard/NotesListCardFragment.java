package come.geekbrains.myfragmentslessons.newer.uicard;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.newer.Dependencies;
import come.geekbrains.myfragmentslessons.newer.domaincard.Callback;
import come.geekbrains.myfragmentslessons.newer.domaincard.NoteCard;
import come.geekbrains.myfragmentslessons.newer.domaincard.ToolbarHolder;


public class NotesListCardFragment extends Fragment {

  public static final String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
  public static final String SELECTED_NOTE = "SELECTED_NOTE";
  NotesCardAdapter notesCardAdapter = new NotesCardAdapter();

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

            Dependencies.NOTES_CARD_REPOSITORY.addNote("Title 8", "Descriptions 8", new Callback<NoteCard>() {

              @Override
              public void onSuccess(NoteCard data) {

              }

              @Override
              public void onError(Throwable exception) {
              }
            });
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
    getParentFragmentManager()
            .setFragmentResultListener(AddNoteBottomSheetDialogFragment.ADD_KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
              @Override
              public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                NoteCard noteCard = result.getParcelable(AddNoteBottomSheetDialogFragment.ARG_NOTE);
                int index = notesCardAdapter.addNote(noteCard);
                notesCardAdapter.notifyItemInserted(index);
                recyclerView.smoothScrollToPosition(index);
              }
            });
    view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        new AddNoteBottomSheetDialogFragment()
                .show(getParentFragmentManager(), "AddNoteBottomSheetDialogFragment");
      }
    });
    ProgressBar progressBar = view.findViewById(R.id.progress);
    progressBar.setVisibility(View.VISIBLE);
    Dependencies.NOTES_CARD_REPOSITORY.getAll(new Callback<List<NoteCard>>() {
      @Override
      public void onSuccess(List<NoteCard> data) {
        notesCardAdapter.setData(data);
        notesCardAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
      }

      @Override
      public void onError(Throwable exception) {
        progressBar.setVisibility(View.GONE);
      }
    });


  }
}
