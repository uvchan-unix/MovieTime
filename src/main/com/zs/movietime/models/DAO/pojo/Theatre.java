package com.zs.movietime.models.DAO.pojo;

public class Theatre {
    
    private int theatreID; 
    private String theatreName;
    private String theatreLocation;

    
    public Theatre(int theatreID, String theatreName, String theatreLocation) {
        this.theatreID = theatreID;
        this.theatreName = theatreName;
        this.theatreLocation = theatreLocation;
    }
    
    public int getTheatreID() {
        return theatreID;
    }
    public void setTheatreID(int theatreID) {
        this.theatreID = theatreID;
    }
    public String getTheatreName() {
        return theatreName;
    }
    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }
    public String getTheatreLocation() {
        return theatreLocation;
    }
    public void setTheatreLocation(String theatreLocation) {
        this.theatreLocation = theatreLocation;
    }

    
}
