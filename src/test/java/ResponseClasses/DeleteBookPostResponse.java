package ResponseClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class DeleteBookPostResponse {
    @JsonProperty("Msg")
    private String msg;

    @JsonSetter("Msg")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
