import java.sql.*;

public class ConnectorJDBC {
    static final String USER = "root";
    static final String PASSWORD = "KindGeek!";
    static final String URL = "jdbc:mysql://localhost:3306/ryanairDB?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false";
    static final String CREATE_USER_TABLE = "create table user(id bigint primary key auto_increment, name varchar(255) not null, password varchar(255) not null)";
    static final String INSERT_USER = "insert into user(name, password) values ('%s', '%s')";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        Statement statement = connection.createStatement();
//        statement.execute(CREATE_USER_TABLE);

//        Scanner input = new Scanner(System.in);
//        for (int i = 0; i < 3; i++) {
//            System.out.println("input name and password");
//            statement.execute(String.format(INSERT_USER, input.next(), input.next()));
//        }

        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("id");
            String password = resultSet.getString("password");
            System.out.println(id + ". " + name + " " + password);
        }

        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where name like ? and password = ?");
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Input name for search:");
//        String partOfName = sc.next();
//        System.out.println("Input password for search: ");
//        String inputPassword = sc.next();
//        preparedStatement.setString(1, "%" + partOfName + "%");

        preparedStatement.setString(1, "%e%");
        preparedStatement.setString(2, "2222");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            System.out.println(id + ". " + name + " " + password);
        }

        statement.close();
        connection.close();
    }
}