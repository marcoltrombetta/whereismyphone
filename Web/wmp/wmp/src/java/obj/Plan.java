package obj;

import model.PlanModel;

public class Plan {

	private int id;
	private int cantUsuariosLogged;
        private String desc;
        private boolean historyReport;
	private boolean realTimeTraceReport;
        private int price;

        public Plan(int id, int cantUsuariosLogged, String desc, boolean historyReport, boolean realTimeTraceReport, int price) {
            this.id = id;
            this.cantUsuariosLogged = cantUsuariosLogged;
            this.desc = desc;
            this.historyReport = historyReport;
            this.realTimeTraceReport = realTimeTraceReport;
            this.price = price;
        }

    public Plan() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantUsuariosLogged() {
        return cantUsuariosLogged;
    }

    public void setCantUsuariosLogged(int cantUsuariosLogged) {
        this.cantUsuariosLogged = cantUsuariosLogged;
    }

    public boolean isHistoryReport() {
        return historyReport;
    }

    public void setHistoryReport(boolean historyReport) {
        this.historyReport = historyReport;
    }

    public boolean getRealTimeTraceReport() {
        return realTimeTraceReport;
    }

    public void setRealTimeTraceReport(boolean realTimeTraceReport) {
        this.realTimeTraceReport = realTimeTraceReport;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
       
    public PlanModel toModel(){
          return new PlanModel(
              this.id,
              this.desc
          );
      }
}
