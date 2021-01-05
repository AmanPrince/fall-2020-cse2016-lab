package sample.db_access;

import sample.utils.CommonConstant;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionImpl implements DBConnection {
    @Override
    public Connection openConnection() {
        return getConnection();
    }

    @Override
    public void closeConnection(Connection connection) {
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // url: jdbc:mysql://localhost:3306/DB_NAME
            return DriverManager.getConnection("jdbc:mysql://"+ CommonConstant.IP_ADDRESS+":"+CommonConstant.PORT+"/"+CommonConstant.DB_NAME,
                    CommonConstant.MYSQL_USERNAME,
                    CommonConstant.MYSQL_PASSWORD);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }
}
