package interfaces;

import java.util.Collection;
import obj.Menu;

/**
 *
 * @author marco
 */
public interface MenuInterface {
    Menu getById(int id);
    Collection<Menu> getMenuByPlanByRole(int idplan,int idrole);
    Collection<Menu> getMenuByPlanByRoleParentItems(int parentid,int idplan,int idrole);
    Collection<Menu> getAll();
    boolean delete(int id);
    int insert(Menu menu);
    void update(Menu menu);
}
