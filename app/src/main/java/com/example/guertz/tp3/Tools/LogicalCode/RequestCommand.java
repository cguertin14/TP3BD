package com.example.guertz.tp3.Tools.LogicalCode;

import org.json.JSONObject;

/**
 * Created by guertz on 28/08/17.
 */

public interface RequestCommand {
    void onPreExecute();
    void onSuccess(JSONObject data) throws Exception;
    void onFailure(JSONObject error) throws Exception;
    void onPostExecute(Boolean result);
}
