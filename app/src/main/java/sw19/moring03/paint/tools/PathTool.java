package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;
import sw19.moring03.paint.utils.PointF;
import android.graphics.Path;

import java.util.ArrayList;

public class PathTool extends Tools {
    private Path path;

    public PathTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
        pathEffect = new PathEffect();
        path = new Path();
    }

    public PathTool(int col, int strkW, PathEffect effect) {
        points = new ArrayList<>();
        strokeWidth = strkW;
        color = col;
        pathEffect = effect;
        path = new Path();
    }

    @Override
    public void addPoint(PointF point) {
        if (path.isEmpty()) {
            path.moveTo(point.x, point.y);
        } else {
            path.lineTo(point.x, point.y);
        }
        super.addPoint(point);
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {

        if (points == null || points.size() < 2) {
            return false;
        }

        paint.setPathEffect(pathEffect);
        canvas.drawPath(path, paint);

        return true;
    }
}
