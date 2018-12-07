package com.filedecoder.decoder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultEditorKit;

public class FileDecoderApp {

	private JFrame frmImageDecoder;
	private JTextField textFieldTargetPath;
	private JTextArea textAreaEncodedStr;
	private JLabel lblMessage;
	private final ButtonGroup buttonGroup = new ButtonGroup();	
	private JFileChooser chooser;
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileDecoderApp window = new FileDecoderApp();
					window.frmImageDecoder.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}
	
	public FileDecoderApp() {
		initialize();
	}
	
	private void initialize() {
		frmImageDecoder = new JFrame();
		frmImageDecoder.setBackground(Color.LIGHT_GRAY);
		frmImageDecoder.setTitle("File Base64 Decoder");
		frmImageDecoder.setBounds(100, 100,800, 400);
		frmImageDecoder.getContentPane().setLayout(null);
		frmImageDecoder.setResizable(false);
		frmImageDecoder.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(17, 17, 735, 334);
		frmImageDecoder.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblMessage = new JLabel();
		lblMessage.setBounds(10, 317, 715, 17);
		panel.add(lblMessage);
		lblMessage.setFont(new Font("Times New Roman", Font.PLAIN | Font.ITALIC, 12));
		lblMessage.setForeground(new Color(0, 128, 0));
		
		JLabel lblEnterTargetPath = new JLabel("Enter target path : ");
		lblEnterTargetPath.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblEnterTargetPath.setBounds(25, 19, 128, 15);
		panel.add(lblEnterTargetPath);
		
		textFieldTargetPath = new JTextField();
		textFieldTargetPath.setBounds(206, 16, 484, 20);
		panel.add(textFieldTargetPath);
		textFieldTargetPath.setColumns(10);
		textFieldTargetPath.setEnabled(false);
		
		BufferedImage image2=null;
		try {
            URL resource = frmImageDecoder.getClass().getResource("/decoder.png");
            URL resource2 = frmImageDecoder.getClass().getResource("/search.png");
            BufferedImage image = ImageIO.read(resource);
            image2 = ImageIO.read(resource2);
            frmImageDecoder.setIconImage(image);
        } catch (IOException e) {
        	lblMessage.setForeground(new Color(255, 0, 0));
        	lblMessage.setText(e.getMessage());
        }
		
		final JButton btnBrowse = new JButton();
		btnBrowse.setBounds(695, 16, 30, 19);
		btnBrowse.setIcon(new ImageIcon(image2));
		panel.add(btnBrowse);
		
		JLabel lblEnterEncodedString = new JLabel("Enter encoded string : ");
		lblEnterEncodedString.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblEnterEncodedString.setBounds(25, 67, 160, 14);
		panel.add(lblEnterEncodedString);
		
		textAreaEncodedStr = new JTextArea();
		textAreaEncodedStr.setBounds(206, 62, 484, 175);
		panel.add(textAreaEncodedStr);
		textAreaEncodedStr.setLineWrap(true);
		textAreaEncodedStr.setBackground(new Color(192, 192, 192));
		
		final JButton btnDecode = new JButton("Decode");
		btnDecode.setFont(new Font("Times New Roman", Font.BOLD, 14));		
		btnDecode.setBounds(511, 283, 89, 23);
		panel.add(btnDecode);
		
		final JButton btnCancel = new JButton("Clear");
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 14));		
		btnCancel.setBounds(601, 283, 89, 23);
		panel.add(btnCancel);
		
		JLabel lblFileType = new JLabel("File Type : ");
		lblFileType.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFileType.setBounds(25, 251, 98, 14);
		panel.add(lblFileType);
		
		final JRadioButton rdbtnImage = new JRadioButton("Image");
		buttonGroup.add(rdbtnImage);
		rdbtnImage.setBounds(206, 244, 64, 23);
		panel.add(rdbtnImage);
		rdbtnImage.setSelected(true);
		
		final JRadioButton rdbtnPdf = new JRadioButton("Pdf");
		buttonGroup.add(rdbtnPdf);
		rdbtnPdf.setBounds(266, 244, 54, 23);
		panel.add(rdbtnPdf);
		
		final JRadioButton rdbtnWord = new JRadioButton("Word");
		buttonGroup.add(rdbtnWord);
		rdbtnWord.setBounds(322, 244, 64, 23);
		panel.add(rdbtnWord);
		
		final JRadioButton rdbtnPlainText = new JRadioButton("Plain Text");
		buttonGroup.add(rdbtnPlainText);
		rdbtnPlainText.setBounds(385, 244, 89, 23);
		panel.add(rdbtnPlainText);
		
		JPopupMenu popup = new JPopupMenu();
	      JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
	      item.setText("Cut");
	      popup.add(item);
	      item = new JMenuItem(new DefaultEditorKit.CopyAction());
	      item.setText("Copy");
	      popup.add(item);
	      item = new JMenuItem(new DefaultEditorKit.PasteAction());
	      item.setText("Paste");
	      popup.add(item);
	      textAreaEncodedStr.setComponentPopupMenu(popup);
		btnDecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(validateFields(textFieldTargetPath,textAreaEncodedStr)) {
					String encodedString=textAreaEncodedStr.getText();
					FileType fileType=FileType.JPG;
					if(rdbtnImage.isSelected()) {
						fileType=FileType.JPG;					
					}else if(rdbtnPdf.isSelected()) {
						fileType=FileType.PDF;
					}else if(rdbtnWord.isSelected()) {
						fileType=FileType.WORD;
					}else if(rdbtnPlainText.isSelected()) {
						fileType=FileType.TEXT;
					}				
					FileDecoder decoder=new FileDecoder();
					try {
						String filename=decoder.imageDecoder(encodedString.trim(), textFieldTargetPath.getText(),fileType);
						lblMessage.setForeground(new Color(0, 128, 0));
						lblMessage.setText("File saved at path :"+filename);
						btnCancel.getAction();
					} catch (FileNotFoundException exp) {
						lblMessage.setForeground(new Color(255, 0, 0));
						lblMessage.setText("Error occurred!! "+exp.getMessage());
					} catch(Exception exp) {
						lblMessage.setForeground(new Color(255, 0, 0));
						lblMessage.setText("Error occurred!! Invalid input encoded string");
					}
				}										
			}

			private boolean validateFields(JTextField textFieldTargetPath, JTextArea textAreaEncodedStr) {
				if(textFieldTargetPath.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "please enter target path");
					textFieldTargetPath.requestFocus();
					return false;
				}
				if(textAreaEncodedStr.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "please enter base64 encoded string");
					textAreaEncodedStr.requestFocus();
					return false;
				}
				return true;
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldTargetPath.setText(null);
				textAreaEncodedStr.setText(null);
				lblMessage.setText(null);
				rdbtnImage.setSelected(true);
				rdbtnPdf.setSelected(false);
				rdbtnWord.setSelected(false);
				rdbtnPlainText.setSelected(false);
				textFieldTargetPath.requestFocus();
			}
		});		
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedPath=getFileChooser();
				textFieldTargetPath.setText(selectedPath);
			}
		});		
	}

	public String getFileChooser() {
    	chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Save File");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       
        chooser.setAcceptAllFileFilterUsed(false);
        //  
        String selectedPath="";
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
        	selectedPath=String.valueOf(chooser.getSelectedFile());
        }       
        return selectedPath;
    }   
}
