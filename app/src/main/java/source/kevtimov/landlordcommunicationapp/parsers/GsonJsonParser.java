package source.kevtimov.landlordcommunicationapp.parsers;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import source.kevtimov.landlordcommunicationapp.parsers.base.JsonParser;

public class GsonJsonParser<T> implements JsonParser<T> {
    private final Class<T> mKlass;
    private final Class<T[]> mArrayKlass;
    private final Gson mGson;


    public GsonJsonParser(Class<T> klass, Class<T[]> arrayKlass) {
        mKlass = klass;
        mArrayKlass = arrayKlass;
        mGson = new Gson();
    }

    @Override
    public List<T> fromJsonArray(String jsonString) {
        T[] result = mGson.fromJson(jsonString,mArrayKlass);
        List<T> resultToList = Arrays.asList(result);
        return resultToList;
    }

    @Override
    public T fromJson(String jsonString) {
        return mGson.fromJson(jsonString,mKlass);
    }

    @Override
    public String toJson(T object) {
        return mGson.toJson(object);
    }
}
