package utils;

import data.Transactions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MySqlUtil {

    private JdbcTemplate jdbcTemplate;

    private DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/bankademo?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSource.setUsername(System.getenv("DB_USERNAME"));
        dataSource.setPassword(System.getenv("DB_PASSWORD"));
        return dataSource;
    }



    private static class TransactionsRowMapper implements RowMapper<Transactions> {
        @Override
        public Transactions mapRow(ResultSet resultSet, int i) throws SQLException {
            Transactions transactions = new Transactions();
            transactions.setId(resultSet.getLong("id"));
            double amount = parseAmount(resultSet.getString("amount"));
            transactions.setAmount(amount);
            transactions.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());
            transactions.setNote(resultSet.getString("note"));
            transactions.setUserSourceId(resultSet.getLong("user_source_id"));
            transactions.setUserTargetId(resultSet.getLong("user_target_id"));
            return transactions;
        }
    }

    private static double parseAmount(String amountString) {
        int intValue = Integer.parseInt(amountString);
        return (double) intValue / 100;
    }


    public Transactions getAllMoneyTransfers() {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "SELECT * FROM transaction ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new TransactionsRowMapper());
    }

    public int gerRandomUserId() {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "SELECT id FROM user";
        List<Integer> listOfIds = jdbcTemplate.queryForList(sql, Integer.class);
        return listOfIds.stream().collect(Utils.toShuffledList()).get(0);
    }

    public int getRandomNotUsedUserId() {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "SELECT id FROM user ORDER BY id DESC LIMIT 1";
        int lastId = jdbcTemplate.queryForObject(sql, Integer.class);
        return lastId + 1;
    }

}
