package TodoApp.controller;

import TodoApp.model.Task;
import TodoApp.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskController {

    public void save(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (idProject, name, description, completed, notes, deadline, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ? ,?)";

        try ( Connection con = ConnectionFactory.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, task.getIdProject());
            ps.setString(2, task.getName());
            ps.setString(3, task.getDescription());
            ps.setBoolean(4, task.isCompleted());
            ps.setString(5, task.getNotes());
            ps.setDate(6, new Date(task.getDeadline().getTime()));
            ps.setDate(7, new Date(task.getCreatedAt().getTime()));
            ps.setDate(8, new Date(task.getUpdateAt().getTime()));
            ps.execute();
        } catch (Exception e) {
            throw new SQLException("Erro ao salvar a tarefa " + e.getMessage(), e);
        }
    }

    public void update(Task task) {

    }

    public void removeById(int taskId) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try ( Connection con = ConnectionFactory.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, taskId);
            ps.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa");
        }
    }

    public List<Task> getAll(int idProject) {
        String sql = "SELECT FROM tasks WHERE idProject = ?";

        List<Task> x = new ArrayList<>();
        return x;
    }
}
