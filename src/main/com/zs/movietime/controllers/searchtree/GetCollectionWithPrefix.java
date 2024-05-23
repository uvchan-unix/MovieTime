package com.zs.movietime.controllers.searchtree;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.zs.movietime.models.collectiontree.Collection;
import com.zs.movietime.models.util.FunctionDefanitions;

@WebServlet("/services/search/getcollectionWithPrefix")
public class GetCollectionWithPrefix extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-Type", "application/json");

        String prefix = req.getParameter("prefix");

        if (prefix.length() == 0 || prefix == null) {

            FunctionDefanitions.outputWriter("no input", resp);

        } else {
            Collection collection = FunctionDefanitions.getCollectionTree(req);
            JSONArray json = collection.getCollection(prefix);
            
            if (json != null) {
                FunctionDefanitions.outputWriter(json.toString(), resp);
            }
            else{
                FunctionDefanitions.outputWriter("No Matches Found :(", resp);
            }
        }

    }

}
