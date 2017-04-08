package interfaces;

import java.util.Collection;
import obj.PositionInfo;
import obj.PositionReport;

public interface PositionInfoInterface {
    public Collection<PositionInfo> getByIdUsuario(int idusuario, int cant);
    public PositionInfo getByIdUsuarioByImei(int idusuario, String imei);
    public boolean delete(int id, int idphoneinfo);
    public int insert(PositionInfo positionInfo);
    public void update(PositionInfo positionInfo);
    
    public Collection<PositionInfo> getByIdUsuarioByImei_Hist(int idusuario, String imei);
    public Collection<PositionInfo> getByIdUsuarioById_Hist(int idusuario, int id, String date);
    public Collection<PositionInfo> getByIdUsuarioByDateByPhoneInfo_Hist(int idusuario, String dateFrom, String dateTo, int idphoneinfo);
    public int insert_Hist(PositionInfo positionInfo);
    public void update_Hist(PositionInfo positionInfo);
    
    public Collection<PositionReport> getPositionReport_Hist(int idusuario,String dateFrom, String dateTo, int idphoneinfo);
}
