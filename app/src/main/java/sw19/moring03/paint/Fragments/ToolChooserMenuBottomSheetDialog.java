package sw19.moring03.paint.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.R;

public class ToolChooserMenuBottomSheetDialog extends BottomSheetDialogFragment {
    public int strokeWidth = 0;

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tool_chooser_menu, container, false);

        SeekBar seekBar = view.findViewById(R.id.strokeWidth);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                strokeWidth = progresValue;
                ((MainActivity)getContext()).setStrokeWidth(strokeWidth);
                
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }

        });
        return view;
    }
}
