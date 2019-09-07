package fightApp;

/*
 * Author @ John Hutchins 4-18 
 *  ReadAndWrite reads and writes HasPlayed
 *  reads it to a StringBuilder and Generic Collect.(list)
 *  outputs all past players in list and allows player to add one name
 *  writes name to same file
 */

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.DropMode;
import java.awt.Font;

public class ReadAndWrite extends JPanel {
	private JButton btnAddName;
	private List<String> list = new ArrayList<String>();
	private StringBuilder sb = new StringBuilder();
	private JTextField textField;
	private JScrollPane sp;

	/**
	 * Create the panel.
	 */
	public ReadAndWrite() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel ButtonPanel = createButtonPanel();
		add(ButtonPanel, BorderLayout.SOUTH);
		
		JPanel infoPanel = createInfoPanel();
		add(infoPanel, BorderLayout.NORTH);

		
		JPanel IOPanel = createIOPanel();
		add(IOPanel, BorderLayout.CENTER);
	}

	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel();
		
		JLabel lblWhosPlayed = new JLabel("Who's Played?! Enter Your Name to Live On For Forever!");
		lblWhosPlayed.setHorizontalTextPosition(SwingConstants.LEFT);
		lblWhosPlayed.setBorder(new EmptyBorder(10, 0, 10, 0));
		infoPanel.add(lblWhosPlayed);
		return infoPanel;
	}

	private JPanel createIOPanel() {
		JPanel IOPanel = new JPanel();
		IOPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		IOPanel.setLayout(new GridLayout(0, 2, 0, 0));
		readIn();
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(null);
        textArea.setBorder(new EmptyBorder(10, 0, 10, 0));
        textArea.setFocusable(false);
        textArea.setEditable(false);
		textArea.setText(sb.toString());
		IOPanel.add(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		IOPanel.add(textField);
		textField.setColumns(5);
		return IOPanel;
	}

	private JPanel createButtonPanel() {
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
		add(ButtonPanel, BorderLayout.SOUTH);
		
		btnAddName = new JButton("Add Name");
		btnAddName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sb.append(textField.getText());
				writeOut();
				textField.setEditable(false);
				textField.setText("Thanks For Playing!");
				btnAddName.setEnabled(false);
			}
		});
		ButtonPanel.add(btnAddName);
		return ButtonPanel;
	}
	private void writeOut(){
		try(PrintWriter writer = new PrintWriter("src/fightApp/IOFiles/HavePlayed.txt")){;
				writer.println(sb);
				
			} catch (IOException e) {
				System.out.println("==> " + e);
				e.printStackTrace();
			} 
		
	}

	private void readIn(){
		try(BufferedReader reader = new BufferedReader(new FileReader("src/fightApp/IOFiles/HavePlayed.txt"))){;
				String line;
				while((line = reader.readLine()) != null){
					list.add(line);
					sb.append(line);
					sb.append("\n");	
				}
			} catch (IOException e) {
				System.out.println("==> " + e);
				e.printStackTrace();
			} 
	}
}