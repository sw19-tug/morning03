package sw19.moring03.paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.tools.EraseTool;
import sw19.moring03.paint.R;
import sw19.moring03.paint.tools.CircleTool;
import sw19.moring03.paint.tools.LineTool;
import sw19.moring03.paint.tools.OvalTool;
import sw19.moring03.paint.tools.PointTool;
import sw19.moring03.paint.tools.RectangleTool;
import sw19.moring03.paint.tools.Tools;
import sw19.moring03.paint.utils.Color;
import sw19.moring03.paint.utils.Tool;
import sw19.moring03.paint.tools.PathTool;

public class DrawingView extends View {
    private Paint paint;
    private List<Tools> objectsToPaint;

    public DrawingView(Context c, AttributeSet attributeSet) {
        super(c, attributeSet);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.black));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(((MainActivity)getContext()).getStrokeWidth());
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

        if (objectsToPaint != null) {
            for (Tools tool : objectsToPaint) {
                paint.setStrokeWidth(tool.getStrokeWidth());
                paint.setColor(tool.getColor());
                tool.draw(canvas, paint);
            }
        }
    }


    private void addPoint(float x, float y) {
        PointF point = new PointF(x, y);

        if (objectsToPaint.size() != 0) {
            objectsToPaint.get(objectsToPaint.size() - 1).addPoint(point);
        }
    }

    private void selectTool() {
        Tool chosenTool = ((MainActivity) getContext()).getChosenTool();

        int strokeWidth = ((MainActivity)getContext()).getStrokeWidth();

        switch (chosenTool) {
            case FILL:
                break;
            case ERASER:
                objectsToPaint.add(new EraseTool(strokeWidth));
                break;
            case DRAW_LINE:
                objectsToPaint.add(new LineTool(getColor(), strokeWidth));
                break;
            case DRAW_PATH:
                objectsToPaint.add(new PathTool(getColor(), strokeWidth));
                break;
            case DRAW_POINT:
                objectsToPaint.add(new PointTool(getColor(), strokeWidth));
                break;
            case DRAW_CIRCLE:
                objectsToPaint.add(new CircleTool(getColor(), strokeWidth));
                break;
            case DRAW_RECTANGLE:
                objectsToPaint.add(new RectangleTool(getColor(), strokeWidth));
                break;
            case DRAW_OVAL:
                objectsToPaint.add(new OvalTool(getColor(), strokeWidth));
                break;
        }
    }

    private int getColor() {
        Color chosenColor = ((MainActivity) getContext()).getChosenColor();
        int color = 0;
        switch (chosenColor) {
            case WHITE:
                color = getResources().getColor(R.color.white);
                break;
            case YELLOW:
                color = getResources().getColor(R.color.yellow);
                break;
            case ORANGE:
                color = getResources().getColor(R.color.orange);
                break;
            case RED:
                color = getResources().getColor(R.color.red);
                break;
            case PURPLE:
                color = getResources().getColor(R.color.purple);
                break;
            case MAGENTA:
                color = getResources().getColor(R.color.magenta);
                break;
            case TURKIS:
                color = getResources().getColor(R.color.turkis);
                break;
            case LIGHT_GREEN:
                color = getResources().getColor(R.color.lightGreen);
                break;
            case DARK_GREEN:
                color = getResources().getColor(R.color.darkGreen);
                break;
            case LIGHT_BLUE:
                color = getResources().getColor(R.color.lightBlue);
                break;
            case DARK_BLUE:
                color = getResources().getColor(R.color.darkBlue);
                break;
            default:
                color = getResources().getColor(R.color.black);
                break;
        }
        return color;
    }
}