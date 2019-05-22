package sw19.moring03.paint.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.R;
import sw19.moring03.paint.utils.Tool;

public class ShapeChooserFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.shapeChooserFragment)
                .setItems(R.array.shape_type, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String[] types = getResources().getStringArray(R.array.shape_type);

                            if (getContext() != null) {
                                MainActivity activity = (MainActivity) getContext();

                                switch (types[which]) {
                                    case "Circle":
                                        activity.setChosenTool(Tool.DRAW_CIRCLE);
                                        break;
                                    case "Rectangle":
                                        activity.setChosenTool(Tool.DRAW_RECTANGLE);
                                        break;
                                    case "Oval":
                                        activity.setChosenTool(Tool.DRAW_OVAL);
                                        break;
                                }
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
