package sw19.moring03.paint;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import sw19.moring03.paint.utils.ImageShare;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ImageShareTest {
    private ImageShare imageShare;


    @Before
    public void startUp() {
        imageShare = new ImageShare();
    }

    @Test
    public void testShareInvalidImage() {
        assertFalse(imageShare.shareImage(null, null, null));
    }

    @Test
    public void testSimpleImage() {
        Context context = Mockito.mock(Context.class);
         assertTrue(imageShare.shareImage(context, new File("test.png"), null));
    }

}
