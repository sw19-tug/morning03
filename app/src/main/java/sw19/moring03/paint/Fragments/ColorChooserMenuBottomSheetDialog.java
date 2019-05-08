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

public class ColorChooserMenuBottomSheetDialog extends BottomSheetDialogFragment {
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.color_chooser_menu, container, false);

        SeekBar seekBarRed = view.findViewById(R.id.redSlider);
        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                red = progressValue;
                ((MainActivity)getContext()).setChosenColor(R.color.custom);

            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }

        });

        SeekBar seekBarGreen = view.findViewById(R.id.greenSlider);
        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                green = progressValue;
                ((MainActivity)getContext()).setChosenColor(R.color.custom);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }

        });

        SeekBar seekBarBlue = view.findViewById(R.id.blueSlider);
        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                blue = progressValue;
                ((MainActivity)getContext()).setChosenColor(R.color.custom);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }

        });

        return view;
    }
}
