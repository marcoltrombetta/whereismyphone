package interfaces;

import java.util.Collection;
import obj.*;

public interface RoleInterface {
    public Collection<Role> getAll();
    public Role getById(int id);
    public boolean delete(int id);
    public int insert(Role role);
    public void update(Role role);
}
