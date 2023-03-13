package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

import javax.swing.table.DefaultTableModel;

/**
 * aceasta clasa este folosita pentru e crea metode de tipul T
 * @param <T>
 */
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * creeaza Select Query pentru a ne fi mai usor sa apelam pentru baza de date
	 * @param field
	 * @return returneaza un string cu select query
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	/**
	 * creeaza Select All Query pentru a ne fi mai usor sa apelam pentru baza de date
	 * @return returneaza un string ce contine Select All Query
	 */
	private String createSelectAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());

		return sb.toString();
	}

	/**
	 * creeaza Insert Query pentru a ne fi mai usor sa apelam pentru baza de date
	 * @param t
	 * @return returneaza un string care contine Insert Query
	 * @throws IllegalAccessException
	 */
	private String createInsertQuery(T t) throws IllegalAccessException {
		/*INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);*/
		String sb;
		Field[] fields = type.getDeclaredFields();
		sb = "INSERT INTO " + type.getSimpleName() + " (";
		for (int i = 1; i < fields.length - 1; i++) {
			fields[i].setAccessible(true);
			sb += fields[i].getName() + ", ";
		}
		fields[fields.length - 1].setAccessible(true);
		sb += fields[fields.length - 1].getName() + ") VALUES (";
		for (int i = 1; i < fields.length - 1; i++) {
			fields[i].setAccessible(true);
			sb += "'" + fields[i].get(t) + "', ";
		}
		fields[fields.length - 1].setAccessible(true);
		sb += "'" + fields[fields.length - 1].get(t) + "')";
		return sb;
	}

	/**
	 * creeaza Delete Query pentru a ne fi mai usor sa apelam pentru baza de date
	 * @param id
	 * @return returneaza un string ce contine delete query in functie de id
	 */
	private String createDeleteQuery(int id) {

		String sb;
		Field[] fields = type.getDeclaredFields();
		sb = "DELETE FROM " + type.getSimpleName() + " WHERE " + fields[0].getName() + " = " + id;
		return sb;
	}

	/**
	 * creeaza Update Query pentru a ne fi mai usor sa apelam pentru baza de date
	 * @param t
	 * @param id
	 * @return returneaza un string ce contine update query in functie de id
	 * @throws IllegalAccessException
	 */
	private String createUpdateQuery(T t, int id) throws IllegalAccessException {
		String sb;
		Field[] fields = type.getDeclaredFields();
		sb = "UPDATE " + type.getSimpleName() + " SET ";
		for (int i = 1; i < fields.length - 1; i++) {
			fields[i].setAccessible(true);
			sb += fields[i].getName() + " = '" + fields[i].get(t) + "', ";
		}
		fields[fields.length - 1].setAccessible(true);
		fields[0].setAccessible(true);
		sb += fields[fields.length - 1].getName() + " = '" + fields[fields.length - 1].get(t) + "' WHERE " + fields[0].getName() + " = " + id;

		return sb;
	}

	/**
	 * aceasta metoda creeaza o lista care contine toate obiectele de tipul T
	 * @return returneaza lista creata
	 */
	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectAllQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * aceasta metoda gaseste un obiect in functie de id
	 * @param id
	 * @return returneaza obiectul gasit
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * aceasta metoda creeaza obiecte de tip T in functie de parametrul dat
	 * @param resultSet
	 * @return returneaza o lista de obiecte de tipul T
	 */
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T) ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * aceasta metoda este echivalentul insertului din mysql in functie de obiectul t
	 * @param t
	 * @throws IllegalAccessException
	 */
	public void insert(T t) throws IllegalAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createInsertQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int k = statement.executeUpdate();


		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

	}

	/**
	 * aceasta metoda este echivalentul update ului din mysql in functie de obiectul T, dar si de id
	 * @param t
	 * @param id
	 * @throws IllegalAccessException
	 */
	public void update(T t, int id) throws IllegalAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createUpdateQuery(t, id);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int k = statement.executeUpdate();


		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

	}

	/**
	 * aceasta metoda este echivalenta cu delete-ul din mysql in functie de id
	 * @param id
	 */
	public void delete(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createDeleteQuery(id);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int k = statement.executeUpdate();


		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

	}

	/**
	 * aceasta metoda creeaza un tabel ce contine toate elementele unuui obiect
	 * @param list
	 * @return un tabel
	 * @throws IllegalAccessException
	 */
	public DefaultTableModel tableModel(List<T> list) throws IllegalAccessException {
		DefaultTableModel table = new DefaultTableModel();
		Field[] fields = type.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			table.addColumn(fields[i].getName());
		}

		for (T t : list) {
			Object[] linie = new Object[fields.length];
			for (int i = 0; i < fields.length; i++) {
				linie[i] = new Object();
				fields[i].setAccessible(true);
				linie[i] = fields[i].get(t);
			}
			table.addRow(linie);
		}

	return table;
	}
}
