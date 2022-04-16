package come.geekbrains.myfragmentslessons.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import come.geekbrains.myfragmentslessons.R;

public class PreferenceFragment extends Fragment {

  public PreferenceFragment() {
    super(R.layout.fragment_preference);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Toolbar toolbar = view.findViewById(R.id.toolbar);

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getParentFragmentManager()
                .popBackStack();
      }
    });


  }

  }



