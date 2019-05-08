package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import sw19.moring03.paint.utils.PointF;

import java.util.List;

public abstract class Tools {

    protected List<PointF> points;
    protected int color;
    protected int strokeWidth = 5;

    static private int backgroundColor = Color.WHITE;

    public abstract boolean draw(Canvas canvas, Paint paint);

    public void addPoint(PointF point) {
        points.add(point);
    }

    public List<PointF> getPoints() {
        return points;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    protected static int getBackgroundColor() {
        return backgroundColor;
    }

    public int getColor() {
        return color;
    }

}
