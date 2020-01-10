package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseUtils {
	private static Connection conn;
	private static String driver;
	private static String user;
	private static String password;
	private static String url;

	private static void initProperties() {
		Properties properties = new Properties();
		try {
			// 加载本地配置文件
			properties.load(new FileInputStream("src/db.properties"));
			// 得到解析后的配置信息
			driver = properties.getProperty("jdbc.driver");
			user = properties.getProperty("jdbc.user");
			password = properties.getProperty("jdbc.password");
			url = properties.getProperty("jdbc.url");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static {
		// 加载配置文件，初始化连接信息
		initProperties();
		try {
			// 1.加载驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("驱动加载失败！");
		}
		// 得到数据库连接
		getConnection();
	}

	private DatabaseUtils() {

	}

	public static synchronized Connection getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}

	// 通用增删改
	public static int executeUpdate(String sql, Object... params) {
		return executeUpdate(sql, false, params);
	}

	public static int executeInsert(String sql, Object... params) {
		return executeUpdate(sql, true, params);
	}

	private static int executeUpdate(String sql, boolean isKeys, Object... params) {
		if (conn == null) {
			getConnection();
		}
		PreparedStatement ps = null;
		ResultSet set = null;
		try {
			// 获取操作数据库的句柄
			if (isKeys && sql.toLowerCase().startsWith("insert")) {
				ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			} else {
				ps = conn.prepareStatement(sql);
			}
			// 设置参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 执行操作
			if (isKeys && sql.toLowerCase().startsWith("insert")) {
				int r = ps.executeUpdate();
				if (r > 0) {
					set = ps.getGeneratedKeys();
					while (set.next()) {
						return set.getInt(Statement.RETURN_GENERATED_KEYS);
					}
				}
			} else {
				return ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (set != null) {
					set.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return 0;
	}

	public static <T> T executeQueryOne(Class<T> clazz, String sql, Object... params) {
		List<T> ts = executeQuery(clazz, sql, params);
		return ts.size() > 0 ? ts.get(0) : null;
	}

	public static <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) {
		List<T> result = new ArrayList<>();
		// 判定数据库连接是否创建完成！
		if (conn == null) {
			getConnection();
		}

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 获取操作句柄
			ps = conn.prepareStatement(sql);
			// 设置参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 执行查询
			rs = ps.executeQuery();
			// 得到元数据
			ResultSetMetaData metaData = rs.getMetaData();
			// 循环取出记录
			while (rs.next()) {
				// 创建对象保存当前记录
				T t = clazz.newInstance();
				// 循环封装数据
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					// 取出当前字段
					String column = metaData.getColumnLabel(i).toLowerCase();
					// 把字段命名规则（下划线）转换为属性命名规则（小驼峰命名法）
					String[] strs = column.split("_");
					String fieldName = "";
					for (int j = 0; j < strs.length; j++) {
						if (j == 0) {
							fieldName += strs[j];
						} else {
							fieldName += strs[j].substring(0, 1).toUpperCase() + strs[j].substring(1);
						}
					}

					String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					// 获取当前字段对象
					Field field = clazz.getDeclaredField(fieldName);
					// 使用set方法设置参数
					Method method = clazz.getDeclaredMethod(methodName, field.getType());
					// 调用方法
					method.invoke(t, rs.getObject(column));
				}
				result.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
