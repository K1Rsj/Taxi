package ua.finalproject.model.dao.hibernate.session;

import com.mysql.cj.jdbc.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.MySQL5Dialect;
import ua.finalproject.constants.db.DbConfig;
import ua.finalproject.model.dao.hibernate.converter.CarStateConverter;
import ua.finalproject.model.dao.hibernate.converter.UserRoleConverter;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.full.CarType;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.entities.full.User;
import ua.finalproject.util.PropertyManager;

import java.util.Properties;

public class HibernateSession {

    private static final SessionFactory SESSION_FACTORY = initialize();

    public static Session getSession() {
        return SESSION_FACTORY.openSession();
    }

    private static SessionFactory initialize() {
        Properties jpaProps = new Properties();
        jpaProps.put(Environment.DRIVER, Driver.class.getCanonicalName());
        jpaProps.put(Environment.DIALECT, MySQL5Dialect.class.getCanonicalName());
        jpaProps.put(Environment.URL, PropertyManager.CONFIG.getString(DbConfig.DB_URL));
        jpaProps.put(Environment.USER, PropertyManager.CONFIG.getString(DbConfig.DB_USER));
        jpaProps.put(Environment.PASS, PropertyManager.CONFIG.getString(DbConfig.DB_PASSWORD));
        jpaProps.put(Environment.HBM2DDL_AUTO, PropertyManager.CONFIG.getString(DbConfig.DB_DLL_VALIDATE));
        jpaProps.put(Environment.SHOW_SQL, true);

        Configuration config = new Configuration()
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(CarType.class)
                .addProperties(jpaProps);
        config.addAttributeConverter(CarStateConverter.class);
        config.addAttributeConverter(UserRoleConverter.class);

        return config.buildSessionFactory();
    }
}
