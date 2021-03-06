package TestCases;

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

public class TestSet1 {

    @Test (priority = 0)
    public void verifyPOSTAddBookPositiveScenario(){

        RestAssured.baseURI = "http://216.10.245.166";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Agile1");
        requestParams.put("isbn", "12345");
        requestParams.put("aisle", "123");
        requestParams.put("author", "Rowling123");
        request.body(requestParams.toJSONString());

        Response response12 = request.post("/Library/Addbook.php");

        Assert.assertEquals(response12.getStatusCode(), 200);

    }

    @Test (priority = 1)
    public void verifyPOSTAddBookNegativeScenario() {

        RestAssured.baseURI = "http://216.10.245.166";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Agile1");
        requestParams.put("isbn", "12345");
        requestParams.put("aisle", "123");
        requestParams.put("author", "Rowling123");
        request.body(requestParams.toJSONString());

        Response response22 = request.post("/Library/Addbook.php");

        int statusCode = response22.getStatusCode();
        System.out.println("POST - AddBook Response Status: " + statusCode);
        Assert.assertEquals(response22.getStatusCode(), 404);

    }


    @Test (priority = 2)
    public void verifyAddBookGetBookDeleteBookAddBookFlow() throws  ParseException {

        RestAssured.baseURI = "http://216.10.245.166";
        RequestSpecification request = RestAssured.given();

        JSONObject addBookRequest = new JSONObject();
        addBookRequest.put("name", "Agile1");
        addBookRequest.put("isbn", "1224");
        addBookRequest.put("aisle", "1222");
        addBookRequest.put("author", "Rowling123");
        request.body(addBookRequest.toJSONString());

        Response response1 = request.post("/Library/Addbook.php");

        String responseAddBook = response1.asString();
        System.out.println(responseAddBook);

        JSONParser parser = new JSONParser();
        JSONObject responseAddBookParams = (JSONObject) parser.parse(responseAddBook);
        String bookID = responseAddBookParams.get("ID").toString();

        Response response2 = request.queryParam("ID", bookID)
                .get("/Library/GetBook.php");

        String responseGetBook = response2.toString();

        Assert.assertEquals(response2.getStatusCode(), 200);

        JSONObject requestParamsDelete = new JSONObject();
        requestParamsDelete.put("ID", bookID);
        request.body(requestParamsDelete.toJSONString());

        Response response3 = request.post("/Library/DeleteBook.php");

        String responseDeleteBook = response3.asString();

        JSONObject responseDeleteBookParams = (JSONObject) parser.parse(responseDeleteBook);
        String deleteMsg = responseDeleteBookParams.get("msg").toString();
        System.out.println(deleteMsg);

        Assert.assertEquals(deleteMsg, "book is successfully deleted");

        Response response4 = request.queryParam("ID", bookID)
                .get("/Library/GetBook.php");

        String responseGetBook2 = response4.asString();

        JSONObject responseGetBookParams2 = (JSONObject) parser.parse(responseGetBook2);
        String responseMsg = responseGetBookParams2.get("msg").toString();
        Assert.assertEquals(responseMsg, "The book by requested bookid / author name does not exists!");


        JSONObject addBookRequest2 = new JSONObject();
        addBookRequest2.put("name", "Agile1");
        addBookRequest2.put("isbn", "1224");
        addBookRequest2.put("aisle", "1222");
        addBookRequest2.put("author", "Rowling123");
        request.body(addBookRequest.toJSONString());

        Response response5 = request.post("/Library/Addbook.php");

        String responseAddBook2 = response5.asString();
        System.out.println(responseAddBook2);

        JSONObject responseAddBookParams2 = (JSONObject) parser.parse(responseAddBook2);
        String addBookResponseMsg = responseAddBookParams2.get("Msg").toString();

        Assert.assertEquals(addBookResponseMsg, "successfully added");

    }

}