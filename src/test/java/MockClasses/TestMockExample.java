package MockClasses;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.json.simple.JSONObject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class TestMockExample {

    //private static WireMockServer wireMockServer;

    //WireMock.configureFor("my.remote.host", 8000);

    public static WireMock wireMock = new WireMock("http://localhost:", 8000);
    /*@Rule
    public static WireMockRule wireMockRule = new WireMockRule(8089);
*/

    public static void main(String[] args) {


        JSONObject bodyResponseParams = new JSONObject();
        bodyResponseParams.put("book_name", "Agile1");
        bodyResponseParams.put("isbn", "12221");
        bodyResponseParams.put("aisle", "12AA4");
        bodyResponseParams.put("author", "Rowling123");
        String id = "1222112AA4";

        wireMock.stubFor(get(urlEqualTo("/Library/GetBook.php"))
                .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                        .withBody(bodyResponseParams.toJSONString())));


        /*stubFor(get(urlEqualTo("/Library/GetBook.php")).withQueryParam("ID", id)
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>Some content</response>")));
    */


    /*Result result = myHttpServiceCallingObject.doSomething();

    assertTrue(result.wasSuccessful());

    verify(postRequestedFor(urlMatching("/my/resource/[a-z0-9]+"))
            .withRequestBody(matching(".*<message>1234</message>.*"))
            .withHeader("Content-Type", notMatching("application/json")));*/
    }
}
