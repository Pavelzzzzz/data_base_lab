package com.pavelzzzzz.data_base_lab.form;

import com.pavelzzzzz.data_base_lab.dto.UserEntity;
import com.pavelzzzzz.data_base_lab.service.UserServise;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;
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

  private String[] columnsHeader = new String[]{"Email", "firstName", "lastName"};

  private JPanel contentPane;
  private JButton buttonUpdate;
  private JButton buttonAdd;
  private DefaultTableModel tableModel;
  private JTable table;
  private JScrollPane tableScrollPane;
  private JLabel recordCountLable;
  private JButton buttonLoad;
  private JButton buttonWrite;

  private List<UserEntity> userEntityList;

  public MainForm() {
    setModal(true);
    getRootPane().setDefaultButton(buttonUpdate);

    tableModel = new DefaultTableModel();
    tableModel.setColumnIdentifiers(columnsHeader);

    table = new JTable(tableModel);
    tableScrollPane.setViewportView(table);
    setContentPane(contentPane);


    userEntityList = new LinkedList<UserEntity>();
    userEntityList.add(UserEntity.builder()
//        .userId(1)
        .email("Tom@gmail.com")
        .firstName("Tom")
        .lastName("Ankevich")
        .build());

    userEntityList.add(UserEntity.builder()
//        .userId(2)
        .email("Vasia@gmail.com")
        .firstName("Vasia")
        .lastName("Pushevich")
        .build());

    userEntityList.add(UserEntity.builder()
//        .userId(3)
        .email("Alex@gmail.com")
        .firstName("Alex")
        .lastName("Panko")
        .build());

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

    buttonLoad.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        onLoad();
      }
    });

    buttonWrite.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        onWrite();
      }
    });
  }

  private void onWrite() {
    UserServise.write(userEntityList);
  }

  private void onLoad() {
    userEntityList = UserServise.getAll();
    onUpdate();
  }

  private void onAdd() {
    AddDialog dialog = new AddDialog(this);
    dialog.pack();
    dialog.setVisible(true);
  }

  private void onUpdate() {

    while (tableModel.getRowCount() > 0) {
      tableModel.removeRow(0);
    }

    for (UserEntity userEntity
        : userEntityList) {
      tableModel.addRow(new Object[]{//userEntity.getUserId(),
          userEntity.getEmail(),
          userEntity.getFirstName(),
          userEntity.getLastName()});
    }

    recordCountLable.setText(tableModel.getRowCount() + "");
  }

  private void onCancel() {
    // add your code here if necessary
    UserServise.shutdown();
    dispose();
  }

  public List<UserEntity> getUserEntityList() {
    return userEntityList;
  }

  public void setUserEntityList(List<UserEntity> userEntityList) {
    this.userEntityList = userEntityList;
  }

  public static void main(String[] args) {
    MainForm dialog = new MainForm();
    dialog.pack();
    dialog.setVisible(true);
    System.exit(0);
  }

}
