package ru.cherkasov.w3d2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cherkasov.w3d2.entity.Person;
import ru.cherkasov.w3d2.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDAOImpl.class);

    private static final String GET_PERSON_BY_SUBJECT = "SELECT DISTINCT course.person_id, name,birthdate FROM course LEFT JOIN person ON course.person_id = person.person_id WHERE subject_id = ?";
    private static final String GET_SUBJECT_BY_PERSON = "SELECT DISTINCT s.subject_id,description FROM course LEFT JOIN subject s on course.subject_id = s.subject_id WHERE person_id = ?";
    private static final String INSERT_INTO_COURSE_PERSON_ID_SUBJECT_ID_VALUES = "INSERT INTO course(person_id, subject_id) VALUES (?,?)";

    private final Connection connection;

    public CourseDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Получить список всех студентов, изучающих определенный предмет
     *
     * @param subject предмет
     * @return список студентов
     * @throws SQLException ошибка выполнения запроса
     */
    @Override
    public Collection<Person> getPersonsBySubject(Subject subject) throws SQLException {
        List<Person> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_PERSON_BY_SUBJECT)) {
            statement.setInt(1, subject.getId());
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Person p = new Person();
                p.setId(res.getInt("person_id"));
                p.setName(res.getString("name"));
                p.setBirthDate(res.getDate("birthdate").getTime());
                result.add(p);
            }
        }
        return result;
    }

    /**
     * Получить список предметов, изучаемых данным студентом
     *
     * @param person студент
     * @return список предметов
     * @throws SQLException ошибка выполения запроса
     */
    @Override
    public Collection<Subject> getSubjectsByPerson(Person person) throws SQLException {
        List<Subject> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_BY_PERSON)) {
            statement.setInt(1, person.getId());
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Subject s = new Subject();
                s.setId(res.getInt("subject_id"));
                s.setDescription(res.getString("description"));
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Записать данного студента, на несколько предметов
     *
     * @param person   студент
     * @param subjects список предметов
     * @throws SQLException ошибка выполнения запроса
     */
    @Override
    public void linkToCourse(Person person, Subject... subjects) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_COURSE_PERSON_ID_SUBJECT_ID_VALUES)) {
            for (Subject subj : subjects) {
                statement.setInt(1, person.getId());
                statement.setInt(2, subj.getId());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    /**
     * Записать на данный предмет нескольких студентов
     *
     * @param subject предмет
     * @param persons список студентов
     * @throws SQLException Ошибка выполнения запроса
     */
    @Override
    public void linkToCourse(Subject subject, Person... persons) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_COURSE_PERSON_ID_SUBJECT_ID_VALUES)) {
            for (Person p : persons) {
                statement.setInt(1, p.getId());
                statement.setInt(2, subject.getId());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }
}
