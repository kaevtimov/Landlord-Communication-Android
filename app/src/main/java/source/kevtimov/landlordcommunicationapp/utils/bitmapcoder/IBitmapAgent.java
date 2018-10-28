package source.kevtimov.landlordcommunicationapp.utils.bitmapcoder;

import android.graphics.Bitmap;

public interface IBitmapAgent {

    String convertBitmapToString(Bitmap bitmap);

    Bitmap convertStringToBitmap(String databaseBitmapString);
}
