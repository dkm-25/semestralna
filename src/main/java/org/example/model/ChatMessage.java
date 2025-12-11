package org.example.model;

public class ChatMessage {

    private Long groupId;
    private Long userId;
    private String userName;
    private String message;

    public ChatMessage() {}

    public ChatMessage(Long groupId, Long userId, String userName, String message) {
        this.groupId = groupId;
        this.userId = userId;
        this.userName = userName;
        this.message = message;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
