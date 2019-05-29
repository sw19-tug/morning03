package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

public class SprayCanTool extends Tools {

    private Random random;
    private static final int RADIUS_SCALE = 5;
    private int distance;

    public SprayCanTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
        distance = strokeWidth * RADIUS_SCALE;
    }

    public SprayCanTool(int col, int strkW) {
        points = new ArrayList<>();
        strokeWidth = strkW;
        color = col;
        distance = strokeWidth * RADIUS_SCALE;
    }
    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        random = new Random(277713);
        if (points != null && points.size() < 1) {
            return false;
        }

        for (int i = 0; i < points.size(); i++) {
            for (int k = 0; k < RADIUS_SCALE; k++) {
                PointF randomizedPoint = getRandomizedPoint(points.get(i));
                canvas.drawCircle(randomizedPoint.x, randomizedPoint.y, strokeWidth / 2.0f, paint);
            }
        }
        return true;
    }

    private Boolean isInCircle(PointF middle, int x, int y) {

        float distance = (float)Math.sqrt(Math.pow(middle.x - x, 2) + Math.pow(middle.y - y, 2));
        return distance <= this.distance;

    }

    private PointF getRandomizedPoint(PointF middle) {
        int x;
        int y;
        do {
            x = random.nextInt(2 * distance) - distance + (int)middle.x;
            y = random.nextInt(2 * distance) - distance + (int)middle.y;
        } while (!isInCircle(middle, x, y));

        return new PointF(x, y);
    }
}
