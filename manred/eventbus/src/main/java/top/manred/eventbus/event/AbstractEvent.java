package top.manred.eventbus.event;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEvent {
    //用于传输数据
    private Map<String,Object> data = new HashMap<>();

    public AbstractEvent() {
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }
}