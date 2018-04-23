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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class AddDialog extends JDialog {

  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;
  private JTextField emailTextField;
  private JTextField firstNameTextField;
  private JTextField lastNameTextField;

  private MainForm mainForm;

  public AddDialog(MainForm mainForm) {
    this.mainForm = mainForm;

    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);

    buttonOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onOK();
      }
    });

    buttonCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        onCancel();
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

  private void onOK() {
    mainForm.getUserEntityList().add(UserEntity.builder()
//    .userId(mainForm.getUserEntityList().size())
    .email(emailTextField.getText())
    .firstName(firstNameTextField.getText())
    .lastName(lastNameTextField.getText())
    .build());
    dispose();
  }

  private void onCancel() {
    // add your code here if necessary
    dispose();
  }

}
