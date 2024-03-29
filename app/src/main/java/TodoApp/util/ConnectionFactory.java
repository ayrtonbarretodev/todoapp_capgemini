package TodoApp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/todoapp";
    public static final String USER = "root";
    public static final String PASS = "q1w2e3";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER); //carrega o driver que vamos utilizar para dentro da nossa aplica��o
            return DriverManager.getConnection(URL, USER, PASS);
            //faz a comunica��o com o BD levando em considera��o esses par�metros informados e retorna a conex�o
        } catch (Exception e) {
            throw new RuntimeException("Erro na conex�o com o banco de dados", e);
        }
    }

}
