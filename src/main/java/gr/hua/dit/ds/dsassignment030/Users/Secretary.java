package gr.hua.dit.ds.dsassignment030.Users;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import static gr.hua.dit.ds.dsassignment030.config.MySQL.db_con_obj;
import static gr.hua.dit.ds.dsassignment030.config.MySQL.db_prep_obj;

public class Secretary
{
    @Autowired
    DataSource dataSource;

    public Secretary(){}

    public String getDocPoints(String docid) throws SQLException
    {
        //TODO: Validate if ID exists in database->https://www.baeldung.com/spring-data-exists-query

        String getQueryStatement = "select sum(SurveilancePoints + CorrectionPoints + TeachingPoints) as 'Total' from PhD_Candidate	where ID="
                + docid + ";";
        db_prep_obj = db_con_obj.prepareStatement(getQueryStatement);
        ResultSet rs = db_prep_obj.executeQuery();

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next())
        {
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
            {
                Object object = rs.getObject(columnIndex);
                String col_name = metaData.getColumnName(columnIndex);
                return col_name+' '+object.toString()+"/80";
            }
        }
        return "";
    }
    public String getcorrectionPoints(String docid) throws SQLException
    {
        //TODO: Validate if ID exists in database->https://www.baeldung.com/spring-data-exists-query

        String getQueryStatement = "select  CorrectionPoints  as 'Correction Points', targetCorrectionPoints as 'Correction Points Target' from PhD_Candidate where ID="
                + docid + ";";

        db_prep_obj = db_con_obj.prepareStatement(getQueryStatement);
        ResultSet rs = db_prep_obj.executeQuery();

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next())
        {
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
            {
                Object object = rs.getObject(columnIndex);
                String col_name = metaData.getColumnName(columnIndex);
                return col_name+":" +object.toString()+"\t";
            }
        }
        return "";
    }
    public String getSurveilancePointsFromDB(String docid) throws SQLException
    {
        //TODO: Validate if ID exists in database->https://www.baeldung.com/spring-data-exists-query

        String getQueryStatement = "select  SurveilancePoints as 'Surveilance Points', targetSurveilancePoints as 'Surveilance Pointss Target' from PhD_Candidate where ID="
                + docid + ";";

        db_prep_obj = db_con_obj.prepareStatement(getQueryStatement);
        ResultSet rs = db_prep_obj.executeQuery();

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next())
        {
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
            {
                Object object = rs.getObject(columnIndex);
                String col_name = metaData.getColumnName(columnIndex);
                return col_name+":" +object.toString()+"\t";
            }
        }
        return "";
    }
    public String getTeachingPointsFromDB(String docid) throws SQLException
    {
        //TODO: Validate if ID exists in database->https://www.baeldung.com/spring-data-exists-query

        String getQueryStatement = "select  TeachingPoints as 'Teaching Points', targetTeachingPoints as 'Teaching Points Target' from PhD_Candidate where ID="
                + docid + ";";

        db_prep_obj = db_con_obj.prepareStatement(getQueryStatement);
        ResultSet rs = db_prep_obj.executeQuery();

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next())
        {
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
            {
                Object object = rs.getObject(columnIndex);
                String col_name = metaData.getColumnName(columnIndex);
                return col_name+":" +object.toString()+"\t";
            }
        }
        return "";
    }

}
