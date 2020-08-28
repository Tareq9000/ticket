package Tickets;

import java.util.List;

public interface iDao {

    List<Ticket> getAll();
    List<Ticket> get(int id);
    boolean insert(Ticket t);
    boolean update(Ticket t);
    boolean delete(int id);

}
