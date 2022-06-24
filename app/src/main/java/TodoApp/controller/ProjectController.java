package TodoApp.controller;

import TodoApp.model.Project;
import TodoApp.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectController {

    public void save(Project project) {
        String sql = "INSERT INTO projects (name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?)";

        try ( Connection con = ConnectionFactory.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setDate(3, new Date(project.getCreatedAt().getTime()));
            ps.setDate(4, new Date(project.getUpdatedAt().getTime()));
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o projeto " + e.getMessage(), e);
        }
    }

    public void update(Project project) {
        String sql = "UPDATE projects SET name = ?, description = ?,createdAt = ?, updatedAt = ? WHERE id = ?";

        //Estabelecendo a conexão com o BD e preparando a query
        try ( Connection con = ConnectionFactory.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            //Setando os valores do statement

            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setDate(3, new Date(project.getCreatedAt().getTime()));
            ps.setDate(4, new Date(project.getUpdatedAt().getTime()));
            ps.setInt(5, project.getId());

            //Executando a query
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o projeto " + e.getMessage(), e);
        }

    }

    public void removeById(int projectId) {
        String sql = "DELETE FROM projects WHERE id = ?";

        try ( Connection con = ConnectionFactory.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, projectId);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o projeto" + e.getMessage(), e);
        }
    }

    public List<Project> getAll() {
        String sql = "SELECT * FROM projects";

        List<Project> projects = new ArrayList<>();

        try ( Connection con = ConnectionFactory.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ps.executeQuery();
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setId(rs.getInt("id"));
                    project.setName(rs.getString("name"));
                    project.setDescription(rs.getString("description"));
                    project.setCreatedAt(rs.getDate("createdAt"));
                    project.setUpdatedAt(rs.getDate("updatedAt"));

                    projects.add(project);
                }
            } catch (Exception e) {
                e.getMessage();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir o projeto" + e.getMessage(), e);
        }

        return projects;
    }

}
