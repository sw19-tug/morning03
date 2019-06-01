package sw19.moring03.paint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import sw19.moring03.paint.tools.LineTool;
import sw19.moring03.paint.tools.PathTool;
import sw19.moring03.paint.tools.PointTool;
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
        List<Tools> expectedToolList = Arrays.asList(new PointTool(), new LineTool(), new PathTool());
        for (Tools tool : expectedToolList) {
            drawingObjectManager.addTool(tool);
        }
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
        drawingObjectManager.addTool(new PointTool());
        drawingObjectManager.addPoint(expectedPoint);
        PointTool pointTool = (PointTool) drawingObjectManager.getObjectsToPaint().get(0);
        assertEquals(expectedPoint, pointTool.getPoints().get(0));
    }
}
