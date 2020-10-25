package team5.entity;

public enum Role {
    ADMIN,USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }



}
