import java.net.SocketOption;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionDB {
    public static Connection getMySQLConnection() throws SQLException {
        Connection conn = null;
        //Connection String: Chuỗi kết nối (thông tin ) đến csdl;
        String connURL = "jdbc:mysql://localhost:3308/fptaptech2";
        conn = DriverManager.getConnection(connURL,"root","");
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        if(getMySQLConnection()!=null){
            System.out.println("Kết nối thành công");
        }
    }
}