package sw19.moring03.paint.util;

import android.support.test.espresso.action.CoordinatesProvider;
import android.view.View;
import android.widget.SeekBar;

public class SeekBarCoordProvider implements CoordinatesProvider {
    private int pathWidth;

    public SeekBarCoordProvider(int pathWidth) {
        this.pathWidth = pathWidth;
    }

    @Override
    public float[] calculateCoordinates(View view) {
        SeekBar seekBar = (SeekBar) view;
        int width = seekBar.getWidth() - seekBar.getPaddingLeft() - seekBar.getPaddingRight();
        double progress = pathWidth == 0 ? seekBar.getProgress() : pathWidth;
        int xPosition = (int) (seekBar.getPaddingLeft() + width * progress / seekBar.getMax());
        int[] xy = new int[2];
        view.getLocationOnScreen(xy);
        return new float[]{ xy[0] + xPosition, xy[1] + 10 };
    }
}
