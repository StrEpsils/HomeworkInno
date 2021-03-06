package ru.cherkasov.w3d2.dao;

import ru.cherkasov.w3d2.entity.Subject;

import java.sql.SQLException;
import java.util.Collection;

public interface SubjectDAO {
    /**
     * Получить список всех предметов
     *
     * @return список всех предметов
     * @throws SQLException ошибка работы с БД
     */
    Collection<Subject> getAllSubjects() throws SQLException;

    /**
     * Сохранить предмет в БД
     * @param subject предмет
     * @throws SQLException ошибка работы с БД
     */
    void createSubject(Subject subject) throws SQLException;

    /**
     * Изменить предмет в БД
     * @param subject предмет
     * @throws SQLException ошибка работы с БД
     */
    void updateSubject(Subject subject) throws SQLException;

    /**
     * Удалить предмет из БД
     * @param subject предмет
     * @throws SQLException ошибка работы с БД
     */
    void deleteSubject(Subject subject) throws SQLException;
}
