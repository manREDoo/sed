package top.manred.asyndata.data;


import lombok.Data;
import top.manred.asyndata.asyn.AsyncObject;
import top.manred.asyndata.asyn.Provider;

import java.io.Serializable;

@Provider(RecordManagerImpl.class)
@Data
public class Record extends AsyncObject implements Serializable {
    private String data;

    public Record(String data) {
        this.data = data;
    }
}
