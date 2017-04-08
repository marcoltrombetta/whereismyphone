package interfaces;

import java.util.Collection;
import obj.Action;

/**
 *
 * @author marco
 */
public interface ActionInterface {
    Collection<Action> getByIdUsuario(int idusuario);
    Action getById(int id, int idusuario);
    int insert(Action action);
    void update(Action action);
    boolean deleteByPhoneInfo(int idphoneinfo, int idusuario);
}
