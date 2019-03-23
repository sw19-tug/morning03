package sw19.moring03.paint.tools;

import android.graphics.PointF;
import android.graphics.Paint;
import android.graphics.Canvas;

import java.util.List;

public abstract class Tools {

    protected List<PointF> points;

    public abstract boolean draw(Canvas canvas, Paint paint);

    public void addPoint(PointF point)
    {
        points.add(point);
    }

    public void removePoint(PointF point)
    {
        points.remove(point);
    }

    public List<PointF> getPoints()
    {
        return points;
    }
}