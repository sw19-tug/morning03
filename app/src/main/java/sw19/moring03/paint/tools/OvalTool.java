package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;

import java.util.ArrayList;

import sw19.moring03.paint.utils.PointF;

public class OvalTool extends Tools {

    public OvalTool(int col, int strkW, PathEffect effect) {
        points = new ArrayList<>();
        strokeWidth = strkW;
        color = col;
        pathEffect = effect;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (points.size() != 2) {
            return false;
        }

        float xStart = points.get(0).x;
        float yStart = points.get(0).y;

        float xEnd = points.get(points.size() - 1).x;
        float yEnd = points.get(points.size() - 1).y;

        canvas.drawOval(xStart, yStart, xEnd, yEnd, paint);
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
