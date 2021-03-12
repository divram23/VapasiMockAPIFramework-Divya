package RequestClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookPostRequest {
    @JsonProperty("ID")
    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
