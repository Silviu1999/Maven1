import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //conectarea la baza de date
        //schema dbcloud-se refera la baza de date
        //username -auth
        //password-auth
        // http://www.facebook.com:p
        String conncetionurl = "jdbc:mysql://127.0.0.1:3306/shop";
        String username = "root";
        String password = "Crocodil_123";

        try {
            Connection connection = DriverManager.getConnection(conncetionurl, username, password);

           // insert(connection,"user5","Georghe","Coroi");
            getById(1,connection);
            getById(4,connection);
            getById(6,connection);
            System.out.println(delete(connection,1));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public  static void insert(Connection connection,String username,String last_name,String first_name) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("Insert INTO customers (username,first_name,last_name) VALUES (?,?,?)");
        ps.setString(1, username);
        ps.setString(2, last_name);
        ps.setString(3, first_name);
        ps.execute();
        System.out.println(ps.getUpdateCount());


    }
    public static void getById(int id, Connection connection) throws SQLException {


        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select * from customers where id=" + id);

        while (rs.next()) {
            System.out.println("id:" + rs.getInt("id"));
            System.out.println("username:" + rs.getString("username"));
            System.out.println("First Name:" + rs.getString("first_name"));
            System.out.println("Last Name:" + rs.getString("last_name"));
        }

    }

    public  void getAll(Connection connection,String table)throws SQLException{
        Statement statement=connection.createStatement();
        ResultSet rs=statement.executeQuery("Select *from "+table);
        ResultSetMetaData resultSetMetaData=rs.getMetaData();
        for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
            System.out.println(resultSetMetaData.getColumnLabel(i));
            System.out.println(resultSetMetaData.getColumnTypeName(i));
        }

        while (rs.next()) {

            System.out.println("id:" + rs.getInt("id"));
            System.out.println("username:" + rs.getString("username"));
            System.out.println("First Name:" + rs.getString("first_name"));
            System.out.println("Last Name:" + rs.getString("last_name"));
        }


    }

    public static int delete (Connection connection,int id) throws SQLException{
        Statement statement=connection.createStatement();
        return  statement.executeUpdate("Delete from customers where id="+id);
    }
}
