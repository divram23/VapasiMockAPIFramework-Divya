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
    public void verifyDeleteBookAndReaddBook() throws ParseException {
        RestAssured.baseURI = "http://216.10.245.166";

        addBookRequest("Testing 1");
        String bookIDadded = TestMethods.bookID;
        getBookByID(bookIDadded);
        deleteBookByID(bookIDadded);
        getBookByID(bookIDadded);
        addBookRequest("Testing 1");
        String actualMsg = TestMethods.responseMsg;
        Assert.assertEquals(actualMsg, "successfully added");

    }

    @Test (priority = 4)
    public void verifyGetBookByAuthorName() throws ParseException {
        RestAssured.baseURI = "http://216.10.245.166";

        addBookPOSTRequest("Book1");
        addBookPOSTRequest("Book2");
        addBookPOSTRequest("Book3");

        int actualBookCount = getBooksAuthorName("Divya112");
        Assert.assertEquals(actualBookCount, 3);

    }

    /*@Test(priority =5)
    public void verifyDeleteBookAndGetBookByAuthor() throws ParseException {
        RestAssured.baseURI = "http://216.10.245.166";

        addBookPOSTRequest("API 1");
        addBookPOSTRequest("API 2");
        addBookPOSTRequest("API 3");

        int bookCountbyAuthorBeforeDelete = getBooksAuthorName("boxom");

        deleteBookByAuthorName("boom");

        int bookCountByAuthorAfterDelete = getBooksAuthorName("boxom");

        Assert.assertEquals(bookCountByAuthorAfterDelete, bookCountbyAuthorBeforeDelete);

    }*/
}
