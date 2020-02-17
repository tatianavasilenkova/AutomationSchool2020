package my.reqrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPage {

    public Users[] getData() {
        return data;
    }

    public void setData(Users[] data) {
        this.data = data;
    }

    Users[] data;

}
