package sw19.moring03.paint.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

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
import sw19.moring03.paint.tools.StickerTool;
import sw19.moring03.paint.tools.TextTool;
import sw19.moring03.paint.tools.Tools;
import sw19.moring03.paint.utils.DrawingObjectManager;
import sw19.moring03.paint.utils.PointF;
import sw19.moring03.paint.utils.Tool;

public class DrawingView extends View {

    private Paint paint;
    public DrawingObjectManager drawingObjectManager;

    public DrawingView(Context c, AttributeSet attributeSet) {
        super(c, attributeSet);
        paint = new Paint();
        paint.setAntiAlias(false);
        paint.setColor(getResources().getColor(R.color.black));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(((MainActivity)getContext()).getStrokeWidth());
        drawingObjectManager = new DrawingObjectManager();
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
                if (drawingObjectManager.getObjectsToPaint().size() == 1) {
                    ((MainActivity) getContext()).invalidateOptionsMenu();
                }
                invalidate();
                performClick();
                break;
            case MotionEvent.ACTION_MOVE:
                addPoint(xCoord, yCoord);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Tools> objectsToPaint = drawingObjectManager.getObjectsToPaint();
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
        drawingObjectManager.addPoint(point);
    }

    public void selectTool() {
        MainActivity activity = (MainActivity)getContext();
        Tool chosenTool = activity.getChosenTool();
        int strokeWidth = activity.getStrokeWidth();
        PathEffect effect = activity.getPathEffect();

        switch (chosenTool) {
            case FILL:
                Bitmap bitmap = getCurrentBitmap();
                int[] frameBuffer = new int[bitmap.getWidth() * bitmap.getHeight()];
                bitmap.getPixels(frameBuffer, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
                drawingObjectManager.addTool(new FillTool(getColor(), frameBuffer, bitmap.getWidth(), bitmap.getHeight()));
                break;
            case DRAW_STICKER:
                drawingObjectManager.addTool(new StickerTool(BitmapFactory.decodeResource(activity.getResources(), activity.getStickerToDraw())));
                break;
            case ERASER:
                drawingObjectManager.addTool(new EraseTool(strokeWidth));
                break;
            case DRAW_LINE:
                drawingObjectManager.addTool(new LineTool(getColor(), strokeWidth, effect));
                break;
            case DRAW_PATH:
                drawingObjectManager.addTool(new PathTool(getColor(), strokeWidth, effect));
                break;
            case DRAW_POINT:
                drawingObjectManager.addTool(new PointTool(getColor(), strokeWidth));
                break;
            case SPRAY_CAN:
                drawingObjectManager.addTool(new SprayCanTool(getColor(), strokeWidth));
                break;
            case DRAW_CIRCLE:
                drawingObjectManager.addTool(new CircleTool(getColor(), strokeWidth, effect));
                break;
            case DRAW_RECTANGLE:
                drawingObjectManager.addTool(new RectangleTool(getColor(), strokeWidth, effect));
                break;
            case DRAW_OVAL:
                drawingObjectManager.addTool(new OvalTool(getColor(), strokeWidth, effect));
                break;
            case TAKE_PHOTO:
                drawingObjectManager.addTool(new PhotoTool(((MainActivity) getContext()).newPhoto));
                break;
            case DRAW_TEXT:
                TextInsertFragment textFragment = new TextInsertFragment();
                textFragment.show(((MainActivity) getContext()).getSupportFragmentManager(), "textInsertFragment");

                drawingObjectManager.addTool(new TextTool(getColor(), strokeWidth, getContext()));
                break;
        }
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

    public void redoLastPaintObject() {
        drawingObjectManager.addLastRemovedElementToPaintList();
        invalidate();

        if (!drawingObjectManager.isRedoPossible()) {
            ((MainActivity)getContext()).invalidateOptionsMenu();
        }
    }

    public void undoLastPaintObject() {
        drawingObjectManager.removeLastElementFromPaintList();
        invalidate();

        if (!drawingObjectManager.isUndoPossible()) {
            ((MainActivity)getContext()).invalidateOptionsMenu();
        }
    }
}
