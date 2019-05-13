package sw19.moring03.paint.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.R;

public class TextInsertFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final EditText userinput = new EditText(getContext());
        userinput.setId(R.id.editText);
        builder.setView(userinput);
        builder.setTitle(R.string.insertTextFragment)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (getContext() != null) {
                            MainActivity activity = (MainActivity) getContext();
                            activity.setTextToInsert(userinput.getText().toString());
                        }
                    }
                }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );
        return builder.create();

    }
}
