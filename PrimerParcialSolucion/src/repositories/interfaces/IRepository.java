package repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
    List<T> findAll()throws SQLException;
    T findById(int id)throws SQLException;
}
