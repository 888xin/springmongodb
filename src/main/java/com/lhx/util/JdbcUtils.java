package com.lhx.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lhx on 15-1-5 下午4:59
 *
 * @project anti
 * @package com.lifeix.anti.common.util
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public final class JdbcUtils {
    // 参数定义
    private static String url = "jdbc:mysql://localhost:3306/li"; // 数据库地址
    private static String username = "root"; // 数据库用户名
    private static String password = "465864"; // 数据库密码


    private JdbcUtils() {

    }

    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载出错!");
        }
    }

    // 获得连接
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(url, username, password);
//    }

    public static Connection getConnection(String url, String username, String password){
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    // 释放连接
    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close(); // 关闭结果集
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close(); // 关闭Statement
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close(); // 关闭连接
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
    }


    /**
     * 批量更新，用法有点复杂
     * @param connection 数据库连接
     * @param tableName  数据库表名
     * @param mapList 要批量更新的数据
     * @param args sql语句中的参数，最后一个参数要设为更新条件
     * @return 更新是否成功 true 成功
     *  Connection connection = getConnection("jdbc:mysql://localhost:3306/li", "root", "465864") ;
    List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = null ;
    for (int i = 0; i < 2; i++) {
    map = new HashMap<String, Object>();
    map.put("uid", i+1);
    map.put("username","hello");
    map.put("password","123");
    mapList.add(map);
    }
    update(connection, "users", mapList,"username", "password", "uid");
    ========================
    更新成：
    uid username    password
    1	hello	    123
    2	hello	    123
     *
     */
    public static boolean update(Connection connection, String tableName, List<Map<String, Object>> mapList, String... args){
        String sql = "update " + tableName + " set ";
        for (int i = 0; i < args.length; i++) {
            if (i != args.length-1){
                sql += args[i] + "=?," ;
            } else {
                sql = sql.substring(0,sql.length()-1);
                sql += " where " + args[i] + "=?" ;
            }
        }
        PreparedStatement ps = null;
        try {
            for (Map<String, Object> stringObjectMap : mapList) {
                ps = connection.prepareStatement(sql);
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, stringObjectMap.get(args[i]));
                }
                ps.executeUpdate();
            }
            return true ;
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        } finally {
            free(null, ps, null);
        }
    }


    /**
     * 更新单条记录
     * @param connection 连接对象
     * @param sql 有占位符的sql语句，有多少个问号，就要相应传入多少个condition
     * @param condition 对应sql语句中问号的参数
     * @return
     */
    public static boolean update(Connection connection, String sql, String...condition){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < condition.length; i++) {
                ps.setObject(i + 1, condition[i]);
            }
            int i = ps.executeUpdate();
            if (i != 0){
                return true ;
            } else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        } finally {
            free(null, ps, null);
        }
    }

    /**
     * 插入单条记录
     * @param connection 连接对象
     * @param sql 有占位符的sql语句，有多少个问号，就要相应传入多少个condition
     * @param condition 对应sql语句中问号的参数
     * @return
     */
    public static boolean insert(Connection connection, String sql, String...condition){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < condition.length; i++) {
                ps.setObject(i + 1, condition[i]);
            }
            int i = ps.executeUpdate();
            if (i != 0){
                return true ;
            } else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        } finally {
            free(null, ps, null);
        }
    }

    /**
     * 插入数据
     * @param connection 连接对象
     * @param tableName 数据库表
     * @param mapList 内容集合
     * @param args 字段名称，往哪些字段插入数据就要写出这个字段，并且内容集合里面要有该字段对应的数据
     * @return 是否成功插入数据
     */
    public static boolean insert(Connection connection, String tableName, List<Map<String, Object>> mapList, String... args){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into ").append(tableName).append("(");
        String q = "" ;
        for (int i = 0; i < args.length; i++) {
            if (i != args.length-1){
                stringBuilder.append(args[i]).append(",");
                q += "?,";
            } else {
                stringBuilder.append(args[i]);
                q += "?" ;
                stringBuilder.append(") values (") ;
                stringBuilder.append(q).append(")");
            }
        }
        PreparedStatement ps = null;
        try {
            for (Map<String, Object> stringObjectMap : mapList) {
                ps = connection.prepareStatement(stringBuilder.toString());
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, stringObjectMap.get(args[i]));
                }
                ps.executeUpdate();
            }
            return true ;
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        } finally {
            JdbcUtils.free(null, ps, null);
        }
    }

    /**
     * 获得数据
     * @param connection 连接对象
     * @param sql 查询语句
     * @return list里面的每个map集合是一条数据，map.get("字段名称")来获得数据
     */
    public static List<Map<String, Object>> getData(Connection connection, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = ps.getMetaData();
            int columnCount = rsmd.getColumnCount();
            List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
            Map<String, Object> data = null;
            while (rs.next()) {
                data = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    data.put(rsmd.getColumnLabel(i), rs.getObject(rsmd
                            .getColumnLabel(i)));
                }
                datas.add(data);
            }
            return datas;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            free(rs, ps, null);
        }
    }
}
