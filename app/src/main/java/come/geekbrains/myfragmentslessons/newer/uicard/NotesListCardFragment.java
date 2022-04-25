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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.newer.Dependencies;
import come.geekbrains.myfragmentslessons.newer.domaincard.InMemoryCardNotesRepository;
import come.geekbrains.myfragmentslessons.newer.domaincard.NoteCard;


public class NotesListCardFragment extends Fragment {

  private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd:MM, HH:mm", Locale.getDefault());

  public NotesListCardFragment() {
    super(R.layout.fragment_notes_list);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
      LinearLayout container = view.findViewById(R.id.container);

    List<NoteCard> notes = Dependencies.NOTES_CARD_REPOSITORY.getAll();

    for (NoteCard noteCard: notes){
      View itemView = getLayoutInflater().inflate(R.layout.item_card, container, false);

      itemView.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Toast.makeText(requireContext(),getText(noteCard.getTitle()), Toast.LENGTH_SHORT).show();
        }
      });
      TextView title = itemView.findViewById(R.id.title);
      ImageView imageView = itemView.findViewById(R.id.imageView);
      TextView description = itemView.findViewById(R.id.description);
      TextView createdata = itemView.findViewById(R.id.create_data);

      title.setText(noteCard.getTitle());
      description.setText(noteCard.getDescription());
      imageView.setImageResource(noteCard.getPicture());;
      createdata.setText(mSimpleDateFormat.format(noteCard.getCreationDate()));

      container.addView(itemView);

    }

  }
}
