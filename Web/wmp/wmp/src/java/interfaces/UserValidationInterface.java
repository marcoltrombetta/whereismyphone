package interfaces;

import java.util.Collection;
import obj.UserValidation;

/**
 *
 * @author marco
 */
public interface UserValidationInterface {
    public UserValidation getById(int id,int idusuario);
    public Collection<UserValidation> getAll();
    public boolean delete(int id);
    public int insert(UserValidation validation);
    public void update(UserValidation validation);
    public UserValidation getByIdUsuario(int idusuario);
}
