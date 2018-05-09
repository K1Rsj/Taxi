package ua.finalproject.dao.mapper;


import ua.finalproject.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        String firstName = resultSet.getString("first_name");
        String secondName = resultSet.getString("second_name");
        String phoneNumber = resultSet.getString("phone_number");
        Long moneySpent = resultSet.getLong("money_spent");
        User.Role role = UtilDao.parseUserRole(resultSet.getString("role"));

        return User.builder()
                .id(id)
                .login(login)
                .password(password)
                .email(email)
                .firstName(firstName)
                .secondName(secondName)
                .phoneNumber(phoneNumber)
                .moneySpent(moneySpent)
                .role(role)
                .build();
    }

    @Override
    public void setValuesForQuery(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getFirstName());
        preparedStatement.setString(5, user.getSecondName());
        preparedStatement.setString(6, user.getPhoneNumber());
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        return null;
    }
}
