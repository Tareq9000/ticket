package ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner; 

public class TicketDao implements iDao{
    
    public static void main(String[] args) throws SQLException {
    	Scanner scan = new Scanner(System.in);
        TicketDao td = new TicketDao();
        Ticket ticket = new Ticket(15.00, "The konzert");
        
    }
    String connStatement = "jdbc:mysql://localhost:3306/konzert?autoReconnect=true&useSSL=false";
    String user = "root";
    String passWord = "1234";

    
    public int selectCount() {
    	Connection con = null;
    	
        try {
        	con = DriverManager.getConnection(connStatement, user, passWord);
        	//con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        	con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        	
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            con.setAutoCommit(false);

            String query = "SELECT * FROM tickets";
            
            ResultSet rs = stmt.executeQuery(query);
            
            int count = 0;
            
            while (rs.next()) {
                String name = rs.getString("nummer");
                String preis = rs.getString("preis");
                String konzert = rs.getString("konzert");
                System.out.println(name+" "+preis+" "+konzert);
                count++;
            }
            con.commit();
            con.close();
            
            return count;

        } catch (SQLException | ClassNotFoundException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return 0;
    }
    
    
    public String getCount() {
    	Connection con = null;
    	
        try {
        	con = DriverManager.getConnection(connStatement, user, passWord);
        	
        	con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        	
            con.setAutoCommit(false);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            String query = "select * from tickets";
            
            ResultSet rs = stmt.executeQuery(query);
            int ret = 0;
            while (rs.next()) {
                String name = rs.getString("nummer");
                String preis = rs.getString("preis");
                String konzert = rs.getString("konzert");
                System.out.println(name+" "+preis+" "+konzert);
                ret++;
            }
            
            
            con.setAutoCommit(true);
            con.close();
            return Integer.toString(ret);

        } catch (SQLException | ClassNotFoundException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Ticket> getAll() {
    	Connection con = null;
    	
        try {
        	con = DriverManager.getConnection(connStatement, user, passWord);
        	
        	con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        	
            con.setAutoCommit(false);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            String query = "select * from tickets";
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                String name = rs.getString("nummer");
                String preis = rs.getString("preis");
                String konzert = rs.getString("konzert");
                System.out.println(name+" "+preis+" "+konzert);
            }
            
            
            con.setAutoCommit(true);
            con.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ticket> get(int id) {
    	Connection con = null;
        try {
        	con = DriverManager.getConnection(connStatement, user, passWord);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();

            String query = "select * from tickets where nummer="+id;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("nummer");
                String preis = rs.getString("preis");
                String konzert = rs.getString("konzert");
                System.out.println(name+" "+preis+" "+konzert);
            }

            con.setAutoCommit(true);
            con.close();


        } catch (SQLException | ClassNotFoundException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(Ticket t) {
    	Connection con = null;
    	System.out.println("insert...");
    	if(new TicketDao().selectCount() < GUI.ticketsToSell) {
	        try {
	        	con = DriverManager.getConnection(connStatement, user, passWord);
	        	con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	        	con.setAutoCommit(false);
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Statement stmt = con.createStatement();
	
	            String query = "INSERT INTO tickets (preis, konzert)" +
	                    "VALUES ("+t.getPreis()+", \""+t.getKonzert()+"\")";
	
	            stmt.executeUpdate(query);
	
	            con.commit();
	            con.close();
	            return true;
	
	        } catch (SQLException | ClassNotFoundException throwables) {
	            try {
	                con.rollback();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            throwables.printStackTrace();
	            return false;
	        }
    	}
    	return false;

    }

    @Override
    public boolean update(Ticket t) {
    	Connection con = null;
        try {
        	con = DriverManager.getConnection(connStatement, user, passWord);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();

            String query = "UPDATE tickets" +
                    "SET preis = "+t.getPreis()+", konzert = \""+t.getKonzert()+"\"" +
                    "WHERE nummer ="+ t.getId();

            stmt.executeUpdate(query);

            con.setAutoCommit(true);
            con.close();
            return true;

        } catch (SQLException | ClassNotFoundException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(int id) {
    	Connection con = null;
        try {
        	con = DriverManager.getConnection(connStatement, user, passWord);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();

            String query = "DELETE FROM tickets WHERE nummer="+id;

            stmt.executeUpdate(query);

            con.setAutoCommit(true);
            con.close();
            return true;

        } catch (SQLException | ClassNotFoundException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            return false;
        }


    }
    public boolean clearTable() {
    	Connection con = null;
    	
        try {
        	con = DriverManager.getConnection(connStatement, user, passWord);
        	con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            
            String query = "DELETE FROM tickets";

            stmt.executeUpdate(query);
            
            con.commit();
            con.close();
            return true;

        } catch (SQLException | ClassNotFoundException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            return false;
        }


    }

}
