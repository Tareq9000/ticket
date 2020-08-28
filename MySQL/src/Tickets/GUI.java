package Tickets;

import java.awt.BorderLayout;

import javax.swing.*;
class GUI{
    public static void main(String args[]){
       JFrame frame = new JFrame("Phantom Problem");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(400,300);
       JPanel pane = new JPanel(new BorderLayout());
       JButton button = new JButton("Select");
       JButton button2 = new JButton("Insert");
       
       JLabel l = new JLabel("");
       
       pane.add(button, BorderLayout.EAST);
       pane.add(button2, BorderLayout.WEST);
       pane.add(l, BorderLayout.CENTER);
       frame.getContentPane().add(pane); // Adds Button to content pane of frame
       frame.setVisible(true);
       
       TicketDao td = new TicketDao();
       
       button.addActionListener(new java.awt.event.ActionListener() {
           // Beim Drücken des Menüpunktes wird actionPerformed aufgerufen
           public void actionPerformed(java.awt.event.ActionEvent e) {
        	   
               l.setText(td.selectWithSleep());
           }
       });
       
       button2.addActionListener(new java.awt.event.ActionListener() {
           // Beim Drücken des Menüpunktes wird actionPerformed aufgerufen
           public void actionPerformed(java.awt.event.ActionEvent e) {
        	   Ticket ticket = new Ticket(25.00, "The konzert");
        	   if(td.insert(ticket)) {
        		   l.setText("Inserted");
        	   }
        	   l.setText(l.getText() + " "+td.getCount()+" rows.");
           }
       });
    }
}