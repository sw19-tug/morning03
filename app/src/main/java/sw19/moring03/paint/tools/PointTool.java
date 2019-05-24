package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;

import java.util.ArrayList;

public class PointTool extends Tools {

    public PointTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
        pathEffect = new PathEffect();
    }

    public PointTool(int col, int strkW) {
        points = new ArrayList<>();
        strokeWidth = strkW;
        color = col;
    }

    public int getPointCount() {
        return points.size();
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (points != null && points.size() < 1) {
            return false;
        }

        for (int i = 0; i < points.size(); i++) {
            float xCoord = points.get(i).x;
            float yCoord = points.get(i).y;
            canvas.drawCircle(xCoord, yCoord, strokeWidth / 2.0f, paint);
        }
        return true;
    }

    public void addPoint(PointF point) {
        if (getPointCount() > 0) {
            return;
        }

        super.addPoint(point);
    }
}
