package edu.mccc.tsa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;




@SuppressWarnings("unused")
public class SoftwareDevelopment extends JFrame  {
	private static final long serialVersionUID = 1L;
	private JButton jc = new JButton("Prev");
	private JButton jd = new JButton("Next");
	private int page = 1;
	private JLabel jl = new JLabel(Integer.toString(page));
	protected JTextArea textArea = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(textArea);
	protected JTextField textField;


	public SoftwareDevelopment() {
		super("SoftwareDevelopment");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024, 768);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		add(jc, BorderLayout.WEST);
		jc.addActionListener(
				ae -> {
					BufferedReader bs;
					page-=1;
					try {
						bs = new BufferedReader(new FileReader("D:/TSA/File"+page+".txt"));
						textArea.setText(null);
						String line = bs.readLine();
						while(line != null){
					        textArea.append(line + "\n");
					        line = bs.readLine();
					  
					     }
					} catch (Exception e) {
						e.printStackTrace();
					}		
					jl.setText(Integer.toString(page));
				}
		);
		add(jd, BorderLayout.EAST);
		jd.addActionListener(
				ae -> {
					BufferedReader bs;
					page+=1;
					try {
						bs = new BufferedReader(new FileReader("D:/TSA/File"+page+".txt"));
						textArea.setText(null);
						String line = bs.readLine();
						while(line != null){
					        textArea.append(line + "\n");
					        line = bs.readLine();
					  
					     }
					} catch (Exception e) {
						e.printStackTrace();
					}
					jl.setText(Integer.toString(page));
				}
		);
		add(createToolBar(), BorderLayout.NORTH);
		textArea.setEditable(false);
		add(scrollPane);
	}
	
	public static void main(String[] sa) {
		EventQueue.invokeLater (
				() -> new SoftwareDevelopment()
		);
	}
	private JToolBar createToolBar() {
		JToolBar jtb = new JToolBar();
		JButton je = new JButton("Open");
		JTextField jt = new JTextField("Page Number");
		je.addActionListener(
				ae -> {
					JFileChooser jfc = new JFileChooser("txt files");
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
						".txt files", "txt"
					);
					jfc.setFileFilter(filter);
					if (jfc.showOpenDialog(je) == JFileChooser.APPROVE_OPTION) {
						try {
							////////////////////////////////Splitting the file////////////////////////////////////////////////////////////////////
							Scanner scanner = new Scanner(jfc.getSelectedFile());
							double nol = 43.0;
							int count = 0; 
							while (scanner.hasNextLine())   
							  {  
							   scanner.nextLine();  
							   count++;  
							  }  
							scanner.close();
							double temp = (count/nol);  
							  int temp1=(int)temp;  
							  int nof=0;  
							  if(temp1==temp){  
							   nof=temp1;  
							  }  
							  else  
							  {  
							   nof=temp1+1;  
							  }
							 FileInputStream fstream = new FileInputStream(jfc.getSelectedFile());
							  DataInputStream in = new DataInputStream(fstream);  
							  BufferedReader br = new BufferedReader(new InputStreamReader(in)); 
							  String strLine;  
							  for (int j=1;j<=nof;j++)  
							  {  
							   FileWriter fstream1 = new FileWriter(jfc.getCurrentDirectory()+"/File"+j+".txt");     // Destination File Location  
							   BufferedWriter out = new BufferedWriter(fstream1);   
							   for (int i=1;i<=nol;i++)  
							   {  
							    strLine = br.readLine();   
							    if (strLine!= null)  
							    {  
							     out.write(strLine);   
							     if(i!=nol)  
							     {  
							      out.newLine();  
							     }  
							    }  
							   }  
							   out.close();  
							  }  

							  in.close();  
						
							BufferedReader bs = new BufferedReader(new FileReader(jfc.getCurrentDirectory()+"/File"+page+".txt"));	
							String line = bs.readLine();						
							while(line != null){
						        textArea.append(line + "\n");
						        line = bs.readLine();
						  
						        }
							bs.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			);
		jtb.add(je);
		jtb.addSeparator();
		jtb.add(jl);
		jtb.addSeparator();
		jtb.add(jt);
		jt.addActionListener(
			ae -> {
				String text = jt.getText();
				BufferedReader bs;
				try {
					bs = new BufferedReader(new FileReader("D:/TSA/File"+text+".txt"));
					page = Integer.parseInt(text);
					textArea.setText(null);
					String line = bs.readLine();
					while(line != null){
				        textArea.append(line + "\n");
				        line = bs.readLine();
				  
				     }
				} catch (Exception e) {
					e.printStackTrace();
				}							
				jl.setText(Integer.toString(page));
			}
		);
		
		return jtb;
		
	}


}
