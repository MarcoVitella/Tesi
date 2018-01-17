package searchEngine;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import com.mongodb.client.model.Filters;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	DatabaseManager dbManager=new DatabaseManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrame frame = new SearchFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		setTitle("SearchFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 66, 302, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String textFieldValue = textField.getText();
				String result=dbManager.findRicerca(textFieldValue);
				ImageWindow im=new ImageWindow(result);
				im.setVisible(true);
				setVisible(false);
			}
		});
		btnSearch.setBounds(335, 108, 89, 23);
		contentPane.add(btnSearch);
		
		JList list = new JList();
		list.setBounds(170, 108, 0, -11);
		contentPane.add(list);
		
		JTextPane txtpnInsertTagIn = new JTextPane();
		txtpnInsertTagIn.setEditable(false);
		txtpnInsertTagIn.setText("Insert tag in order to search images of a certain pattern.");
		txtpnInsertTagIn.setBounds(10, 11, 302, 44);
		contentPane.add(txtpnInsertTagIn);
	}
}
