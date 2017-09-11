package org.net.plat4j.common.material.model;



/**
 * AbstractCfgSheetMaterialValue entity provides the base persistence definition of the CfgSheetMaterialValue entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCfgSheetMaterialValue  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1813279396064456869L;
	private Long id;
     private Long mainId;
     private String isDeleted;
     private Long agentId;
     private String propertyDesc;
     private String propertyName;
     private String coumnName;
     private String colCode;


    // Constructors

    /** default constructor */
    public AbstractCfgSheetMaterialValue() {
    }

	/** minimal constructor */
    public AbstractCfgSheetMaterialValue(Long id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractCfgSheetMaterialValue(Long id, Long mainId, String isDeleted, Long agentId, String propertyDesc, String propertyName, String coumnName, String colCode) {
        this.id = id;
        this.mainId = mainId;
        this.isDeleted = isDeleted;
        this.agentId = agentId;
        this.propertyDesc = propertyDesc;
        this.propertyName = propertyName;
        this.coumnName = coumnName;
        this.colCode = colCode;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainId() {
        return this.mainId;
    }
    
    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public String getIsDeleted() {
        return this.isDeleted;
    }
    
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getAgentId() {
        return this.agentId;
    }
    
    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getPropertyDesc() {
        return this.propertyDesc;
    }
    
    public void setPropertyDesc(String propertyDesc) {
        this.propertyDesc = propertyDesc;
    }

    public String getPropertyName() {
        return this.propertyName;
    }
    
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getCoumnName() {
        return this.coumnName;
    }
    
    public void setCoumnName(String coumnName) {
        this.coumnName = coumnName;
    }

    public String getColCode() {
        return this.colCode;
    }
    
    public void setColCode(String colCode) {
        this.colCode = colCode;
    }

}
