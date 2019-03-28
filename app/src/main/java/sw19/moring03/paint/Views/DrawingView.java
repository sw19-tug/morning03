package sw19.moring03.paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.tools.LineTool;
import sw19.moring03.paint.tools.PointTool;
import sw19.moring03.paint.tools.Tools;
import sw19.moring03.paint.utils.Tool;
import sw19.moring03.paint.tools.PathTool;


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
                tool.draw(canvas, mPaint);
            }
        }
    }

    private void addPoint(float x, float y) {
        PointF point = new PointF(x, y);

        if(objectsToPaint.size() != 0) {
            Tools currentTool = objectsToPaint.get(objectsToPaint.size()-1);

            if((currentTool instanceof PointTool) && ((PointTool)currentTool).getPointCount() > 0)
            {
                return;
            }

            objectsToPaint.get(objectsToPaint.size()-1).addPoint(point);
        }
    }

    private void selectTool() {
        Tool chosenTool = ((MainActivity) getContext()).getChosenTool();

        switch (chosenTool) {
            case FILL:
                break;
            case ERASER:
                break;
            case DRAW_LINE:
                objectsToPaint.add(new LineTool());
                break;
            case DRAW_PATH:
                objectsToPaint.add(new PathTool());
                break;
            case DRAW_POINT:
                objectsToPaint.add(new PointTool());
                break;
            case DRAW_CIRCLE:
                break;
            case DRAW_RECTANGLE:
                break;
        }
    }
}