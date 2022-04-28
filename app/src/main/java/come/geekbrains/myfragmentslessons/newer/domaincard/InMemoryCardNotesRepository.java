package come.geekbrains.myfragmentslessons.newer.domaincard;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import come.geekbrains.myfragmentslessons.R;

public class InMemoryCardNotesRepository implements NotesCardRepository{

  private ArrayList<NoteCard> data = new ArrayList<>();

  private Executor mExecutor = Executors.newSingleThreadExecutor();

  private Handler mHandler = new Handler(Looper.getMainLooper());



  public InMemoryCardNotesRepository(){
    data.add(new NoteCard(UUID.randomUUID().toString(),"Birds","Description1",
            R.drawable.birds, new Date()));
    data.add(new NoteCard(UUID.randomUUID().toString(),"Boats","Description2",
            R.drawable.boats, new Date()));
    data.add(new NoteCard(UUID.randomUUID().toString(),"Cartoons","Description3",
            R.drawable.cartoon, new Date()));
    data.add(new NoteCard(UUID.randomUUID().toString(),"Flowers","Description4",
            R.drawable.flowers, new Date()));
    data.add(new NoteCard(UUID.randomUUID().toString(),"Oceans","Description5",
            R.drawable.oceans, new Date()));
    data.add(new NoteCard(UUID.randomUUID().toString(),"Spaces","Description6",
            R.drawable.space, new Date()));

  }

  @Override
  public void getAll(Callback<List<NoteCard>> callback) {

    mExecutor.execute(new Runnable() {
      @Override
      public void run() {

        try {
          Thread.sleep(2000);
        }catch (InterruptedException e){
          e.printStackTrace();
        }
        mHandler.post(new Runnable() {
          @Override
          public void run() {
            callback.onSuccess(data);
          }
        });

      }
    });
  }

  @Override
  public void addNote(String title, String message, Callback<NoteCard> callback) {

    mExecutor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        NoteCard noteCard = new NoteCard(UUID.randomUUID().toString(),title,message,
                R.drawable.boats, new Date());

        data.add(noteCard);

        mHandler.post(new Runnable() {
          @Override
          public void run() {
            callback.onSuccess(noteCard);
          }
        });
      }
    });
  }

  @Override
  public void removeNote(NoteCard note, Callback<Void> callback) {
    mExecutor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        data.remove(note);

        mHandler.post(new Runnable() {
          @Override
          public void run() {
            callback.onSuccess(null);
          }
        });
      }
    });

  }

  @Override
  public void updateNote(NoteCard note, String title, String message, Callback<NoteCard> callback) {

    mExecutor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        NoteCard newNoteCard = new NoteCard(note.getId(),title,message,
                R.drawable.img5, note.getCreationDate());

        int index = data.indexOf(note);


        data.set(index, newNoteCard);

        mHandler.post(new Runnable() {
          @Override
          public void run() {
            callback.onSuccess(newNoteCard);
          }
        });
      }
    });
  }
}
