package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class Main extends JFrame {
    private JTextField txtTitle;
    private JTextArea txtDescription;
    private JComboBox<String> cbState;
    private JTable tableTasks;
    private DefaultTableModel tableModel;
    private ArrayList<Task> taskList;

    public Main(){
        taskList = new ArrayList<>();
        this.windowConfig();
        this.componentInicialize();
    }

    private void windowConfig(){
        setTitle("Gestor de Tareas - Test Práctico");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));
    }
    private void buildTitle(JPanel entryPanel){
        entryPanel.add(new JLabel("Title: ")); // title
        txtTitle = new JTextField();
        entryPanel.add(txtTitle);
    }
    private void buildDescription(JPanel entryPanel){
        entryPanel.add(new JLabel("Description:")); // description
        txtDescription = new JTextArea(2,20);
        entryPanel.add(new JScrollPane(txtDescription));
    }
    private void buildState(JPanel entryPanel){
        entryPanel.add(new JLabel("State: "));
        String[] states = {"Pending", "In progress", "Complete"};
        cbState = new JComboBox<>(states);
        entryPanel.add(cbState);
    }
    private void buildButton(JPanel entryPanel){
        JButton buttonAdd = new JButton("Add task");
        buttonAdd.addActionListener(e -> this.addTask());
        entryPanel.add(buttonAdd);
    }
    private void buildTableTask(){
        tableModel = new DefaultTableModel(new Object[]{"Title", "Description", "State"},0);
        tableTasks = new JTable(tableModel);
        add(new JScrollPane(tableTasks), BorderLayout.CENTER);
    }
    private void buildActions(){
        JPanel actionsPanel = new JPanel();
        JButton buttonDelete = new JButton("Delete selected");
        JButton buttonChangeState = new JButton("Change to complete");

        buttonDelete.addActionListener(e -> this.deleteTask());
        buttonChangeState.addActionListener(e->this.changeState());

        actionsPanel.add(buttonDelete);
        actionsPanel.add(buttonChangeState);
        add(actionsPanel,BorderLayout.SOUTH);
    }
    private void cleanFields(){
        txtTitle.setText("");
        txtDescription.setText("");
    }
    private void componentInicialize(){
        JPanel entryPanel = new JPanel(new GridLayout(4,2,5,5));
        entryPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        this.buildTitle(entryPanel);
        this.buildDescription(entryPanel);
        this.buildState(entryPanel);
        this.buildButton(entryPanel);
        add(entryPanel,BorderLayout.NORTH);

        this.buildTableTask();
        this.buildActions();
    }
    private void addTask(){
        String title = txtTitle.getText().trim(); // get title
        if(title.isEmpty()){ //Show error
            JOptionPane.showMessageDialog(this, "Title is required", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String description = txtDescription.getText();
        String state = (String) cbState.getSelectedItem();
        Task newTask = new Task(title, description, state);
        taskList.add(newTask);
        tableModel.addRow(new Object[]{newTask.getTitle(), newTask.getDescription(), newTask.getState()});
        this.cleanFields();
    }
    private void deleteTask(){
        int row = tableTasks.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this,"Select a task to delete.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?");
        if(confirm == JOptionPane.YES_OPTION){
            taskList.remove(row);
            tableModel.removeRow(row);
            JOptionPane.showMessageDialog(this, "Task delete correctly");
        }
    }
    private void changeState(){
        int row = tableTasks.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a task first.");
            return;
        }
        taskList.get(row).setState("Complete");
        tableModel.setValueAt("Completed", row, 2);
    }
    static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
