package come.geekbrains.myfragmentslessons.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.domain.Note;

public class NoteDetailActivity extends AppCompatActivity {

  private static final String EXTRA_NOTE = "EXTRA_NOTE";

  static void show(Context context, Note note){

    Intent intent = new Intent(context, NoteDetailActivity.class);
    intent.putExtra(EXTRA_NOTE, note);

    context.startActivity(intent);

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    if (savedInstanceState == null){

      Note note = getIntent().getParcelableExtra(EXTRA_NOTE);
      NoteDetailFragment noteDetailFragment = NoteDetailFragment.newInstance(note);

      getSupportFragmentManager()
              .beginTransaction()
              .replace(R.id.container, noteDetailFragment)
              .commit();

    }
  }
}