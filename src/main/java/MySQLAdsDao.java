import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MySQLAdsDao implements Ads { //implements Ads.java interface list methods for retrieving ads and displaying them
    private Connection connection;
    public MySQLAdsDao(Config config) {
        Driver driver;
        try {
            driver = new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Ad> all() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ads");
        List<Ad> ads = new ArrayList<>();
        while (resultSet.next()) { // move to next available row
            //Transform the result set into List of models (User)
            ads.add(
                    new Ad(
                            resultSet.getLong("id"),
                            resultSet.getLong("user_id"),
                            resultSet.getString("title"),
                            resultSet.getString("description")
                    )
            );
        }
        return ads;
    }
    @Override
    public Long insert(Ad ad) throws SQLException {
        String query = String.format(
                "INSERT INTO ads(user_id, title, description) VALUES (%d, '%s', '%s')",
                1, //ad.getUserId(),
                ad.getTitle(),
                ad.getDescription()
        );
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        if(rs.next()){
            return rs.getLong(1);
        }
        return null;
    }
}
