package com.gongsibao.db;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.netsharp.core.Column;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.dataccess.DataAccessFactory;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.util.StringManager;

public class Tools {

	private String prefix = "yj_";
	private String pakcageName = "com.gongsibao.entity.yj";
	private String indent = "    ";
	private String dir = "E:/yj";

	@Test
	public void queryTable() throws IOException {

		IDataAccess dao = DataAccessFactory.create();

		DataTable table = dao.executeTable(
				"select * from information_schema.tables t where t.table_schema='gsb' and table_name like '" + prefix
						+ "%'",
				null);

		for (IRow row : table) {
			System.out.println(row.get("TABLE_NAME").toString()+"----------------------------------------");
			this.generate(row.get("TABLE_NAME").toString());
		}

	}

	public void generate(String tableName) throws IOException {

		String cmdText = "select * from " + tableName + " limit 1";
		IDataAccess dao = DataAccessFactory.create();
		DataTable table = dao.executeTable(cmdText, null);

		this.generateEntity(table);
		this.generateService(table);

	}

	private void generateEntity(DataTable table) throws IOException {

		String tableName = table.getTableName();

		StringBuilder builder = new StringBuilder();
		builder.append("package " + this.pakcageName + ";" + StringManager.NewLine);
		builder.append(StringManager.NewLine);
		builder.append("import com.gongsibao.entity.BaseEntity;" + StringManager.NewLine);
		builder.append("import java.sql.Timestamp;" + StringManager.NewLine);
		builder.append("import org.netsharp.core.annotations.Column;" + StringManager.NewLine);
		builder.append("import org.netsharp.core.annotations.Table;" + StringManager.NewLine);
		builder.append(StringManager.NewLine);
		builder.append("@Table(name=\"" + tableName + "\")" + StringManager.NewLine);
		builder.append("public class " + this.toClass(tableName) + " extends BaseEntity {" + StringManager.NewLine);

		for (Column column : table.getColumns()) {
			if (column.getColumnName().equals("pkid")) {
				continue;
			}
			String field = this.toHump(column.getColumnName());
			String property = this.toUpper(field);

			if (!StringManager.equals(field, column.getColumnName())) {
				builder.append( this.indent + "@Column(name=\"" + column.getColumnName() + "\",header=\""+ property +"\")" + StringManager.NewLine);
			}
			else {
				builder.append( this.indent + "@Column(header=\""+ this.toHump(column.getColumnName())+"\")" + StringManager.NewLine);
			}

			builder.append(this.indent + "private " + column.getType().getSimpleName() + " " + this.toHump(column.getColumnName()) + ";" + StringManager.NewLine);
		}

		builder.append(StringManager.NewLine);

		for (Column column : table.getColumns()) {
			if (column.getColumnName().equals("pkid")) {
				continue;
			}

			String field = this.toHump(column.getColumnName());
			String property = this.toUpper(field);

			builder.append(this.indent + "public " + column.getType().getSimpleName() + " get" + property + "() {"
					+ StringManager.NewLine);
			builder.append(this.indent + this.indent + "return " + field + ";" + StringManager.NewLine);
			builder.append(this.indent + "}" + StringManager.NewLine);

			builder.append(this.indent + "public void set" + property + "("
					+ column.getType().getSimpleName() + " " + field + ") {" + StringManager.NewLine);
			builder.append(this.indent + this.indent + "this." + field + " = " + field + ";" + StringManager.NewLine);
			builder.append(this.indent + "}" + StringManager.NewLine);
		}

		builder.append("}");

		FileWriter w = new FileWriter(this.dir + "/entity/" + this.toClass(tableName) + ".java");

		w.write(builder.toString());
		w.flush();
		w.close();
	}

	private void generateService(DataTable table) throws IOException {

		String tableName = table.getTableName();
		String entity = this.toClass(tableName);

		StringBuilder builder = new StringBuilder();
		builder.append("package " + this.pakcageName + ".base;" + StringManager.NewLine);
		builder.append(StringManager.NewLine);
		builder.append("import " + this.pakcageName + ".entity." + entity + ";" + StringManager.NewLine);
		builder.append("import org.netsharp.base.IPersistableService;" + StringManager.NewLine);
		builder.append(StringManager.NewLine);
		builder.append("public interface I" + entity + "Service extends IPersistableService<" + entity + "> {"
				+ StringManager.NewLine);

		builder.append("}");

		FileWriter w = new FileWriter(this.dir + "/base/I" + this.toClass(tableName) + "Service.java");

		w.write(builder.toString());
		w.flush();
		w.close();

		//-----------------------------
		
		builder = new StringBuilder();
		builder.append("package " + this.pakcageName + ".service;" + StringManager.NewLine);
		builder.append(StringManager.NewLine);
		builder.append("import " + this.pakcageName + ".entity." + entity + ";" + StringManager.NewLine);
		builder.append("import " + this.pakcageName + ".base.I" + entity + "Service;" + StringManager.NewLine);
		builder.append("import org.netsharp.communication.Service;" + StringManager.NewLine);
		builder.append("import org.netsharp.service.PersistableService;" + StringManager.NewLine);
		builder.append(StringManager.NewLine);
		builder.append("@Service" + StringManager.NewLine);
		builder.append("public class " + this.toClass(tableName) + "Service extends PersistableService<" + entity
				+ "> implements I" + entity + "Service {" + StringManager.NewLine);

		builder.append(StringManager.NewLine);
		builder.append(this.indent + "public " + entity + "Service(){" + StringManager.NewLine);
		builder.append(this.indent +this.indent + "super();" + StringManager.NewLine);
		builder.append(this.indent +this.indent + "this.type=" + entity + ".class;" + StringManager.NewLine);
		builder.append(this.indent + "}" + StringManager.NewLine);

		builder.append("}");

		w = new FileWriter(this.dir + "/service/" + this.toClass(tableName) + "Service.java");

		w.write(builder.toString());
		w.flush();
		w.close();
	}

	private String toClass(String tableName) {
		tableName = tableName.substring(this.prefix.length());
		tableName = this.toHump(tableName);
		return this.toUpper(tableName);
	}

	private String toUpper(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	private String toHump(String columnName) {

		String[] items = columnName.split("_");
		String field = items[0];
		for (int i = 1; i < items.length; i++) {
			field += this.toUpper(items[i]);
		}

		return field;
	}
}
