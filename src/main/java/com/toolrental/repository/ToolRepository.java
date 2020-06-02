package com.toolrental.repository;

import com.toolrental.model.Tool;
import com.toolrental.utils.ToolRentalDataStore;

import java.util.HashMap;

public class ToolRepository {
    private HashMap<String, Tool> codeToToolMap;

    public ToolRepository(){
        codeToToolMap = ToolRentalDataStore.getToolData();
    }

    public Tool getTool(String code){
       return codeToToolMap.get(code);
    }
}
