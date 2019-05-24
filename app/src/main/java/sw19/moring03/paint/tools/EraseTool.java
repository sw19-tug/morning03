package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PathEffect;

import java.util.ArrayList;

public class EraseTool extends Tools {

    public EraseTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
        pathEffect = new PathEffect();
    }

    public EraseTool(int strkW) {
        points = new ArrayList<>();
        color = Color.BLACK;
        strokeWidth = strkW;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {

        if (points == null || points.size() < 2) {
            return false;
        }

        int backUpColor = paint.getColor();

        final int backgroundColor = Color.WHITE;

        paint.setColor(backgroundColor);
        for (int i = 1; i < points.size(); i++) {
            canvas.drawLine(points.get(i - 1).x, points.get(i - 1).y, points.get(i).x, points.get(i).y, paint);
        }
        paint.setColor(backUpColor);
        return true;
    }
}
