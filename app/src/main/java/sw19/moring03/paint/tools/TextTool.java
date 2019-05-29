package sw19.moring03.paint.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import sw19.moring03.paint.utils.PointF;
import android.graphics.Typeface;

import java.util.ArrayList;

public class TextTool extends Tools {
    private String text = "";
    private String font = "";
    private int fontSize;
    private float scaledSizeInPixels;
    private Context context;

    public TextTool() {
        points = new ArrayList<>();
        strokeWidth = 5;
        color = Color.BLACK;
    }

    public TextTool(int col, int strkW, Context context) {
        points = new ArrayList<>();
        fontSize = strkW * 3;
        strokeWidth = strkW;
        scaledSizeInPixels = 17 * context.getResources().getDisplayMetrics().scaledDensity;
        color = col;
        this.context = context;
    }

    @Override
    public boolean draw(Canvas canvas, Paint paint) {
        if (points.size() == 0) {
            return false;
        }

        if (!font.equals("")) {
            paint.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/" + font));
        }

        paint.setTextSize(scaledSizeInPixels + fontSize);
        paint.setStyle(Paint.Style.FILL);
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

    public void setFont(String font) {
        this.font = font;
    }
}
