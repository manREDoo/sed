package top.manred.messagedispatch.dispatcher;

import java.util.Map;

/**
 * Handler调用service时，统一返回CommandResult,直接让command.decode(CommandResult r)
 * 
 */
public class CommandResult {

	public static final int SUCCESS = 0;
	public static final int FAILT = 1;

	private int result = SUCCESS;

	private Map<String, Object> map = null;

	private Object object = null;

	public CommandResult() {
	}

	public CommandResult(int result) {
		this.result = result;
	}

	public CommandResult(int result, Object object) {
		this.result = result;
		this.object = object;
	}

	public CommandResult(int result, Map<String, Object> map) {
		this.result = result;
		this.map = map;
	}

    public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
