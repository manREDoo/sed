package top.manred.messagedispatch.hanlder;

import org.springframework.stereotype.Component;
import top.manred.messagedispatch.dispatcher.AbstractHandler;
import top.manred.messagedispatch.dispatcher.Cmd;
import top.manred.messagedispatch.dispatcher.Command;
import top.manred.messagedispatch.dispatcher.CommandResult;
import top.manred.messagedispatch.dispatcher.MsgHead;

@Component
public class HelloHandler extends AbstractHandler {

    @Cmd(id = MsgHead.H_100000)
    public CommandResult hello(String name){
        return new CommandResult(1,"hello "+name+" !");
    }
}