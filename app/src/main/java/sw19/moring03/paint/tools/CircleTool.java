package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;

import java.util.ArrayList;

public class CircleTool extends Tools {

    private float radius;

    public CircleTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
        radius = 0;
        pathEffect = new PathEffect();
    }

    public CircleTool(int col, int strkW, PathEffect effect) {
        points = new ArrayList<>();
        strokeWidth = strkW;
        color = col;
        radius = 0;
        pathEffect = effect;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (points.size() != 2) {
            return false;
        }

        PointF center = points.get(0);
        PointF outerPoint = points.get(1);

        radius = (float)Math.sqrt(Math.pow((double)Math.abs(center.x - outerPoint.x), (double)2) + Math.pow((double)Math.abs(center.y - outerPoint.y), (double)2));

        paint.setPathEffect(pathEffect);

        canvas.drawCircle(center.x, center.y, radius, paint);

        return true;
    }

    @Override
    public void addPoint(PointF point) {
        if (points.size() == 2) {
            points.remove(1);
        }

        super.addPoint(point);
    }
}
