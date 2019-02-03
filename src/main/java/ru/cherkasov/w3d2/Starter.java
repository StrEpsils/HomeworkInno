package ru.cherkasov.w3d2;

import ru.cherkasov.w3d2.dao.PersonDAO;
import ru.cherkasov.w3d2.dao.PersonDAOImpl;
import ru.cherkasov.w3d2.dao.SubjectDAO;
import ru.cherkasov.w3d2.dao.SubjectDAOImpl;
import ru.cherkasov.w3d2.entity.Person;
import ru.cherkasov.w3d2.entity.Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Starter {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String login = "inno";
        String pass = "polis";

//        Connection connection = DriverManager.getConnection(url, login, pass);

        try (Connection conn = DriverManager.getConnection(url, login, pass)) {
            PersonDAO daoPerson = new PersonDAOImpl(conn);
            createPersons(daoPerson);
            SubjectDAO daoSubject = new SubjectDAOImpl(conn);
            createSubjects(daoSubject);
        }
//        connection.close();
    }

    public static void createPersons(PersonDAO dao) throws SQLException {
        // Create Persons
        Person person = new Person();
        person.setName("John Snow");
        person.setBirthDate(System.currentTimeMillis());
        person.setName("John Snow Targarien");
        dao.createPerson(person);

        Person ned = new Person("Ned Stark", System.currentTimeMillis() - 3600 * 1000 * 24);
        dao.createPerson(ned);

        Person cate = new Person("Catelyn Tully", System.currentTimeMillis() - 3600 * 1000 * 24 * 2);
        dao.createPerson(cate);
    }

    public static void createSubjects(SubjectDAO dao) throws SQLException {
        Subject subj = new Subject();
        subj.setDescription("Game of Thrones");
        dao.createSubject(subj);

        Subject subj2 = new Subject();
        subj2.setDescription("War of five kings");
        dao.createSubject(subj2);

        Subject subj3 = new Subject();
        subj3.setDescription("Dance of the dragons");
        dao.createSubject(subj3);
    }

}
