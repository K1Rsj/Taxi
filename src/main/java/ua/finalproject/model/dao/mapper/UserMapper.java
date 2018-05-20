package ua.finalproject.model.dao.mapper;


import ua.finalproject.constants.db.TableColumnNames;
import ua.finalproject.model.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(TableColumnNames.ID_USER);
        String login = resultSet.getString(TableColumnNames.LOGIN);
        String password = resultSet.getString(TableColumnNames.PASSWORD);
        String email = resultSet.getString(TableColumnNames.EMAIL);
        String firstName = resultSet.getString(TableColumnNames.FIRST_NAME);
        String secondName = resultSet.getString(TableColumnNames.SECOND_NAME);
        String phoneNumber = resultSet.getString(TableColumnNames.PHONE_NUMBER);
        Long moneySpent = resultSet.getLong(TableColumnNames.MONEY_SPENT);
        User.Role role = UtilDao.parseUserRole(resultSet.getString(TableColumnNames.ROLE));

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
}
