package com.pavelzzzzz.data_base_lab.form;

import com.pavelzzzzz.data_base_lab.dto.UserEntity;
import com.pavelzzzzz.data_base_lab.service.UserServise;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class MainForm extends JDialog {

  private String[] columnsHeader = new String[]{"UserId", "Email", "firstName", "lastName"};

  private JPanel contentPane;
  private JButton buttonUpdate;
  private JButton buttonAdd;
  private DefaultTableModel tableModel;
  private JTable table;
  private JScrollPane tableScrollPane;
  private JLabel recordCountLable;

  public MainForm() {
    setModal(true);
    getRootPane().setDefaultButton(buttonUpdate);

    tableModel = new DefaultTableModel();
    tableModel.setColumnIdentifiers(columnsHeader);

    onUpdate();

    table = new JTable(tableModel);
    tableScrollPane.setViewportView(table);
    setContentPane(contentPane);

    buttonUpdate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onUpdate();
      }
    });

    buttonAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onAdd();
      }
    });

    // call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

    // call onCancel() on ESCAPE
    contentPane.registerKeyboardAction(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                           onCancel();
                                         }
                                       }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


  }

  private void onAdd() {
    AddDialog dialog = new AddDialog();
    dialog.pack();
    dialog.setVisible(true);
    onUpdate();
  }

  private void onUpdate() {

    while (tableModel.getRowCount() > 0) {
      tableModel.removeRow(0);
    }

    for (UserEntity userEntity
        : UserServise.getAll()) {
      tableModel.addRow(new Object[]{userEntity.getUserId(),
          userEntity.getEmail(),
          userEntity.getFirstName(),
          userEntity.getLastName()});
    }

    recordCountLable.setText(tableModel.getRowCount() + "");
  }

  private void onCancel() {
    // add your code here if necessary
    dispose();
  }

  public static void main(String[] args) {
    MainForm dialog = new MainForm();
    dialog.pack();
    dialog.setVisible(true);
    System.exit(0);
  }

}
