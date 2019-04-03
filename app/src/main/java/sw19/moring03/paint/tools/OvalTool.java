package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.RequiresApi;

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

        float x_start = points.get(0).x;
        float y_start = points.get(0).y;

        float x_end = points.get(points.size() - 1).x;
        float y_end = points.get(points.size() - 1).y;

        canvas.drawOval(x_start, y_start, x_end, y_end, paint);

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
