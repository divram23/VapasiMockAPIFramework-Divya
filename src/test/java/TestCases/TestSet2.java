package TestCases;

import ResponseClasses.DeleteBookPostResponse;
import ResponseClasses.GetBookByIDResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utils.ListenerTest.class)

public class TestSet2 {


    @Test (priority = 0)
    public void verifyGetBookByIDGETMethodWithDeserializationResponse() {
        RequestSpecification request = RestAssured.given().queryParam("ID", "12213122");

        Response response1 = request.get("/Library/GetBook.php");

        GetBookByIDResponse[] getBookResponses = response1.as(GetBookByIDResponse[].class);

        String actual = getBookResponses[0].getAuthor();
        System.out.println(actual);

        Assert.assertEquals(response1.getStatusCode(), 200);
        System.out.println("GetBook GET Method Status returned: " + response1.getStatusCode());

    }

    @Test (priority = 1)
    public void verifyDeleteBookPOSTMethodWithDeserializationResponse() {
        RequestSpecification request1 = RestAssured.given();

        JSONObject requestParamsDelete = new JSONObject();
        requestParamsDelete.put("ID", "122");
        request1.body(requestParamsDelete.toJSONString());

        Response response2 = request1.post("//Library/DeleteBook.php");

        DeleteBookPostResponse[] deleteBookPostResponse = response2.as(DeleteBookPostResponse[].class);

        String actualDeleteMsg = deleteBookPostResponse[0].getMsg();

        Assert.assertEquals(actualDeleteMsg, "book is successfully deleted");


    }

    @Test (priority = 2)
    public void verifyDeleteBookPOSTMethod() throws ParseException {
        RestAssured.baseURI = "http://216.10.245.166";
        RequestSpecification request1 = RestAssured.given().header("Content-Type", "application/json");

        JSONObject requestParamsDelete = new JSONObject();
        requestParamsDelete.put("ID", "12213130");
        request1.body(requestParamsDelete.toJSONString());

        Response response2 = request1.post("/Library/DeleteBook.php");

        String responseOutput = response2.asString();

        JSONParser parser = new JSONParser();
        JSONObject deleteParamsResponse = (JSONObject) parser.parse(responseOutput);
        String deleteMessage = deleteParamsResponse.get("msg").toString();
        System.out.println(deleteMessage);
    }

}