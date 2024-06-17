package co.edu.poli.ces3.universitas.database.repositories;

import co.edu.poli.ces3.universitas.database.CRUD;
import co.edu.poli.ces3.universitas.database.ConexionMySql;
import co.edu.poli.ces3.universitas.database.dao.User;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements CRUD {

    private ConexionMySql cnnMysql;

    public UserRepository() {
        cnnMysql = new ConexionMySql("localhost");
    }

    @Override
    public List<User> get() throws SQLException {
        Connection con = cnnMysql.conexion();
        Statement sts = con.createStatement();
        ResultSet rs = sts.executeQuery("SELECT * FROM enrollments");
        List<User> list = new ArrayList<>();

        while (rs.next()){
            list.add(new User(
                    rs.getInt("id"),
                    rs.getString("course_name"),
                    rs.getString("semester"),
                    rs.getString("status"),
                    rs.getDate("createdAt"),
                    rs.getDate("updatedAt"),
                    rs.getDate("deletedAt")
            ));
        }

        rs.close();
        sts.close();
        con.close();

        return list;
    }

    @Override
    public User getOne(int id) throws SQLException {
        Connection con = cnnMysql.conexion();
        PreparedStatement sts = con.prepareStatement("SELECT * FROM enrollments WHERE id = ?");
        sts.setInt(1,id);
        ResultSet rs = sts.executeQuery();
        if(rs.next())
            return new User(
                    rs.getInt("id"),
                    rs.getString("course_name"),
                    rs.getString("semester"),
                    rs.getString("status"),
                    rs.getDate("createdAt"),
                    rs.getDate("updatedAt"),
                    rs.getDate("deletedAt")
            );
        return null;
    }

    @Override
    public User insert() {
        return null;
    }

    @Override
    public User update(JsonObject userUpdate, int id) throws SQLException {
        String sql = "UPDATE enrollments SET course_name = ?, semester = ?, status = ?, updatedAt = now() WHERE id = ?";
        Connection cnn = this.cnnMysql.conexion();
        PreparedStatement sts = cnn.prepareStatement(sql);
        sts.setString(1, userUpdate.get("course_name").getAsString());
        sts.setString(2, userUpdate.get("semester").getAsString());
        sts.setString(3, userUpdate.get("status").getAsString());
        sts.setInt(4, id);
        sts.execute();
        return this.getOne(id);
    }

    public void disconect() throws SQLException {
        cnnMysql.disconect();
    }
}
