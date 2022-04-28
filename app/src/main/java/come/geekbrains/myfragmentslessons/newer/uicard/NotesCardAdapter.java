package come.geekbrains.myfragmentslessons.newer.uicard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.newer.domaincard.NoteCard;

public class NotesCardAdapter extends RecyclerView.Adapter<NotesCardAdapter.NotesCardViewHolder> {

  public OnNoteCardClicked getNoteClicked() {
    return noteClicked;
  }

  private Fragment fragment;

  public NotesCardAdapter(Fragment fragment) {
    this.fragment = fragment;
  }

  private OnNoteCardClicked noteClicked;

  private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd:MM, HH:mm", Locale.getDefault());

  private List<NoteCard> data = new ArrayList<>();

  public void removeNote(NoteCard selectedNote) {
    data.remove(selectedNote);
  }

  public void replaceNote(NoteCard note, int selectedPosition) {
    data.set(selectedPosition, note);
  }

  interface OnNoteCardClicked {
    void onNoteCardClicked(NoteCard noteCard);

    void onNoteLongClicked(NoteCard noteCard, int position);
  }

  public void setNoteClicked(OnNoteCardClicked noteClicked) {
    this.noteClicked = noteClicked;
  }

  public int addNote(NoteCard noteCard) {
    data.add(noteCard);
    return data.size() - 1;
  }


  public void setData(Collection<NoteCard> noteCards) {
    data.addAll(noteCards);
  }

  @NonNull
  @Override
  public NotesCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
    NotesCardViewHolder notesCardViewHolder = new NotesCardViewHolder(itemView);
    return notesCardViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull NotesCardViewHolder holder, int position) {
    NoteCard noteCard = data.get(position);
    holder.title.setText(noteCard.getTitle());
    holder.description.setText(noteCard.getDescription());
    holder.imageView.setImageResource(noteCard.getPicture());
    holder.createdata.setText(mSimpleDateFormat.format(noteCard.getCreationDate()));
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  class NotesCardViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    ImageView imageView;
    TextView description;
    TextView createdata;

    public NotesCardViewHolder(@NonNull View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.title);
      imageView = itemView.findViewById(R.id.imageView);
      description = itemView.findViewById(R.id.description);
      createdata = itemView.findViewById(R.id.create_data);
      CardView cardView = itemView.findViewById(R.id.card_view);
      fragment.registerForContextMenu(cardView);
      cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          if (noteClicked != null) {
            int clickedPosition = getAdapterPosition();
            noteClicked.onNoteCardClicked(data.get(clickedPosition));
          }
        }
      });
      cardView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          cardView.showContextMenu();
          if (noteClicked != null) {
            int clickedPosition = getAdapterPosition();
            noteClicked.onNoteLongClicked(data.get(clickedPosition), clickedPosition);
          }
          return true;
        }
      });
    }
  }
}
