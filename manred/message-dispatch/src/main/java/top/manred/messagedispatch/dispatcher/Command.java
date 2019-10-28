package top.manred.messagedispatch.dispatcher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Command {

    //消息头
    protected int head;

    //参数严格有序
    protected String[] bodies;


    public Command() {
    }

    public Command(int head) {
        this.head = head;
    }

    public Command(int head, String[] bodies) {
        this.head = head;
        this.bodies = bodies;
    }
}