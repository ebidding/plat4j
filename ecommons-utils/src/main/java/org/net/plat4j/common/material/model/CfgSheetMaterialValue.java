package org.net.plat4j.common.material.model;


public class CfgSheetMaterialValue extends AbstractCfgSheetMaterialValue implements java.io.Serializable {
	  // Constructors

    /**
	 * 
	 */
	private static final long serialVersionUID = -4478161160374887418L;

	/** default constructor */
    public CfgSheetMaterialValue() {
    }

	/** minimal constructor */
    public CfgSheetMaterialValue(Long id) {
        super(id);        
    }
    
    /** full constructor */
    public CfgSheetMaterialValue(Long id, Long mainId, String isDeleted, Long agentId, String propertyDesc, String propertyName, String coumnName, String colCode) {
        super(id, mainId, isDeleted, agentId, propertyDesc, propertyName, coumnName, colCode);        
    }
   
}
