package sw19.moring03.paint.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;

public class TextTool extends Tools {
    private String text = "";

    public TextTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (points.size() == 0) {
            return false;
        }

        paint.setTextSize(40);
        canvas.drawText(this.text, points.get(0).x, points.get(0).y, paint);
        return true;
    }

    @Override
    public void addPoint(PointF point) {
        if (points.size() > 0) {
            return;
        }

        super.addPoint(point);
    }

    public void setText(String text) {
        this.text = text;
    }
}
