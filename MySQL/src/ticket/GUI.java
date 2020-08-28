package ticket;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GUI {
	
	  public static int ticketsToSell = 10;
	  
	  public static void main(String[] args) {
	      JPanel formPanel = createFormPanel();
	      JFrame frame = createFrame();
	      frame.add(formPanel);
	      frame.setLocationRelativeTo(null);
	      frame.setVisible(true);
	  }

	  private static JPanel createFormPanel() {
	      //initialize fields
		  JLabel anzahlLabel = new JLabel("0");
	      JTextField anzahlTickets = new JTextField(10);
	      JButton submitBtn = new JButton("Submit");
	      
	      TicketDao td = new TicketDao();
	      //td.clearTable();
	      anzahlLabel.setText(Integer.toString(ticketsToSell-td.selectCount()));
	      //create panel
	      JPanel panel = new JPanel();
	      panel.setBorder(new EmptyBorder(10, 10, 10, 10));
	      //using FormBuilder
	      FormBuilder.init(panel)
	                 .add("Tickets übrig", anzahlLabel, FormBuilder::spanX3)
	                 .newRow()
	                 .add("anzahl Tickets", anzahlTickets, FormBuilder::spanX3)
	                 .newRow()
	                 .newRow()
	                 .newRow()
	                 .skipColumns(2).add(submitBtn, FormBuilder::spanX2);
	      
	      submitBtn.addActionListener(new java.awt.event.ActionListener() {
	           public void actionPerformed(java.awt.event.ActionEvent e) {
	        	   int eingabe = Integer.parseInt(anzahlTickets.getText());
	        	   int bereitsInDB = Integer.parseInt(anzahlLabel.getText());
	        	   System.out.println(eingabe);
	        	   if(eingabe+td.selectCount() <= ticketsToSell) {
	        		   while(eingabe > 0) {
	        			   System.out.print("a");
	        			   eingabe--;
	        			   
	        			   Ticket ticket = new Ticket(15.00, "The konzert");
	        			   td.insert(ticket);
	        		   }
	        	   }
	        	   
	        	   
	        	   
	              // anzahlLabel.getText();
	           }
	      });
	      
	      return panel;
	  }

	  private static JFrame createFrame() {
	      JFrame frame = new JFrame("Tickets");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setSize(new Dimension(400, 300));
	      return frame;
	  }
	}