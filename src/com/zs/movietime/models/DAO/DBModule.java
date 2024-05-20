package zs.movietime.models.DAO;

import java.sql.Connection;

public class DBModule {

    private Connection con = null;
    
    public DBModule(Connection con){
        this.con = con;
    }
    
    
}
