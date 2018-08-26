package egcom.yafi.packy;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    Role( String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
