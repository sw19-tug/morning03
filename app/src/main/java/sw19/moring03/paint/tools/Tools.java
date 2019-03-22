package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.PointF;

import java.util.List;

public abstract class Tools {
    public Canvas canvas = new Canvas();

    public abstract boolean draw(List<PointF> points);
}
