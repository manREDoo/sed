package top.manred.messagedispatch.dispatcher;

public interface IMessageDispatcher {
    /**
     * 实现消息分发
     * @param command
     * @return
     */
    public CommandResult dispatch(Command command);
}
