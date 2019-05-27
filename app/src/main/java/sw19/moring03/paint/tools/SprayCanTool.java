package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class SprayCanTool extends Tools {
    public SprayCanTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
    }

    public SprayCanTool(int col, int strkW) {
        points = new ArrayList<>();
        strokeWidth = strkW;
        color = col;
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

    public int getPointCount() {
        return points.size();
    }

}
