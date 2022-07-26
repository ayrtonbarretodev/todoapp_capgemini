package TodoApp.util;

import TodoApp.model.Task;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ayrton
 */
public class DeadlineColumnCellRederer extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setHorizontalAlignment(JLabel.CENTER);
        
        TaskTableModel taskModel = (TaskTableModel) table.getModel();
        Task task = taskModel.getTasks().get(row);
        
        
            if (task.getDeadline().after(new Date())) { //Se a data da tarefa for depois da data atual, ainda está no prazo
                label.setBackground(Color.GREEN);
            } else {
                label.setBackground(Color.RED);
            }
        
        return label;
    }
    
    
    
}
