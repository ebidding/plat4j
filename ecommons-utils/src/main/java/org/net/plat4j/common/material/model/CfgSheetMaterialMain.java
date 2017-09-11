package org.net.plat4j.common.material.model;

public class CfgSheetMaterialMain extends AbstractCfgSheetMaterialMain implements java.io.Serializable{

    // Constructors

    /**
	 * 
	 */
	private static final long serialVersionUID = -1997106431669166309L;


	/** default constructor */
    public CfgSheetMaterialMain() {
    }

    
    /** full constructor */
    public CfgSheetMaterialMain(Long id, String tableName, String sheetCode, String isDeleted, Long agentId) {
        super(id, tableName, sheetCode, isDeleted, agentId);        
    }
}
