package MockTestcases;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.http.HttpResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class StubMethods {




    public String httpResponse(String testURL) throws IOException {
        String HttpUrl = "http://localhost:8089" + testURL;
        /*try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpGet get = new HttpGet(url);
            HttpEntity entity = client.execute(get).getEntity();
            return  EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Unable to call " + url, e);
        }*/

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(HttpUrl);
        HttpResponse response = null;
        try {
            response = (HttpResponse) httpClient.execute(request).getEntity();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return EntityUtils.toString((HttpEntity) response, "UTF-8");

        /*//HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/some/thing");
        HttpResponse response = client.execute(request)*/

    }



    public void stubAddBookPOSTMethod(){

        JSONObject addBookResponseParams = new JSONObject();
        addBookResponseParams.put("name", "WireMock Book1");
        addBookResponseParams.put("isbn", "112211");
        addBookResponseParams.put("aisle", "22");
        addBookResponseParams.put("author", "Flower");


        stubFor(post(urlEqualTo("/Library/Addbook.php"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(addBookResponseParams.toJSONString()).withStatus(200)));


    }

    public void stubGetBookByIDGETMethod(String ID){

        JSONObject getBookResponseParams = new JSONObject();
        getBookResponseParams.put("name", "WireMock Book2");
        getBookResponseParams.put("isbn", "112212");
        getBookResponseParams.put("aisle", "12");
        getBookResponseParams.put("author", "Flower1");

        stubFor(get(urlEqualTo("/Library/GetBook.php?ID="+ID))//.withQueryParam("ID", ID)
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(getBookResponseParams.toJSONString()).withStatus(200)));

    }

    public void stubGetBookByAuthorNameGETMethod(String authorName){

        JSONObject getBookAuthorResponseParams = new JSONObject();
        getBookAuthorResponseParams.put("book_name", "WireMock Book3");
        getBookAuthorResponseParams.put("isbn", "110011");
        getBookAuthorResponseParams.put("aisle", "101");

        //StringValuePattern author = authorName;

        stubFor(get(urlEqualTo("/Library/GetBook.php?AuthorName="+authorName))//.withQueryParam("AuthorName", authorName)
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(getBookAuthorResponseParams.toJSONString()).withStatus(200)));


    }


    public void stubDeleteBookPOSTMethod(){

        stubFor(post(urlEqualTo("/Library/DeleteBook.php"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withBody("Delete operation working via Mocking!!!").withStatus(200)));

    }

}
