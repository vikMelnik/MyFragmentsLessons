package come.geekbrains.myfragmentslessons.newer.uicard;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DefaultItemAnimator;
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

  private NoteCard selectedNote;
  private int selectedPosition;
  private ProgressBar progressBar;
  private NotesCardAdapter notesCardAdapter;

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
            AddNoteBottomSheetDialogFragment.addInstance()
                    .show(getParentFragmentManager(), "AddNoteBottomSheetDialogFragment");
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

    DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
    defaultItemAnimator.setAddDuration(3000L);
    recyclerView.setItemAnimator(defaultItemAnimator);

    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
    dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_divider));

    recyclerView.addItemDecoration(dividerItemDecoration);

    notesCardAdapter = new NotesCardAdapter(this);
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

      @Override
      public void onNoteLongClicked(NoteCard noteCard, int position) {
        selectedNote = noteCard;
        selectedPosition = position;

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

    getParentFragmentManager()
            .setFragmentResultListener(AddNoteBottomSheetDialogFragment.UPDATE_KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
              @Override
              public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                NoteCard note = result.getParcelable(AddNoteBottomSheetDialogFragment.ARG_NOTE);

                notesCardAdapter.replaceNote(note, selectedPosition);

                notesCardAdapter.notifyItemChanged(selectedPosition);
              }
            });

    view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          AddNoteBottomSheetDialogFragment.addInstance()
                .show(getParentFragmentManager(), "AddNoteBottomSheetDialogFragment");
      }
    });
    progressBar = view.findViewById(R.id.progress);
    progressBar.setVisibility(View.VISIBLE);
    Dependencies.getNotesCardRepository(requireContext()).getAll(new Callback<List<NoteCard>>() {
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

  @Override
  public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    MenuInflater menuInflater = requireActivity().getMenuInflater();
    menuInflater.inflate(R.menu.menu_notes_context, menu);
  }

  @Override
  public boolean onContextItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_delete:
        progressBar.setVisibility(View.VISIBLE);
        Dependencies.getNotesCardRepository(requireContext()).removeNote(selectedNote, new Callback<Void>() {
          @Override
          public void onSuccess(Void data) {
            progressBar.setVisibility(View.GONE);
            notesCardAdapter.removeNote(selectedNote);
            notesCardAdapter.notifyItemRemoved(selectedPosition);

          }
          @Override
          public void onError(Throwable exception) {
          }
        });
        return true;
      case R.id.action_edit:
        AddNoteBottomSheetDialogFragment.editInstance(selectedNote)
                .show(getParentFragmentManager(), "AddNoteBottomSheetDialogFragment");
        return true;
    }
    return super.onContextItemSelected(item);
  }
}
