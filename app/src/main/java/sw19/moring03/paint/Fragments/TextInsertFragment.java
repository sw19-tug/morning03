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
import sw19.moring03.paint.Views.DrawingView;

public class TextInsertFragment extends DialogFragment {
    private String insertedText = "";

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
                        insertedText = userinput.getText().toString();
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

    @Override
    public void onStop() {
        DrawingView view = ((MainActivity) getContext()).findViewById(R.id.drawingView);
        view.addTextToView(insertedText);

        super.onStop();
    }
}
