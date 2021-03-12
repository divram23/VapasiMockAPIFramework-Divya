package MockClasses;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class Mock1 {

    public static void main(String[] args) {


        WireMockRule wireMockRule = new WireMockRule(8090);


        stubFor(get(urlEqualTo("/an/endpoint")).willReturn(aResponse()
                .withHeader("Content-Type", "text/plain").withBody("Hello world!!").withStatus(200)));

    }
}

    /*stubFor(get(urlEqualTo("/an/endpoint"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBody("You've reached a valid WireMock endpoint")))





   @Test
    public void testStatusCodePositive() {



       Response response = (Response) RestAssured.
        given().
                when().
                get("http://localhost:8090/an/endpoint").
                then().
                assertThat().statusCode(200);

         get("http://localhost:8090/an/endpoint").


        wireMockRule.start();
        setupStub();
        wireMockRule.get("http://localhost:8090/an/endpoint").statusCode();

        wireMockRule




        System.out.println(response);
    }
*/
