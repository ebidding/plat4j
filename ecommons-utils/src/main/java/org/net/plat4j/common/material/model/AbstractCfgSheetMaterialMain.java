package org.net.plat4j.common.material.model;

import java.io.Serializable;

public class AbstractCfgSheetMaterialMain implements Serializable{
	  // Fields    

    /**
	 * 
	 */
	private static final long serialVersionUID = -5809008406727706935L;
	private Long id;
    private String tableName;
    private String sheetCode;
    private String isDeleted;
    private Long agentId;


   // Constructors

   /** default constructor */
   public AbstractCfgSheetMaterialMain() {
   }

   
   /** full constructor */
   public AbstractCfgSheetMaterialMain(Long id, String tableName, String sheetCode, String isDeleted, Long agentId) {
       this.id = id;
       this.tableName = tableName;
       this.sheetCode = sheetCode;
       this.isDeleted = isDeleted;
       this.agentId = agentId;
   }

  
   // Property accessors

   public Long getId() {
       return this.id;
   }
   
   public void setId(Long id) {
       this.id = id;
   }

   public String getTableName() {
       return this.tableName;
   }
   
   public void setTableName(String tableName) {
       this.tableName = tableName;
   }

   public String getSheetCode() {
       return this.sheetCode;
   }
   
   public void setSheetCode(String sheetCode) {
       this.sheetCode = sheetCode;
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
  




}
