package com.zs.movietime.controllers.searchtree;

import com.opensymphony.xwork2.ActionSupport;
import com.zs.movietime.models.collectiontree.Collection;
import com.zs.movietime.models.util.FunctionDefanitions;

import org.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;


public class GetCollectionWithPrefixAction extends ActionSupport{
    private HttpServletResponse response = ServletActionContext.getResponse();
    private HttpServletRequest request = ServletActionContext.getRequest();
    
    private String prefix = request.getParameter("prefix");
    private String jsonResponse;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    // Getter for 'jsonResponse'
    public String getJsonResponse() {
        return jsonResponse;
    }

    @Override
    public String execute() throws Exception {

        response.setHeader("Content-Type", "application/json");

        if (prefix == null || prefix.length() == 0) {
            jsonResponse = "no input";
        } else {
            System.out.println(request);
            Collection collection = (Collection) FunctionDefanitions.getCollectionTree(request);
            JSONArray json = collection.getCollection(prefix);

            if (json != null) {
                jsonResponse = json.toString();
                FunctionDefanitions.outputWriter(jsonResponse, response);
            } else {
                jsonResponse = "No Matches Found :(";
                FunctionDefanitions.outputWriter(jsonResponse, response);
            }
        }

        return SUCCESS;
    }
}
