abstract class User {
    public String name;
    public String email;
    public String password;

    public abstract boolean isValidPassword(String password);
}

class Member implements User {
    public boolean isValidPassword(String password)  {
        return this.password.equals(password);
    }
}

class AnonymousUser implements User {
    public boolean isValidPassword(String password)  {
        throw new Exception("Anonymous users don't have passwords")
    }
}

class EventOrganizer extends Member {
    int attendedEvents = 0;
}