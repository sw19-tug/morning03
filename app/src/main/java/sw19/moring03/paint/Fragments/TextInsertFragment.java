package sw19.moring03.paint.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.R;
import sw19.moring03.paint.Views.DrawingView;

public class TextInsertFragment extends DialogFragment {
    private String insertedText = "";
    private String font = "";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final EditText userinput = new EditText(getContext());
        userinput.setId(R.id.editText);
        userinput.setSingleLine();

        final ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, new String[] {"Normal", "Bold", "Italic", "Crazy"});

        final Spinner spinner = new Spinner(getActivity());
        spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        spinner.setAdapter(adp);

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(userinput);
        layout.addView(spinner);

        builder.setView(layout);
        builder.setTitle(R.string.insertTextFragment)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        insertedText = userinput.getText().toString();

                        switch (spinner.getSelectedItem().toString()) {
                            case "Normal":
                                font = "normal.ttf";
                                break;
                            case "Bold":
                                font = "bold.ttf";
                                break;
                            case "Italic":
                                font = "italic.ttf";
                                break;
                            case "Crazy":
                                font = "crazy.ttf";
                                break;
                            default:
                                break;
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

    @Override
    public void onStop() {
        DrawingView view = ((MainActivity) getContext()).findViewById(R.id.drawingView);
        view.drawingObjectManager.addTextToTool(insertedText);
        view.drawingObjectManager.addFontToTool(font);

        super.onStop();
    }
}
