package come.geekbrains.myfragmentslessons.newer.uicard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.newer.Dependencies;
import come.geekbrains.myfragmentslessons.newer.domaincard.Callback;
import come.geekbrains.myfragmentslessons.newer.domaincard.NoteCard;

public class AddNoteBottomSheetDialogFragment extends BottomSheetDialogFragment {

  public static final String ADD_KEY_RESULT = "AddNoteBottomSheetDialogFragment_ADD_KEY_RESULT";
  public static final String UPDATE_KEY_RESULT = "AddNoteBottomSheetDialogFragment_UPDATE_KEY_RESULT";
  public static final String ARG_NOTE = "ARG_NOTE";

  public static AddNoteBottomSheetDialogFragment addInstance() {
    return new AddNoteBottomSheetDialogFragment();
  }

  public static AddNoteBottomSheetDialogFragment editInstance(NoteCard noteCard) {

    Bundle args = new Bundle();

    args.putParcelable(ARG_NOTE, noteCard);

    AddNoteBottomSheetDialogFragment fragment = new AddNoteBottomSheetDialogFragment();

    fragment.setArguments(args);

    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_add_note_bottom_sheet, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    super.onViewCreated(view, savedInstanceState);

    EditText title = view.findViewById(R.id.title);

    EditText message = view.findViewById(R.id.messages);

    Button btnSave = view.findViewById(R.id.save);

    btnSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        btnSave.setEnabled(false);
        Dependencies.NOTES_CARD_REPOSITORY.addNote(title.getText().toString(), message.getText().toString(), new Callback<NoteCard>() {

          @Override
          public void onSuccess(NoteCard data) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(ARG_NOTE, data);
            getParentFragmentManager().setFragmentResult(UPDATE_KEY_RESULT, bundle);
            btnSave.setEnabled(true);
            dismiss();
          }

          @Override
          public void onError(Throwable exception) {
            btnSave.setEnabled(true);
          }
        });

      }
    });
  }
}
