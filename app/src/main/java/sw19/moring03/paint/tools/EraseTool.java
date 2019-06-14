package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;

import sw19.moring03.paint.utils.PointF;

public class EraseTool extends Tools {
    @VisibleForTesting
    public Path path;

    public EraseTool(int strkW) {
        points = new ArrayList<>();
        color = Color.BLACK;
        strokeWidth = strkW;
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

        int backUpColor = paint.getColor();

        final int backgroundColor = Color.WHITE;

        paint.setColor(backgroundColor);
        canvas.drawPath(path, paint);
        paint.setColor(backUpColor);
        return true;
    }
}
