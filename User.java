public class User {
    protected int userId;
    protected String name;
    protected String email;
    protected String role;

    public User(int id, String name, String email, String role) {
        this.userId = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
