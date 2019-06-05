package sw19.moring03.paint.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;

import sw19.moring03.paint.tools.ChristmasTreeTool;
import sw19.moring03.paint.tools.HeartTool;
import sw19.moring03.paint.tools.StarTool;
import sw19.moring03.paint.tools.TriangleTool;
import sw19.moring03.paint.utils.PointF;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sw19.moring03.paint.Fragments.TextInsertFragment;
import sw19.moring03.paint.MainActivity;
import sw19.moring03.paint.R;
import sw19.moring03.paint.tools.CircleTool;
import sw19.moring03.paint.tools.EraseTool;
import sw19.moring03.paint.tools.FillTool;
import sw19.moring03.paint.tools.LineTool;
import sw19.moring03.paint.tools.OvalTool;
import sw19.moring03.paint.tools.PathTool;
import sw19.moring03.paint.tools.PhotoTool;
import sw19.moring03.paint.tools.PointTool;
import sw19.moring03.paint.tools.RectangleTool;
import sw19.moring03.paint.tools.SprayCanTool;
import sw19.moring03.paint.tools.TextTool;
import sw19.moring03.paint.tools.Tools;
import sw19.moring03.paint.utils.Tool;

public class DrawingView extends View {
    private Paint paint;
    private List<Tools> objectsToPaint;

    public DrawingView(Context c, AttributeSet attributeSet) {
        super(c, attributeSet);
        paint = new Paint();
        paint.setAntiAlias(false);
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
                if (objectsToPaint.size() == 1) {
                    ((MainActivity) getContext()).invalidateOptionsMenu();
                }
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

                paint.setPathEffect(tool.getPathEffect());
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

    public void selectTool() {
        Tool chosenTool = ((MainActivity) getContext()).getChosenTool();
        int strokeWidth = ((MainActivity)getContext()).getStrokeWidth();
        PathEffect effect = ((MainActivity)getContext()).getPathEffect();

        switch (chosenTool) {
            case FILL:
                Bitmap bitmap = getCurrentBitmap();
                int[] frameBuffer = new int[bitmap.getWidth() * bitmap.getHeight()];
                bitmap.getPixels(frameBuffer, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
                objectsToPaint.add(new FillTool(getColor(), frameBuffer, bitmap.getWidth(), bitmap.getHeight()));
                break;
            case ERASER:
                objectsToPaint.add(new EraseTool(strokeWidth));
                break;
            case DRAW_LINE:
                objectsToPaint.add(new LineTool(getColor(), strokeWidth, effect));
                break;
            case DRAW_PATH:
                objectsToPaint.add(new PathTool(getColor(), strokeWidth, effect));
                break;
            case DRAW_POINT:
                objectsToPaint.add(new PointTool(getColor(), strokeWidth));
                break;
            case SPRAY_CAN:
                objectsToPaint.add(new SprayCanTool(getColor(), strokeWidth));
                break;
            case DRAW_CIRCLE:
                objectsToPaint.add(new CircleTool(getColor(), strokeWidth, effect));
                break;
            case DRAW_RECTANGLE:
                objectsToPaint.add(new RectangleTool(getColor(), strokeWidth, effect));
                break;
            case DRAW_OVAL:
                objectsToPaint.add(new OvalTool(getColor(), strokeWidth, effect));
                break;
            case TAKE_PHOTO:
                objectsToPaint.add(new PhotoTool(((MainActivity) getContext()).newPhoto));
                break;
            case DRAW_TEXT:
                TextInsertFragment textFragment = new TextInsertFragment();
                textFragment.show(((MainActivity) getContext()).getSupportFragmentManager(), "textInsertFragment");

                objectsToPaint.add(new TextTool(getColor(), strokeWidth, getContext()));
                break;
            case DRAW_TRIANGLE:
                objectsToPaint.add(new TriangleTool(getColor(), strokeWidth));
                break;
            case DRAW_HEART:
                objectsToPaint.add(new HeartTool(getColor(), strokeWidth));
                break;
            case DRAW_STAR:
                objectsToPaint.add(new StarTool(getColor(), strokeWidth));
                break;
            case DRAW_TREE:
                Bitmap tree = BitmapFactory.decodeResource(getResources(), R.drawable.christmas);
                objectsToPaint.add(new ChristmasTreeTool(tree));
                break;
        }
    }

    public void addTextToTool(String text) {
        ((TextTool)objectsToPaint.get(objectsToPaint.size() - 1)).setText(text);
    }

    public void addFontToTool(String font) {
        ((TextTool)objectsToPaint.get(objectsToPaint.size() - 1)).setFont(font);
    }

    public Bitmap getCurrentBitmap() {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);
        return bitmap;
    }

    @VisibleForTesting
    public int getColor() {
        return ((MainActivity) getContext()).getChosenColor();
    }

    @VisibleForTesting
    public List<Tools> getObjectsToPaint() {
        return objectsToPaint;
    }

    public void removeLastElementFromPaintList() {
        if (objectsToPaint != null && !objectsToPaint.isEmpty()) {
            objectsToPaint.remove(objectsToPaint.size() - 1);
            invalidate();

            if (objectsToPaint.isEmpty()) {
                ((MainActivity)getContext()).invalidateOptionsMenu();
            }
        }
    }

    public boolean isAlreadyDrawn() {
        return !objectsToPaint.isEmpty();
    }

}
