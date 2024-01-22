package learnspring.blogapplication;

import lombok.ToString;

@ToString
public class ErrorMsg {
    String message;

    public ErrorMsg(String message) {
        this.message = message;
    }
}
