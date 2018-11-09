package source.kevtimov.landlordcommunicationapp.utils.bitmaprotator;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.IOException;

public interface IBitmapRotator {

    Bitmap rotateImageIfRequired(Bitmap img, Context context, Uri selectedImage) throws IOException;

    Bitmap rotateImage(Bitmap img, int degree);
}
