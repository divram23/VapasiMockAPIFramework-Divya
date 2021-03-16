package MockTestcases;

import org.json.simple.JSONObject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class StubMethods {

    public void stubAddBookPOSTMethod(){

        JSONObject addBookResponseParams = new JSONObject();
        addBookResponseParams.put("name", "WireMock Book1");
        addBookResponseParams.put("isbn", "112211");
        addBookResponseParams.put("aisle", "22");
        addBookResponseParams.put("author", "Flower");


        stubFor(post(urlEqualTo("/Library/Addbook.php"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(addBookResponseParams.toJSONString()).withStatus(200)));


    }

    public void stubGetBookByIDGETMethod(String ID){

        JSONObject getBookResponseParams = new JSONObject();
        getBookResponseParams.put("name", "WireMock Book2");
        getBookResponseParams.put("isbn", "112212");
        getBookResponseParams.put("aisle", "12");
        getBookResponseParams.put("author", "Flower1");

        stubFor(get(urlEqualTo("/Library/GetBook.php?ID="+ID))//.withQueryParam("ID", ID)
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(getBookResponseParams.toJSONString()).withStatus(200)));

    }

    public void stubGetBookByAuthorNameGETMethod(String authorName){

        JSONObject getBookAuthorResponseParams = new JSONObject();
        getBookAuthorResponseParams.put("book_name", "WireMock Book3");
        getBookAuthorResponseParams.put("isbn", "110011");
        getBookAuthorResponseParams.put("aisle", "101");

        stubFor(get(urlEqualTo("/Library/GetBook.php?AuthorName="+authorName))//.withQueryParam("AuthorName", authorName)
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(getBookAuthorResponseParams.toJSONString()).withStatus(200)));


    }


    public void stubDeleteBookPOSTMethod(){

        stubFor(post(urlEqualTo("/Library/DeleteBook.php"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withBody("Delete operation working via Mocking!!!").withStatus(200)));

    }

}
