package repositories.interfaces;

import java.sql.SQLException;

public interface IABMRepository<T> extends IRepository<T> {
    void create(T type) throws SQLException;
    void update(T type) throws SQLException;
    void delete(int id) throws SQLException;
}
