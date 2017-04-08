package interfaces;

import java.util.Collection;
import obj.*;

public interface LocationBookmarkInterface {
    public Collection<LocationBookmark> getAll();
    public LocationBookmark getById(int id);
    public boolean delete(int id);
    public int insert(LocationBookmark role);
    public void update(LocationBookmark role);
    public Collection<LocationBookmark> getByIdPhoneInfo(int IdPhoneInfo);
}
