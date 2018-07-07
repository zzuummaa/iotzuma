package ru.zuma.iotzuma;

import ru.zuma.iotzuma.model.IOTInputData;
import ru.zuma.iotzuma.model.IOTOutputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IOTDataService {

    private static IOTDataService instance;

    private Map<String, ArrayList<IOTOutputData>> dataMap;

    public static IOTDataService getInstance() {
        if (instance == null) {
            instance = new IOTDataService();
        }
        return instance;
    }

    private IOTDataService() {
        dataMap = new HashMap<>();
    }

    public void add(IOTInputData inputData) {
        IOTOutputData outputData = new IOTOutputData();
        outputData.setType(inputData.getType());
        outputData.setValue(inputData.getValue());
        outputData.setTime(System.currentTimeMillis());

        if (!dataMap.containsKey(inputData.getType())) {
            dataMap.put(inputData.getType(), new ArrayList<IOTOutputData>());
        }
        dataMap.get(inputData.getType()).add(outputData);
    }

    public ArrayList<IOTOutputData> get(String type) {
        return dataMap.get(type);
    }
}
