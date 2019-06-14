package sw19.moring03.paint.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class ImageShare {

    public Boolean shareImage(Context context, File file, Uri uri) {
        if (file != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.setType("image/png");
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            context.startActivity(Intent.createChooser(shareIntent, "Choose an app"));
            return true;
        } else {
            return false;
        }
    }
}
