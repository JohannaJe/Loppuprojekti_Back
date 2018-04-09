package fi.academy.demo.payload;

public class UserSummary {
    private Long id;
    private String username;
    private String name;
    private String data;

    public UserSummary(Long id, String username, String name, String data) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}