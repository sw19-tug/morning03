package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import sw19.moring03.paint.utils.PointF;

import java.util.ArrayList;

public class OvalTool extends Tools {
    public OvalTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
    }

    public OvalTool(int col, int strkW) {
        points = new ArrayList<>();
        strokeWidth = strkW;
        color = col;
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
