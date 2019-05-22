package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;

import java.util.ArrayList;

public class PathTool extends Tools {

    public PathTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
    }

    public PathTool(int col, int strkW, PathEffect effect) {
        points = new ArrayList<>();
        strokeWidth = strkW;
        color = col;
        pathEffect = effect;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {

        if (points == null || points.size() < 2) {
            return false;
        }

        paint.setPathEffect(pathEffect);

        for (int i = 1; i < points.size(); i++) {
            canvas.drawLine(points.get(i - 1).x, points.get(i - 1).y, points.get(i).x, points.get(i).y, paint);
        }

        return true;
    }
}
