package gr.hua.dit.ds.dsassignment030.Users;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static gr.hua.dit.ds.dsassignment030.config.MySQL.db_con_obj;
import static gr.hua.dit.ds.dsassignment030.config.MySQL.db_prep_obj;

public class Admin {

    @Autowired
    DataSource dataSource;



    public String getDocPoints(String docid) throws SQLException
    {
        //TODO: Validate if ID exists in database->https://www.baeldung.com/spring-data-exists-query
        //->https://wkrzywiec.medium.com/how-to-check-if-user-exist-in-database-using-hibernate-validator-eab110429a6

        String getQueryStatementAdmins = "select sum(SurveilancePoints + CorrectionPoints + TeachingPoints) as 'Total' from PhD_Candidate	where ID="
                + docid + ";";
        db_prep_obj = db_con_obj.prepareStatement(getQueryStatementAdmins);
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

}
