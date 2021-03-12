package TestCases;

import io.restassured.RestAssured;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utils.ListenerTest.class)

public class TestSet3 extends TestMethods {

    @Test(priority = 1)
    public void verifyAddBookPOSTPositiveCase() throws ParseException {
        RestAssured.baseURI = "http://216.10.245.166";
        addBook();

        int actualResponseCode = TestMethods.responseCode;
        Assert.assertEquals(actualResponseCode, 200);
        System.out.println("Book created with ID: "+TestMethods.bookID);
    }

    @Test(priority = 2)
    public void verifyAddBookPOSTNegativeCase() throws ParseException {
        RestAssured.baseURI = "http://216.10.245.166";
        addBook();

        int actualResponseCode = TestMethods.responseCode;
        Assert.assertEquals(actualResponseCode, 404);
        System.out.println("Book created with ID: "+TestMethods.responseMsg);
    }

    @Test(priority = 3)
    public void verifyDeleteBookAndReadBook() throws ParseException {
        RestAssured.baseURI = "http://216.10.245.166";

        addBookRequest("CICD1");
        String bookIAdded = TestMethods.bookID;
        getBookByID(bookIAdded);
        deleteBookByID(bookIAdded);
        getBookByID(bookIAdded);
        addBookRequest("CICD1");
        String actualMsg = TestMethods.responseMsg;
        Assert.assertEquals(actualMsg, "successfully added");

    }

    @Test (priority = 4)
    public void verifyGetBookByAuthorName() throws ParseException {
        RestAssured.baseURI = "http://216.10.245.166";

        addBookPOSTRequest("Mock1", "Mark7");
        addBookPOSTRequest("Mock2", "Mark7");
        addBookPOSTRequest("Mock3", "Mark7");

        int actualBookCount = getBooksAuthorName("Mark7");
        System.out.println("No of Books listed for Author: "+actualBookCount);
        Assert.assertEquals(actualBookCount, 3);

    }

    @Test(priority =5)
    public void verifyDeleteBookAndGetBookByAuthor() throws ParseException {
        RestAssured.baseURI = "http://216.10.245.166";

        addBookPOSTRequest("BookTest11", "Long1");
        addBookPOSTRequest("API 11", "Kayle1");
        addBookPOSTRequest("API 21", "Kayle1");
        addBookPOSTRequest("API 31", "Kayle1");

        int bookCountOfAuthor1 = getBooksAuthorName("Long1");
        System.out.println(bookCountOfAuthor1);

        int bookCountOfAuthor2BeforeDelete = getBooksAuthorName("Kayle1");

        deleteBookByAuthorName("Long1");

        int bookCountByAuthorAfterDelete = getBooksAuthorName("Kayle1");

        Assert.assertEquals(bookCountByAuthorAfterDelete, bookCountOfAuthor2BeforeDelete);
    }
}
