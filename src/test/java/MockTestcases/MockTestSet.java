package MockTestcases;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Rule;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(Utils.ListenerTest.class)

public class MockTestSet extends StubMethods {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);



    @Test (priority = 0)
    public void verifyAddBookPOSTMethod() {


        stubAddBookPOSTMethod();

        RestAssured.baseURI = "http://localhost:8080";

        Response response1 = RestAssured.given().post("/Library/Addbook.php");

       // Assert.assertEquals(response1.getStatusCode(), 200);

        JsonPath result = response1.jsonPath();

        String bookName = result.get("name").toString();

        Assert.assertEquals(bookName, "WireMock Book1");
    }


    @Test (priority = 1)
    public void verifyGetBookByIDGETMethod() throws IOException {

        String id = "12345";
        stubGetBookByIDGETMethod(id);

        RestAssured.baseURI = "http://localhost:8080";

        Response response1 = RestAssured.given().queryParam("ID", id).get("/Library/GetBook.php");

        //Assert.assertEquals(response1.getStatusCode(), 200);

        JsonPath result = response1.jsonPath();
        String bookName = result.get("name").toString();

        Assert.assertEquals(bookName, "WireMock Book1");

    }


    @Test (priority = 2)
    public void verifyGetBookByAuthorGETMethod() throws IOException {

        String author = "Flower1";
        stubGetBookByAuthorNameGETMethod(author);

        RestAssured.baseURI = "http://localhost:8080";

        Response response1 = RestAssured.given().queryParam("AuthorName", author).get("/Library/GetBook.php");

        //Assert.assertEquals(response1.getStatusCode(), 200);

        JsonPath result = response1.jsonPath();

        String bookName = result.get("book_name").toString();

        Assert.assertEquals(bookName, "WireMock Book1");
    }


    @Test
    public void verifyDeleteBookPOSTMethod() throws IOException {


        stubDeleteBookPOSTMethod();

        RestAssured.baseURI = "http://localhost:8080";

        Response response1 = RestAssured.given().post("/Library/DeleteBook.php");

        Assert.assertEquals(response1.getStatusCode(), 200);

        String resultString = response1.asString();

        System.out.println(resultString);

    }


}
