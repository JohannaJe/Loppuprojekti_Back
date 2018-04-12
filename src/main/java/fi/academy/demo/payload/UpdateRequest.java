package fi.academy.demo.payload;

import javax.validation.constraints.*;

public class UpdateRequest {

    @NotBlank
    @Size(max = 200)
    private String data;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}