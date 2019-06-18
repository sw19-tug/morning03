package sw19.moring03.paint;

import android.content.ContentResolver;
import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import sw19.moring03.paint.utils.ImageSaver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ImageSaverTest {
    private ImageSaver imageSaver;
    private ContentResolver contentResolver;

    @Before
    public void startUp() {
        contentResolver = Mockito.mock(ContentResolver.class);
        imageSaver = new ImageSaver(contentResolver);
    }

    @Test
    public void testSaveInvalidImage() {
        assertFalse(imageSaver.saveImage(null));
        assertEquals("", imageSaver.getSavedImageURI());
    }

    @Test
    public void testSaveSimpleImage() {
        Bitmap bitmap = Mockito.mock(Bitmap.class);
        assertTrue(imageSaver.saveImage(bitmap));
    }
}
