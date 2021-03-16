package TestCases;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.Random;

public class TestMethods {

    public static int responseCode = 0;
    public static String responseMsg = "";
    public static String bookID = "";


    public Random random = new Random();


    public String rawAisle() {

        int rawAisle = random.nextInt(999)+1;
        String aisleNew = Integer.toString(rawAisle);
        return aisleNew;

    }


    public void addBook() throws ParseException {
        RestAssured.defaultParser = Parser.JSON;
        JSONObject addBookRequest = new JSONObject();
        addBookRequest.put("name", "API Testing Framework");
        addBookRequest.put("isbn", "10101");
        addBookRequest.put("aisle", "127");
        addBookRequest.put("author", "Leo1");


        Response response1 = RestAssured.given().body(addBookRequest.toJSONString())
                .post("/Library/Addbook.php");
        System.out.println("Status Code: " + response1.getStatusCode());

        String responseAddBook = response1.asString();

        responseCode = response1.getStatusCode();

        JSONParser parser = new JSONParser();
        JSONObject responseAddBookParams = (JSONObject) parser.parse(responseAddBook);


        if (responseCode == 200) {
            responseMsg = responseAddBookParams.get("Msg").toString();
        } else if (responseCode == 404) {
            responseMsg = responseAddBookParams.get("msg").toString();
        } else
            responseMsg = "Nothing displayed in Response Body!!!";
    }


    public void addBookRequest(String bookName2) throws ParseException {
        RestAssured.defaultParser = Parser.JSON;
        String aisle2 = rawAisle();

        JSONObject addBookRequest = new JSONObject();
        addBookRequest.put("name", bookName2);
        addBookRequest.put("isbn", "10102");
        addBookRequest.put("aisle", aisle2);
        addBookRequest.put("author", "Cake3");


        Response response1 = RestAssured.given().body(addBookRequest.toJSONString())
                .post("/Library/Addbook.php");
        System.out.println("Status Code: " + response1.getStatusCode());

        String responseAddBook = response1.asString();
        System.out.println(responseAddBook);

        responseCode = response1.getStatusCode();
        JSONParser parser = new JSONParser();
        JSONObject responseAddBookParams = (JSONObject) parser.parse(responseAddBook);
        bookID = responseAddBookParams.get("ID").toString();

        if (responseCode == 200) {
            responseMsg = responseAddBookParams.get("Msg").toString();
            System.out.println(responseMsg);
        } else if (responseCode == 404) {
            responseMsg = responseAddBookParams.get("msg").toString();
            System.out.println(responseMsg);
        } else {
            responseMsg = "Nothing displayed in Response Body!!!";
            System.out.println(responseMsg);
        }

    }


    public void addBookPOSTRequest(String bookName1, String authorName1) {
        String aisle3 = rawAisle();

        JSONObject addBookRequest = new JSONObject();
        addBookRequest.put("name", bookName1);
        addBookRequest.put("isbn", "10103");
        addBookRequest.put("aisle", aisle3);
        addBookRequest.put("author", authorName1);


        Response response1 = RestAssured.given().body(addBookRequest.toJSONString())
                .post("/Library/Addbook.php");
        System.out.println("Status Code: " + response1.getStatusCode());
    }




    public int getBooksAuthorName(String authorName3) throws ParseException {

        RestAssured.defaultParser = Parser.JSON;

        Response response2 = RestAssured.given().queryParam("AuthorName", authorName3)
                .get("/Library/GetBook.php");


        List<String> jsonResponse = response2.jsonPath().getList("book_name");

        int bookCount = jsonResponse.size();

        System.out.println(bookCount);

        String bookNames = response2.jsonPath().getString("book_name");
        System.out.println(bookNames);

        return bookCount;
    }

    public void getBookByID(String ID) throws ParseException {

        Response response4 = RestAssured.given().queryParam("ID", ID)
                .get("/Library/GetBook.php");

        String responseGetBook = response4.asString();

        responseCode = response4.getStatusCode();


        if(responseCode == 200) {
            List<String> jsonResponse = response4.jsonPath().getList("book_name");


            int bookCount1 = jsonResponse.size();
            System.out.println(bookCount1);
            String bookName = response4.jsonPath().getString("book_name[0]");
            System.out.println(bookName);
            String isbn1 = response4.jsonPath().getString("isbn[0]");
            System.out.println(isbn1);
            String aisle1 = response4.jsonPath().getString("aisle[0]");
            System.out.println(aisle1);

        }

        else if(responseCode ==404){
            JSONParser parser = new JSONParser();

            JSONObject responseGetBookParams2 = (JSONObject) parser.parse(responseGetBook);
            responseMsg = responseGetBookParams2.get("msg").toString();
            System.out.println(responseMsg);
        }

        else
            System.out.println("Not Valid response!!");

    }


    public void deleteBookByID(String bookID) throws ParseException {

        JSONObject requestParamsDelete = new JSONObject();
        requestParamsDelete.put("ID", bookID);

        Response response5 = RestAssured.given().body(requestParamsDelete.toJSONString())
                .post("/Library/DeleteBook.php");

        String responseDeleteBook = response5.asString();

        responseCode = response5.getStatusCode();
        JSONParser parser = new JSONParser();

        JSONObject responseDeleteBookParams = (JSONObject) parser.parse(responseDeleteBook);

        if (responseCode == 404) {
            responseMsg = responseDeleteBookParams.get("msg").toString();
            System.out.println(responseMsg);
        } else if (responseCode == 200) {
            responseMsg = responseDeleteBookParams.get("msg").toString();
            System.out.println(responseMsg);
            System.out.println("DeleteBook Response Code: " + responseCode);
        } else
            System.out.println("Not Valid response!!");
    }




public void deleteBookByAuthorName(String authorName6) throws ParseException {
  RestAssured.defaultParser = Parser.JSON;

    Response response6 = RestAssured.given().queryParam("AuthorName", authorName6)
            .get("/Library/GetBook.php");

    String responseGetBookByAuthor = response6.asString();
    System.out.println(responseGetBookByAuthor);

    List<String> jsonResponse = response6.jsonPath().getList("book_name");

    int bookCount = jsonResponse.size();

    //System.out.println(bookCount);

    String isbn1 = response6.jsonPath().getString("isbn[0]");
    String aisle1 = response6.jsonPath().getString("aisle[0]");

    String bookIDOfAuthor = isbn1+aisle1;

    JSONObject requestParamsDelete = new JSONObject();
    requestParamsDelete.put("ID", bookIDOfAuthor);

    Response response7 = RestAssured.given().body(requestParamsDelete.toJSONString())
            .post("/Library/DeleteBook.php");

    String responseDeleteBook = response7.asString();

    responseCode = response7.getStatusCode();
    JSONParser parser = new JSONParser();

    JSONObject responseDeleteBookParams7 = (JSONObject) parser.parse(responseDeleteBook);

    if (responseCode == 404) {
        responseMsg = responseDeleteBookParams7.get("msg").toString();
        System.out.println(responseMsg);
    } else if (responseCode == 200)
    {
        responseMsg = responseDeleteBookParams7.get("msg").toString();
        System.out.println(responseMsg);
        System.out.println("DeleteBook Response Code: " + responseCode);
    } else
        System.out.println("Not Valid response!!");
    }

}
