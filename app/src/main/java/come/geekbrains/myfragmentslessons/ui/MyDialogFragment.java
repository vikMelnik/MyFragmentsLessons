package come.geekbrains.myfragmentslessons.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import come.geekbrains.myfragmentslessons.R;

public class MyDialogFragment extends AlertDialog {
  AlertDialog.Builder dialog;

  protected MyDialogFragment(@NonNull Context context) {
    super(context);
  }

  protected void createDialog(Context context) {
    dialog = new AlertDialog.Builder(context);
    dialog.setIcon(R.drawable.img5);
    dialog.setTitle(R.string.app_name);
    dialog.setMessage(R.string.dialog_msg);
    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        new Activity().finish();
        System.exit(0);
      }
    });
    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
      }
    });
    dialog.show();
  }
}
