package com.zs.movietime.models.collectiontree;

import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zs.movietime.models.DAO.DBConnection;
import com.zs.movietime.models.DAO.DBModule;
import com.zs.movietime.models.DAO.pojo.Movie;
import com.zs.movietime.models.DAO.pojo.Theatre;
import com.zs.movietime.models.util.JsonUtils;

public class Collection {

    private TrieNode rootNode;
    private ExecutorService executor;

    public Collection(DBModule db) {

        rootNode = new TrieNode();
        executor = Executors.newFixedThreadPool(20);
        collectionInit(db);
        // shutdownExecutor();

    }

    public void shutdownExecutor() {
        executor.shutdown();
    }

    public void insertMovie(String movieName, String img, String meta) {

        try {

            String nodeName = movieName.replaceAll("[\\s&.():'-/]", "");
            TrieNode temp = rootNode;

            for (int i = 0; i < nodeName.length(); i++) {
                char c = nodeName.charAt(i);
                int index = Character.toLowerCase(c) - 'a';

                if (temp.children[index] == null) {
                    temp.children[index] = new TrieNode();
                }
                temp = temp.children[index];

            }

            temp.isEndOfWord = true;
            temp.movie = new Movie(movieName, img, meta);
            temp.type = true;

        } catch (Exception e) {
            System.out.println(movieName);
            e.printStackTrace();
        }

    }

    public void insertTheatre(int theatreID, String theatreName, String location) {

        // executor.submit(() -> {

        try {

            TrieNode temp = rootNode;
            String nodeName = theatreName.replaceAll("[\\s&.()'-/]", "");

            for (int i = 0; i < nodeName.length(); i++) {
                char c = nodeName.charAt(i);
                int index = Character.toLowerCase(c) - 'a';
                if (index > 25) {
                    
                }
                if (temp.children[index] == null) {
                    temp.children[index] = new TrieNode();
                }
                temp = temp.children[index];

            }

            temp.isEndOfWord = true;
            temp.theatre = new Theatre(theatreID, theatreName, location);
            temp.type = false;

        } catch (Exception e) {

            System.out.println(e);
            e.printStackTrace();
        }

        // });

    }

    private TrieNode findNode(String prefix) {

        TrieNode temp = rootNode;

        for (int i = 0; i < prefix.length(); i++) {

            char c = prefix.charAt(i);
            int index = Character.toLowerCase(c) - 'a';

            if (temp.children[index] == null) {

                return null;
            }

            temp = temp.children[index];

            System.out.println(c);
        }
        return temp;

    }

    private JSONArray collectAllWords(TrieNode node, JSONArray array, String prefix) {

        if (node == null)
            return null;

        if (node.isEndOfWord) {

            if (node.type) {
                JSONObject json = JsonUtils.objToJson(node.movie);
                array.put(json);
            } else if (!node.type) {
                JSONObject json = JsonUtils.objToJson(node.theatre);
                array.put(json);
            }
        }

        for (int i = 0; i < 26; i++) {

            char c = (char) ('a' + i);
            collectAllWords(node.children[i], array, prefix + c);

        }

        return array;

    }

    private void collectionInit(DBModule db) {

        try (ResultSet movieResult = db.getAllMovie()) {

            while (movieResult.next()) {
                insertMovie(movieResult.getString("movie_name"), movieResult.getString("movie_img_id"),
                        movieResult.getString("movie_meta_json"));
            }

            System.out.println("Data insertion in tree is successfull");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ResultSet theatreResult = db.getAllTheatre()) {
            while (theatreResult.next()) {
                insertTheatre(theatreResult.getInt("theatre_id"), theatreResult.getString("theatre_name"),
                        theatreResult.getString("theatre_location"));
            }

        } catch (Exception e) {
            // TODO: handle exception

            e.printStackTrace();
        }
    }

    public JSONArray getCollection(String prefix) {

        JSONArray array = collectAllWords(findNode(prefix), new JSONArray(), prefix);
        if (array != null) {
            return array;
        }
        return null;
    }

    public static void main(String[] args) {
        DBConnection con = new DBConnection();
        DBModule db = new DBModule(con.getConnection());
        Collection obj = new Collection(db);
        System.out.println(obj.getCollection("a"));
    }
}

class TrieNode {

    TrieNode[] children;
    boolean isEndOfWord;
    Movie movie = null;
    Theatre theatre = null;
    boolean type;

    TrieNode() {

        this.isEndOfWord = false;
        this.children = new TrieNode[26];
    }

}