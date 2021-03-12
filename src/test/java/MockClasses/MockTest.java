/*
package MockClasses;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.json.simple.JSONObject;
import org.junit.Rule;
import org.testng.Assert;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    public static void main(String[] args) {

        JSONObject bodyResponseParams = new JSONObject();
        bodyResponseParams.put("book_name", "Agile1");
        bodyResponseParams.put("isbn", "12221");
        bodyResponseParams.put("aisle", "12AA4");
        bodyResponseParams.put("author", "Rowling123");


        */
/*stubFor(get(urlEqualTo("/Library/GetBook.php")).withQueryParam("ID", ID)
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyResponseParams.toJSONString())));*//*


        givenThat(get(urlEqualTo("/api/message")).willReturn(ok()));

        String serverUrl = buildApiMethodUrl();
        ResponseEntity<String> response = restTemplate.getForEntity(serverUrl,
                String.class
        );
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK );
       // Assert.assertEquals((response.getStatusCode()).isEqualTo(HttpStatus.OK));
    }

    String buildApiMethodUrl() {
        return String.format("http://localhost:%d/api/message",
                this.wireMockServer.port()
        );
    }

    }
}
*/
