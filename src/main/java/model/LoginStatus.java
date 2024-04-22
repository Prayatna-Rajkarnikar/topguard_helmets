package model;

public class LoginStatus {
	private int status;
    private String role;

    public LoginStatus(int status, String role) {
        this.status = status;
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }
}

