package interfaces;

import java.util.Collection;
import obj.*;

public interface AlarmAccionInterface {
    public Collection<AlarmAccion> getAll();
    public AlarmAccion getById(int id);
    public boolean delete(int id);
    public int insert(AlarmAccion accion);
    public void update(AlarmAccion accion);
}
