package ResponseClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class AddBookPostResponse {
    @JsonProperty("id")

    private String Msg;
    private String ID;

    public String getMsg() {
        return Msg;
    }

    @JsonSetter("Msg")
    public void setMsg(String Msg) {
        this.Msg = Msg;
    }


    public String getID() {
        return ID;
    }

    @JsonSetter("ID")
    public void setID(String ID) {
        this.ID = ID;
    }
}
