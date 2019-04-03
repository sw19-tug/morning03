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
    private Paint mPaint;
    private List<Tools> objectsToPaint;

    public DrawingView(Context c, AttributeSet attributeSet) {
        super(c, attributeSet);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(((MainActivity)getContext()).getStrokeWidth());
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
                selectColor();
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
                mPaint.setStrokeWidth(tool.getStrokeWidth());
                tool.draw(canvas, mPaint);
            }
        }
    }


    private void addPoint(float x, float y) {
        PointF point = new PointF(x, y);

        if(objectsToPaint.size() != 0) {
            objectsToPaint.get(objectsToPaint.size()-1).addPoint(point);
        }
    }

    private void selectTool() {
        Tool chosenTool = ((MainActivity) getContext()).getChosenTool();


        int strokeWidth = ((MainActivity)getContext()).getStrokeWidth();

        switch (chosenTool) {
            case FILL:
                break;
            case ERASER:
                objectsToPaint.add(new EraseTool());
                break;
            case DRAW_LINE:
                objectsToPaint.add(new LineTool(strokeWidth));
                break;
            case DRAW_PATH:
                objectsToPaint.add(new PathTool(strokeWidth));
                break;
            case DRAW_POINT:
                objectsToPaint.add(new PointTool(strokeWidth));
                break;
            case DRAW_CIRCLE:
                objectsToPaint.add(new CircleTool());
                break;
            case DRAW_RECTANGLE:
                objectsToPaint.add(new RectangleTool());
                break;
            case DRAW_OVAL:
                objectsToPaint.add(new OvalTool());
                break;
        }
    }

    private void selectColor() {
        Color chosenColor = ((MainActivity) getContext()).getChosenColor();

        switch (chosenColor) {
            case WHITE:
                mPaint.setColor(getResources().getColor(R.color.white));
                break;
            case YELLOW:
                mPaint.setColor(getResources().getColor(R.color.yellow));
                break;
            case ORANGE:
                mPaint.setColor(getResources().getColor(R.color.orange));
                break;
            case RED:
                mPaint.setColor(getResources().getColor(R.color.red));
                break;
            case PURPLE:
                mPaint.setColor(getResources().getColor(R.color.purple));
                break;
            case MAGENTA:
                mPaint.setColor(getResources().getColor(R.color.magenta));
                break;
            case TURKIS:
                mPaint.setColor(getResources().getColor(R.color.turkis));
                break;
            case LIGHT_GREEN:
                mPaint.setColor(getResources().getColor(R.color.lightGreen));
                break;
            case DARK_GREEN:
                mPaint.setColor(getResources().getColor(R.color.darkGreen));
                break;
            case LIGHT_BLUE:
                mPaint.setColor(getResources().getColor(R.color.lightBlue));
                break;
            case DARK_BLUE:
                mPaint.setColor(getResources().getColor(R.color.darkBlue));
                break;
            case BLACK:
                mPaint.setColor(getResources().getColor(R.color.black));
                break;
        }
    }
}