package come.geekbrains.myfragmentslessons.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import come.geekbrains.myfragmentslessons.R;
import come.geekbrains.myfragmentslessons.domain.ToolbarHolder;

public class MainActivity extends AppCompatActivity implements ToolbarHolder {

  private DrawerLayout drawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    drawerLayout = findViewById(R.id.drawer);
    NavigationView navigationView = findViewById(R.id.navigation);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.action_about_app:
            Toast.makeText(getApplicationContext(), "About Owner", Toast.LENGTH_SHORT).show();
            drawerLayout.close();
            return true;
          case R.id.action_preference:
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.fragment_container, new PreferenceFragment())
                    .commit();
            drawerLayout.close();
            return true;
        }
        return false;
      }
    });
  }

  @Override
  public void setToolbar(Toolbar toolbar) {
    ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close);
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();
  }
}
