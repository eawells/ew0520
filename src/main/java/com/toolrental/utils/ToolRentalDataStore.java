package com.toolrental.utils;

import com.toolrental.model.Tool;
import com.toolrental.model.ToolType;

import java.math.BigDecimal;
import java.util.HashMap;

public class ToolRentalDataStore {

    private static final String LADDER_TYPE = "Ladder";
    private static final String CHAINSAW_TYPE = "Chainsaw";
    private static final String JACKHAMMER_TYPE = "Jackhammer";

    private static final String WERNER_LADDER_CODE = "LADW";
    private static final String STIHL_CHAINSAW_CODE = "CHNS";
    private static final String RIDGID_JACKHAMMER_CODE = "JAKR";
    private static final String DEWALT_JACKHAMMER_CODE = "JAKD";

    private ToolRentalDataStore(){}

    public static HashMap<String, Tool> getToolData(){
        HashMap<String, ToolType> typeToToolTypeMap = createToolTypeData();
        return createToolData(typeToToolTypeMap);
    }

    private static HashMap<String, ToolType> createToolTypeData(){
        HashMap<String, ToolType> typeToToolTypeMap = new HashMap();
        typeToToolTypeMap.put(LADDER_TYPE, new ToolType(LADDER_TYPE, new BigDecimal("1.99"), true, true, false));
        typeToToolTypeMap.put(CHAINSAW_TYPE,  new ToolType(CHAINSAW_TYPE, new BigDecimal("1.49"), true, false, true));
        typeToToolTypeMap.put(JACKHAMMER_TYPE, new ToolType(JACKHAMMER_TYPE, new BigDecimal("2.99"), true, false, false));
        return typeToToolTypeMap;
    }

    private static HashMap<String, Tool> createToolData(HashMap<String, ToolType> typeToToolTypeMap){
        HashMap<String, Tool> codeToToolMap = new HashMap();
        codeToToolMap.put(WERNER_LADDER_CODE, new Tool(WERNER_LADDER_CODE, typeToToolTypeMap.get(LADDER_TYPE), "Werner"));
        codeToToolMap.put(STIHL_CHAINSAW_CODE, new Tool(STIHL_CHAINSAW_CODE, typeToToolTypeMap.get(CHAINSAW_TYPE), "Stihl"));
        codeToToolMap.put(RIDGID_JACKHAMMER_CODE, new Tool(RIDGID_JACKHAMMER_CODE, typeToToolTypeMap.get(JACKHAMMER_TYPE), "Ridgid"));
        codeToToolMap.put(DEWALT_JACKHAMMER_CODE, new Tool(DEWALT_JACKHAMMER_CODE, typeToToolTypeMap.get(JACKHAMMER_TYPE), "DeWalt"));
        return codeToToolMap;
    }

}
