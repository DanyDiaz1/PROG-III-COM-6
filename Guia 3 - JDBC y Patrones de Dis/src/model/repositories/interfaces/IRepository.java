package model.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IRepository <T>{
    void save(T t) throws SQLException;
    List<T> findAll() throws SQLException;
    Optional<T> findById(Integer id) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
}
