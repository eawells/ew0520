package com.toolrental.utils;

import com.toolrental.model.Tool;
import com.toolrental.model.ToolType;

import java.math.BigDecimal;
import java.util.HashMap;

public class ToolRentalDataStore {
    private HashMap<String, ToolType> typeToToolTypeMap;
    private HashMap<String, Tool> codeToToolMap;

    public ToolRentalDataStore(){
        setToolTypeData();
        setToolData(typeToToolTypeMap);
    }

    public HashMap<String, Tool> getToolData(){
        return codeToToolMap;
    }

    private void setToolTypeData(){
        typeToToolTypeMap = new HashMap();
        ToolType ladder = new ToolType("Ladder", new BigDecimal("1.99"), true, true, false);
        ToolType chainsaw = new ToolType("Chainsaw", new BigDecimal("1.49"), true, false, true);
        ToolType jackhammer = new ToolType("Jackhammer", new BigDecimal("2.99"), true, false, false);
        typeToToolTypeMap.put(ladder.getType(), ladder);
        typeToToolTypeMap.put(chainsaw.getType(), chainsaw);
        typeToToolTypeMap.put(jackhammer.getType(), jackhammer);
    }

    private void setToolData(HashMap<String, ToolType> typeToToolTypeMap){
        codeToToolMap = new HashMap();
        Tool ladw = new Tool("LADW", typeToToolTypeMap.get("Ladder"), "Werner");
        Tool chns = new Tool("CHNS", typeToToolTypeMap.get("Chainsaw"), "Stihl");
        Tool jakr = new Tool("JAKR", typeToToolTypeMap.get("Jackhammer"), "Ridgid");
        Tool jakd = new Tool("JAKD", typeToToolTypeMap.get("Jackhammer"), "DeWalt");
        codeToToolMap.put(ladw.getCode(), ladw);
        codeToToolMap.put(chns.getCode(), chns);
        codeToToolMap.put(jakr.getCode(), jakr);
        codeToToolMap.put(jakd.getCode(), jakd);
    }

}
