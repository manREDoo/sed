package top.manred.messagedispatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.manred.messagedispatch.MessageDispatchApplication;
import top.manred.messagedispatch.dispatcher.Command;
import top.manred.messagedispatch.dispatcher.CommandResult;
import top.manred.messagedispatch.dispatcher.IMessageDispatcher;
import top.manred.messagedispatch.dispatcher.MessageDispatcher;
import top.manred.messagedispatch.dispatcher.MsgHead;

@RestController
public class HelloController {
    @Autowired
    private IMessageDispatcher messageDispatcher;

/*    @GetMapping("/hello")
    public CommandResult hello(int head, String[] args){
        return messageDispatcher.dispatch(new Command(head, args));
    }*/

    @GetMapping("/hello")
    public CommandResult hello(String name){
        int head = MsgHead.H_100000;
        String[] args = new String[1];
        args[0] = name;
        return messageDispatcher.dispatch(new Command(head, args));
    }
}
