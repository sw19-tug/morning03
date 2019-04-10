package sw19.moring03.paint.util;

import android.support.test.espresso.action.CoordinatesProvider;
import android.view.View;

public class CoordProvider implements CoordinatesProvider {
    float xCoord;
    float yCoord;

    public CoordProvider(float xCoord, float yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    @Override
    public float[] calculateCoordinates(View view) {
        return new float[] {xCoord, yCoord};
    }

}
