package sw19.moring03.paint;

import android.graphics.Color;
import android.graphics.PathEffect;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import sw19.moring03.paint.tools.LineTool;
import sw19.moring03.paint.tools.PathTool;
import sw19.moring03.paint.tools.PointTool;
import sw19.moring03.paint.tools.TextTool;
import sw19.moring03.paint.tools.Tools;
import sw19.moring03.paint.utils.DrawingObjectManager;
import sw19.moring03.paint.utils.PointF;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DrawingObjectManagerTest {
    private DrawingObjectManager drawingObjectManager;

    @Before
    public void setUp() {
        drawingObjectManager = new DrawingObjectManager();
    }

    @Test
    public void testObjectsToRedoList() {
        List<Tools> expectedToolList = Arrays.asList(
                new PointTool(Color.BLACK, 10),
                new LineTool(Color.BLACK, 10, new PathEffect()),
                new PathTool(Color.BLACK, 10, new PathEffect()));
        drawingObjectManager.addTool(expectedToolList.get(0));
        drawingObjectManager.addTool(expectedToolList.get(1));
        drawingObjectManager.addTool(expectedToolList.get(2));
        assertEquals(expectedToolList, drawingObjectManager.getObjectsToPaint());

        drawingObjectManager.removeLastElementFromPaintList();
        assertEquals(Arrays.asList(expectedToolList.get(expectedToolList.size() - 1)), drawingObjectManager.getObjectsToRedo());
    }

    @Test
    public void testInvalidUndo() {
        assertFalse(drawingObjectManager.isUndoPossible());
        drawingObjectManager.removeLastElementFromPaintList();
        assertTrue(drawingObjectManager.getObjectsToRedo().isEmpty());
        assertTrue(drawingObjectManager.getObjectsToPaint().isEmpty());
    }

    @Test
    public void testInvalidRedo() {
        assertFalse(drawingObjectManager.isRedoPossible());
        drawingObjectManager.addLastRemovedElementToPaintList();
        assertTrue(drawingObjectManager.getObjectsToRedo().isEmpty());
        assertTrue(drawingObjectManager.getObjectsToPaint().isEmpty());
    }

    @Test
    public void testInvalidAddPointToTool() {
        drawingObjectManager.addPoint(new PointF(0, 0));
        assertEquals(0, drawingObjectManager.getObjectsToPaint().size());
    }

    @Test
    public void testAddPointToTool() {
        PointF expectedPoint = new PointF(10, 10);
        drawingObjectManager.addTool(new PointTool(Color.BLACK, 10));
        drawingObjectManager.addPoint(expectedPoint);
        PointTool pointTool = (PointTool) drawingObjectManager.getObjectsToPaint().get(0);
        assertEquals(expectedPoint, pointTool.getPoints().get(0));
    }

    @Test
    public void testAddTextToTextTool() {
        final String expectedText = "hello";
        TextTool tool = Mockito.mock(TextTool.class);
        drawingObjectManager.addTool(tool);
        drawingObjectManager.addTextToTool("hello");
        Mockito.verify(tool, Mockito.times(1)).setText(expectedText);
    }

    @Test
    public void testAddFontToTextTool() {
        final String expectedFont = "bold";
        TextTool tool = Mockito.mock(TextTool.class);
        drawingObjectManager.addTool(tool);
        drawingObjectManager.addFontToTool("bold");
        Mockito.verify(tool, Mockito.times(1)).setFont(expectedFont);
    }

    @Test
    public void testAddRedoLastUndoneElement() {
        drawingObjectManager.addTool(new PointTool(Color.BLACK, 10));
        drawingObjectManager.addTool(new PointTool(Color.RED, 5));
        drawingObjectManager.removeLastElementFromPaintList();
        assertEquals(1, drawingObjectManager.getObjectsToPaint().size());
        assertEquals(1, drawingObjectManager.getObjectsToRedo().size());

        drawingObjectManager.addLastRemovedElementToPaintList();
        assertEquals(2, drawingObjectManager.getObjectsToPaint().size());
        assertEquals(0, drawingObjectManager.getObjectsToRedo().size());
    }

}
