/*
package MockClasses;


import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class StubbingTest {










    @Test
    public void getBookByID() {
        JSONObject bodyResponseParams = new JSONObject();
        bodyResponseParams.put("book_name", "Agile1");
        bodyResponseParams.put("isbn", "12221");
        bodyResponseParams.put("aisle", "12AA4");
        bodyResponseParams.put("author", "Rowling123");


        stubFor(get(urlEqualTo("/Library/GetBook.php")).withQueryParam("ID", ID)
                .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(bodyResponseParams.toJSONString()))).with




                        //Assert.assertEquals(testClient.get("/Library/GetBook.php").withQueryParam("ID", ID)).statusCode, is(200);



        */
/*stubFor(get(urlEqualTo("/some/thing"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody()));

        assertThat(testClient.get("/some/thing").statusCode(), is(200));
        assertThat(testClient.get("/some/thing/else").statusCode(), is(404));*//*

    }

    @Test
    public void postAddBook(){
        stubFor(post(urlEqualTo("/Library/Addbook.php"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody()));
        assertThat(testClient.post("/Library/Addbook.php").statusCode, is(200));

    }
*/
