package org.example;

public class LoginInfo {
    private String username;
    private String ipAddress;
    private boolean attemptSuccess;

    public LoginInfo(String username, String ipAddress, boolean attemptSuccess) {
        this.username = username;
        this.ipAddress = ipAddress;
        this.attemptSuccess = attemptSuccess;
    }

    public LoginInfo(String janeSmith, String securepass) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isAttemptSuccess() {
        return attemptSuccess;
    }

    public void setAttemptSuccess(boolean attemptSuccess) {
        this.attemptSuccess = attemptSuccess;
    }

}
