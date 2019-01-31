package ru.cherkasov.w3d2.dao;

import ru.cherkasov.w3d2.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    private static final String INSERT_INTO_SUBJECT_DESCRIPTION_VALUES_RETURNING_SUBJECT_ID = "insert into subject(description) values (?) returning subject_id";
    private static final String UPDATE_SUBJECT_SET_DESCRIPTION_WHERE_SUBJECT_ID = "update subject set description = ? where subject_id = ?";
    private static final String DELETE_FROM_SUBJECT_WHERE_SUBJECT_ID = "delete from subject where subject_id = ?";
    private static final String SELECT_FROM_SUBJECT = "select * from subject";

    private final Connection connection;

    public SubjectDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Получаем весь список предметов
     * @return
     * @throws SQLException
     */
    @Override
    public Collection<Subject> getAllSubjects() throws SQLException {
        List<Subject> subjectList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_FROM_SUBJECT)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("subject_id"));
                subject.setDescription(resultSet.getString("description"));
                subjectList.add(subject);
            }
        }
        return subjectList;
    }

    /**
     * Создаем предмет
     * @param subject предмет
     * @throws SQLException
     */
    @Override
    public void createSubject(Subject subject) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(INSERT_INTO_SUBJECT_DESCRIPTION_VALUES_RETURNING_SUBJECT_ID)){
            statement.setString(1, subject.getDescription());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                subject.setId(resultSet.getInt("subject_id"));
            }
        }
    }

    /**
     * Обновляем предмет
     * @param subject предмет
     * @throws SQLException ошибка выполнения запроса
     */
    @Override
    public void updateSubject(Subject subject) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT_SET_DESCRIPTION_WHERE_SUBJECT_ID)){
            statement.setString(1,subject.getDescription());
            statement.setInt(2, subject.getId());
            statement.execute();
        }
    }

    /**
     * Удаляем предмет
     * @param subject предмет
     * @throws SQLException ошибка выполнения запроса
     */
    @Override
    public void deleteSubject(Subject subject) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_FROM_SUBJECT_WHERE_SUBJECT_ID)){
            statement.setInt(1, subject.getId());
            statement.execute();
        }
    }
}
