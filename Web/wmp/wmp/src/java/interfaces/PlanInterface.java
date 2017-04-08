package interfaces;

import java.util.Collection;
import obj.Plan;

/**
 *
 * @author marco
 */
public interface PlanInterface {
    Collection<Plan> getAll();
    Plan getById(int id);
    boolean delete(int id);
    int insert(Plan plan);
    void update(Plan plan);
}
