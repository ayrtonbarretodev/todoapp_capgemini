package TodoApp.controller;

import TodoApp.model.Task;
import TodoApp.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskController {

    public void save(Task task) {
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
            throw new RuntimeException("Erro ao salvar a tarefa " + e.getMessage(), e);
        }
    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, completed = ?, notes = ?, deadline = ?"
                + "createdAt = ?, updatedAt = ? WHERE id = ?";
        
        //Estabelecendo a conexão com o BD e preparando a query
        try ( Connection con = ConnectionFactory.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            //Setando os valores do statement
            ps.setInt(1, task.getIdProject());
            ps.setString(2, task.getName());
            ps.setString(3, task.getDescription());
            ps.setBoolean(4, task.isCompleted());
            ps.setString(5, task.getNotes());
            ps.setDate(6, new Date(task.getDeadline().getTime()));
            ps.setDate(7, new Date(task.getCreatedAt().getTime()));
            ps.setDate(8, new Date(task.getUpdateAt().getTime()));
            ps.setInt(9, task.getId());
            
            //Executando a query
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar a tarefa " + e.getMessage(), e);
        }

    }

    public void removeById(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try ( Connection con = ConnectionFactory.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, taskId);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a tarefa" + e.getMessage(), e);
        }
    }

    public List<Task> getAll(int idProject) {
        String sql = "SELECT * FROM tasks WHERE idProject = ?";

        List<Task> tasks = new ArrayList<>();

        try ( Connection con = ConnectionFactory.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idProject);
            ps.executeQuery();
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getInt("id"));
                    task.setIdProject(rs.getInt("idProject"));
                    task.setName(rs.getString("name"));
                    task.setDescription(rs.getString("description"));
                    task.setCompleted(rs.getBoolean("completed"));
                    task.setNotes(rs.getString("notes"));
                    task.setDeadline(rs.getDate("deadline"));
                    task.setCreatedAt(rs.getDate("createdAt"));
                    task.setUpdateAt(rs.getDate("updatedAt"));

                    tasks.add(task);
                }
            } catch (Exception e) {
                e.getMessage();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir a tarefa" + e.getMessage(), e);
        }

        return tasks;
    }
}
