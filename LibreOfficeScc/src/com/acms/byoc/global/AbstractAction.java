package com.acms.byoc.global;

import com.acms.framework.taglib.util.html.json.JSONObject;

public abstract class AbstractAction
{
  public abstract void process(String filename, JSONObject actions);
}
