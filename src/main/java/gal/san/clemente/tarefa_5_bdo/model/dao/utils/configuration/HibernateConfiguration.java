package gal.san.clemente.tarefa_5_bdo.model.dao.utils.configuration;

public class HibernateConfiguration {
    
    private String driver;
    
    private String dialect;
    
    private String HBM2DDL_AUTO;
    
    private Boolean SHOW_SQL;

    public HibernateConfiguration(String driver, String dialect, String HBM2DDL_AUTO, Boolean SHOW_SQL) {
        this.driver = driver;
        this.dialect = dialect;
        this.HBM2DDL_AUTO = HBM2DDL_AUTO;
        this.SHOW_SQL = SHOW_SQL;
    }

    public HibernateConfiguration() {
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getHBM2DDL_AUTO() {
        return HBM2DDL_AUTO;
    }

    public void setHBM2DDL_AUTO(String HBM2DDL_AUTO) {
        this.HBM2DDL_AUTO = HBM2DDL_AUTO;
    }

    public Boolean getSHOW_SQL() {
        return SHOW_SQL;
    }

    public void setSHOW_SQL(Boolean SHOW_SQL) {
        this.SHOW_SQL = SHOW_SQL;
    }
    
}
