package sw19.moring03.paint.Fragments;

import android.graphics.PathEffect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.R;

public class LineTypeChooserBottomSheetDialog extends BottomSheetDialogFragment {
    public PathEffect effect;

    public PathEffect getStrokeWidth() {
        return effect;
    }

    public void setStrokeWidth(PathEffect nEffect) {
        effect = nEffect;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.line_type_chooser, container, false);

        final Spinner spinner = view.findViewById(R.id.lineType);

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(spinner.getSelectedItem().toString().equals("Solid Line"))
                {
                    spinner.setSelection(0);
                    ((MainActivity)getContext()).setLineEffect(MainActivity.PATHEFFECTS.SOLID);
                }
                else if(spinner.getSelectedItem().toString().equals("Dashed Line"))
                {
                    spinner.setSelection(1);
                    ((MainActivity)getContext()).setLineEffect(MainActivity.PATHEFFECTS.DASHED);
                }
                else if(spinner.getSelectedItem().toString().equals("Doted Line"))
                {
                    spinner.setSelection(2);
                    ((MainActivity)getContext()).setLineEffect(MainActivity.PATHEFFECTS.DOTED);
                }
                else if(spinner.getSelectedItem().toString().equals("Dashed-Doted Line"))
                {
                    spinner.setSelection(3);
                    ((MainActivity)getContext()).setLineEffect(MainActivity.PATHEFFECTS.DASHEDDOTED);
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        return view;
    }
}
