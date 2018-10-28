package source.kevtimov.landlordcommunicationapp.http;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHttpRequester implements HttpRequester {

    @Override
    public String get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String body = response.body().string();

        return body;
    }

    @Override
    public String post(String url, String bodyString) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"),
                bodyString
        );

        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        Response response = client.newCall(request)
                .execute();

        return response.body().string();
    }

    @Override
    public String delete(String url, int id) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .delete()
                .url(url)
                .build();

        Response response = client.newCall(request)
                .execute();

        return response.body().string();
    }

    @Override
    public String update(String url, String body, int id) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody bodyString = RequestBody.create(
                MediaType.parse("application/json"),
                body
        );

        Request request = new Request.Builder()
                .put(bodyString)
                .url(url)
                .build();

        Response response = client.newCall(request)
                .execute();

        return response.body().string();
    }
}
