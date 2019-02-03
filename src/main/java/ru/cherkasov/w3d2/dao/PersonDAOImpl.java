package ru.cherkasov.w3d2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cherkasov.w3d2.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Реализация интерфейса студент
 */

public class PersonDAOImpl implements ru.cherkasov.w3d2.dao.PersonDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAOImpl.class);

    private static final String SELECT_FROM_PERSON = "select * from person";
    private static final String UPDATE_PERSON_SET_NAME_BIRTHDATE_WHERE_PERSON_ID = "update person set name = ?, birthdate = ? where person_id = ?";
    private static final String INSERT_INTO_PERSON_NAME_BIRTHDATE_VALUES_RETURNING_PERSON_ID = "insert into person(name, birthdate) values (?,?) returning person_id";
    private static final String DELETE_FROM_PERSON_WHERE_PERSON_ID = "delete from person where person_id = ?";

    private final Connection connection;

    public PersonDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Получаем список всех студентов
     * @return список студентов
     * @throws SQLException ошибка выполнения запроса
     */
    @Override
    public Collection<Person> getAllPersons(){
        LOGGER.info("Выполняем запрос на получения списка студентов.");
        List<Person> personList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_FROM_PERSON)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("person_id"));
                person.setName(resultSet.getString("name"));
                person.setBirthDate(resultSet.getDate("birthdate").getTime());
                personList.add(person);
            }
        }catch (SQLException e){
            LOGGER.error("Ошибка получения списка студентов {}", e);
        }
        LOGGER.debug("Получено {} студентов", personList.size());
        return personList;
    }

    /**
     * Сохраняем студента в БД
     * @param person объект-студент
     * @throws SQLException ошибка выполнения запроса
     */
    @Override
    public void createPerson(Person person) {
        try(PreparedStatement statement = connection.prepareStatement(INSERT_INTO_PERSON_NAME_BIRTHDATE_VALUES_RETURNING_PERSON_ID)){
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            ResultSet res = statement.executeQuery();
            if (res.next()){
                person.setId(res.getInt("person_id"));
                LOGGER.info("Добавление нового студента в базу данных {}", person.toString());
                LOGGER.debug("Сохранен студент: {}", person.toString());
            }
        } catch (SQLException e){
            LOGGER.error("Ошибка добавления нового студента {}", e);
        }
    }

    /**
     * Обновляем студента
     * @param person объект-студент
     * @throws SQLException ошибка выполнения запроса
     */
    @Override
    public void updatePerson(Person person) {
        LOGGER.info("Обновление данных студента {}", person.getName());
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON_SET_NAME_BIRTHDATE_WHERE_PERSON_ID)) {
            statement.setString(1, person.getName());
            statement.setDate(2, new Date(person.getBirthDate()));
            statement.setInt(3, person.getId());
            statement.execute();
            LOGGER.debug("Обновление данных студента {}", person.toString());
        } catch (SQLException e){
            LOGGER.error("Ошибка обновления студента {}", e.getMessage());
        }
    }

    /**
     * Удаляем студента
     * @param person объект-студент
     * @throws SQLException ошибка выполения запроса
     */
    @Override
    public void deletePerson(Person person) {
        LOGGER.error("Запрос на удаление студента: {}", person.getName());
        try(PreparedStatement statement = connection.prepareStatement(DELETE_FROM_PERSON_WHERE_PERSON_ID)){
            statement.setInt(1, person.getId());
            statement.execute();
            LOGGER.debug("Удаление студента {}", person.toString());
        }catch (SQLException e){
            LOGGER.error("Ошибка удаление студента {}", e);
        }
    }
}
