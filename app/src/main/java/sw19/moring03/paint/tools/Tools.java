package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.List;

public abstract class Tools {

    protected List<PointF> points;

    static protected int backgroundColor = Color.WHITE;

    public abstract boolean draw(Canvas canvas, Paint paint);

    public void addPoint(PointF point) {
        points.add(point);
    }

    public List<PointF> getPoints() {
        return points;
    }
}
