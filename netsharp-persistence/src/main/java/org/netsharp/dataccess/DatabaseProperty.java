package org.netsharp.dataccess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.dic.DatabaseType;
import org.netsharp.util.PropertyConfigurer;

public class DatabaseProperty {

	static final Log logger = LogFactory.getLog(DatabaseProperty.class);

	private String driverClass;
	private String jdbcUrl;
	private String username;
	private String password;
	private String database;
	private DatabaseType type = DatabaseType.MySql;

	public DatabaseType getType() {
		return type;
	}

	@Override
	public String toString() {
		return this.jdbcUrl;
	}

	private static DatabaseProperty dbp;

	public static DatabaseProperty defaultDatabaseProperty() {

		if (dbp == null) {
			PropertyConfigurer conf = PropertyConfigurer.newInstance("/conf/database.properties");
			dbp = new DatabaseProperty();

			dbp.setDriverClass(conf.get("jdbc.driverClass"));
			dbp.setJdbcUrl(conf.get("jdbc.jdbcUrl"));
			dbp.setUsername(conf.get("jdbc.username"));
//			dbp.setPassword(Base64Encodings.decode(conf.get("jdbc.password")));
			dbp.setPassword(conf.get("jdbc.password"));
			dbp.setDatabase(conf.get("jdbc.database"));

			logger.debug("jdbc.jdbcUrl :" + conf.get("jdbc.jdbcUrl"));
		}

		return dbp;
	}

	public static void setDefaultDatabaseProperty(DatabaseProperty databaseProperty) {
		dbp = databaseProperty;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
}
