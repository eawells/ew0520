package com.toolrental.repository;

import com.toolrental.model.Tool;
import com.toolrental.utils.ToolRentalDataStore;

import java.util.HashMap;

public class ToolRepository {
    private HashMap<String, Tool> codeToToolMap;
    private ToolRentalDataStore toolRentalDataStore;

    public ToolRepository(){
        toolRentalDataStore = new ToolRentalDataStore();
        codeToToolMap = toolRentalDataStore.getToolData();
    }

    public Tool getTool(String code){
       return codeToToolMap.get(code);
    }
}
