package interfaces;

import java.util.Collection;
import obj.*;

public interface UserInterface {
    public User getById(int id);
    public User getByToken(String token) ;
    public User getByEmailAndPassword(String email, String password);
    public User getByEmail(String email);
    public boolean delete(int id);
    public int insert(User user);
    public void update(User user);
    public Collection<User> getAll();
}
