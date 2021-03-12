package TestCases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestMethods {

    public static int responseCode = 0;
    public static String responseMsg = "";
    public static String bookID = "";
    public static JSONParser parser = new JSONParser();

    public void addBook() throws ParseException {

        JSONObject addBookRequest = new JSONObject();
        addBookRequest.put("name", "API Testing Framework");
        addBookRequest.put("isbn", "10101");
        addBookRequest.put("aisle", "124");
        addBookRequest.put("author", "Leo");


        Response response1 = RestAssured.given().body(addBookRequest.toJSONString())
                .post("/Library/Addbook.php");
        System.out.println("Status Code: " + response1.getStatusCode());

        String responseAddBook = response1.asString();
        //System.out.println(responseAddBook);

        responseCode = response1.getStatusCode();

        JSONParser parser = new JSONParser();
        JSONObject responseAddBookParams = (JSONObject) parser.parse(responseAddBook);
//        bookID = responseAddBookParams.get("ID").toString();

        if (responseCode == 200) {
            responseMsg = responseAddBookParams.get("Msg").toString();
        } else if (responseCode == 404) {
            responseMsg = responseAddBookParams.get("msg").toString();
        } else
            responseMsg = "Nothing displayed in Response Body!!!";
    }


    public void addBookRequest(String bookName) throws ParseException {

        JSONObject addBookRequest = new JSONObject();
        addBookRequest.put("name", bookName);
        addBookRequest.put("isbn", "10101");
        addBookRequest.put("aisle", "1");
        addBookRequest.put("author", "leo2");


        Response response1 = RestAssured.given().body(addBookRequest.toJSONString())
                .post("/Library/Addbook.php");
        System.out.println("Status Code: " + response1.getStatusCode());

        String responseAddBook = response1.asString();
        System.out.println(responseAddBook);

        responseCode = response1.getStatusCode();

        JSONObject responseAddBookParams = (JSONObject) parser.parse(responseAddBook);
        bookID = responseAddBookParams.get("ID").toString();

        if (responseCode == 200) {
            responseMsg = responseAddBookParams.get("Msg").toString();
        } else if (responseCode == 404) {
            responseMsg = responseAddBookParams.get("msg").toString();
        } else
            responseMsg = "Nothing displayed in Response Body!!!";


    }


    public void addBookPOSTRequest(String bookName) {
        String aisle1 = rawAisle();

        JSONObject addBookRequest = new JSONObject();
        addBookRequest.put("name", bookName);
        addBookRequest.put("isbn", "10101");
        addBookRequest.put("aisle", aisle1);
        addBookRequest.put("author", "Divya112");


        Response response1 = RestAssured.given().body(addBookRequest.toJSONString())
                .post("/Library/Addbook.php");
        System.out.println("Status Code: " + response1.getStatusCode());
    }

    public String rawAisle() {
        String aisle = "123";
        int rawAisle = Integer.parseInt(aisle);
        rawAisle++;
        String aisleNew = Integer.toString(rawAisle);
        return aisleNew;

    }


    public int getBooksAuthorName(String authorName) throws ParseException {

        Response response2 = RestAssured.given().queryParam("AuthorName", authorName)
                .get("/Library/GetBook.php");

        String responseGetBookByAuthor = response2.asString();
        System.out.println(responseGetBookByAuthor);


        JSONObject responseAddBookParams = (JSONObject) parser.parse(responseGetBookByAuthor);
        int bookCount = 0;
        for (int i = 0; i < responseAddBookParams.size(); i++) {
            JSONObject bookName = (JSONObject) responseAddBookParams.get("book_name");
            bookCount++;
            System.out.println(bookName.toString());
        }

        //Assert.assertEquals(bookCount, 3);

        return bookCount;
    }

    public void getBookByID(String ID) throws ParseException {
        Response response4 = RestAssured.given().queryParam("ID", ID)
                .get("/Library/GetBook.php");

        String responseGetBook = response4.asString();

        responseCode = response4.getStatusCode();

        JSONObject responseGetBookParams2 = (JSONObject) parser.parse(responseGetBook);

        if (responseCode == 404) {
            responseMsg = responseGetBookParams2.get("msg").toString();
            System.out.println(responseMsg);
        } else if (responseCode == 200) {
            System.out.println("GetBookByID Response Code: " + responseCode);
        } else
            System.out.println("Not Valid response!!");

    }


    public void deleteBookByID(String bookID) throws ParseException {
        JSONObject requestParamsDelete = new JSONObject();
        requestParamsDelete.put("ID", bookID);

        Response response5 = RestAssured.given().body(requestParamsDelete.toJSONString())
                .post("/Library/DeleteBook.php");

        String responseDeleteBook = response5.asString();
        //System.out.println(responseOutput);

        responseCode = response5.getStatusCode();

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




/*
public void deleteBookByAuthorName(String authorName) throws ParseException {


    Response response6 = RestAssured.given().queryParam("AuthorName", authorName)
            .get("/Library/GetBook.php");

    String responseGetBookByAuthor = response6.asString();
    System.out.println(responseGetBookByAuthor);


    JSONObject responseAddBookParams = (JSONObject) parser.parse(responseGetBookByAuthor);
    */
/*int bookCount = 0;
    for(int i =0; i < responseAddBookParams.size(); i++)
    {
        JSONObject author = (JSONObject) responseAddBookParams.get("author");
        bookCount++;
        System.out.println(bookName.toString());
    }*//*




    String bookIDOfAuthor = isbn1+aisle1;

    JSONObject requestParamsDelete = new JSONObject();
    requestParamsDelete.put("ID", bookIDOfAuthor);

    Response response7 = RestAssured.given().body(requestParamsDelete.toJSONString())
            .post("/Library/DeleteBook.php");

    String responseDeleteBook = response7.asString();
    //System.out.println(responseOutput);

    responseCode = response7.getStatusCode();

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
*/

}
