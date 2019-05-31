package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import sw19.moring03.paint.utils.PointF;

import java.util.List;

public abstract class Tools {

    protected List<PointF> points;
    protected int color;
    protected int strokeWidth;
    protected PathEffect pathEffect;

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
    public int getColor() {
        return color;
    }

    public PathEffect getPathEffect() {
        return pathEffect;
    }

}
