package sw19.moring03.paint.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.R;

public class ColorChooserMenuBottomSheetDialog extends BottomSheetDialogFragment {
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    @ColorInt
    private int chosenColor;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.color_chooser_menu, container, false);

        SeekBar seekBarRed = view.findViewById(R.id.redSlider);
        final TextView redValue = view.findViewById(R.id.valueRed);
        final Button imageButton = view.findViewById(R.id.currentColor);

        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                red = progressValue;
                chosenColor = Color.rgb(red, green, blue);
                redValue.setText(String.valueOf(red));
                imageButton.setBackgroundColor(Color.rgb(red, green, blue));
                ((MainActivity)getContext()).setChosenColorInt(chosenColor);

            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }

        });

        SeekBar seekBarGreen = view.findViewById(R.id.greenSlider);
        final TextView greenValue = view.findViewById(R.id.valueGreen);

        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                green = progressValue;
                chosenColor = Color.rgb(red, green, blue);
                greenValue.setText(String.valueOf(green));
                imageButton.setBackgroundColor(Color.rgb(red, green, blue));
                ((MainActivity)getContext()).setChosenColorInt(chosenColor);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }

        });

        SeekBar seekBarBlue = view.findViewById(R.id.blueSlider);
        final TextView blueValue = view.findViewById(R.id.valueBlue);

        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                blue = progressValue;
                chosenColor = Color.rgb(red, green, blue);
                blueValue.setText(String.valueOf(blue));
                imageButton.setBackgroundColor(Color.rgb(red, green, blue));
                ((MainActivity)getContext()).setChosenColorInt(chosenColor);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }

        });

        return view;
    }
}
