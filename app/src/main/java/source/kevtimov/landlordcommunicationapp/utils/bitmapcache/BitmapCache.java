package source.kevtimov.landlordcommunicationapp.utils.bitmapcache;

import android.util.LruCache;

public class BitmapCache {

    private static BitmapCache instance;
    private LruCache<Object, Object> lruCache;

    private BitmapCache() {

        lruCache = new LruCache<Object, Object>(1024);

    }

    public static BitmapCache getInstance() {
        if (instance == null) {
            instance = new BitmapCache();
        }

        return instance;
    }

    public LruCache<Object, Object> getLruCache() {
        return lruCache;
    }
}
