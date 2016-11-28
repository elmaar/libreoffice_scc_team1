package com.acms.byoc.global;

import java.util.HashMap;
import java.util.Map;

import com.acms.framework.taglib.util.html.json.JSONArray;
import com.acms.framework.taglib.util.html.json.JSONObject;


public class Processor
{
  Map<String,AbstractAction> actionsList;
  
  public Processor ()
  {
    actionsList = new HashMap<String, AbstractAction>();
    actionsList.put("CREATE_TABLE", new CreateTableAction());
  }
  
  public void process(String content,JSONArray actionsJson)
  {
    for(int i=0;i<actionsJson.length();i++)
    {
      JSONObject action = actionsJson.getJSONObject(i);
      actionsList.get(action.getString("action")).process(content, action);
    }
    System.out.println("fini");
  }
}
