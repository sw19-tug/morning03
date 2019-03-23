package sw19.moring03.paint.Views;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import sw19.moring03.paint.tools.Tools;
import sw19.moring03.paint.tools.LineTool;

public class DrawingView extends View {
    private Paint mPaint;
    private List<Tools> objectsToPaint;


    public DrawingView(Context c, AttributeSet attributeSet) {
        super(c, attributeSet);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4.0f);

        objectsToPaint = new ArrayList<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xCoord = event.getX();
        float yCoord = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
            case MotionEvent.ACTION_DOWN:
                selectTool();
                addPoint(xCoord, yCoord);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                addPoint(xCoord, yCoord);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(objectsToPaint != null) {
            for(Tools tool : objectsToPaint) {

                if(tool instanceof LineTool) {
                    tool.draw(canvas, mPaint);
                }
            }
        }
    }

    private void addPoint(float x, float y) {
        PointF point = new PointF(x, y);
        objectsToPaint.get(objectsToPaint.size()-1).addPoint(point);
    }

    private void selectTool() {
        objectsToPaint.add(new LineTool());
    }
}