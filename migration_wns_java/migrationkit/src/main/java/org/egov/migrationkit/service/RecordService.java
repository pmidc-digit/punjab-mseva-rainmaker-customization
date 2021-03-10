package org.egov.migrationkit.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.client.model.CollectionPayment;
import io.swagger.client.model.WaterConnection;
import io.swagger.client.model.SewerageConnection;
@Transactional
@Service
public class RecordService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Transactional
	public void recordWaterMigration(WaterConnection conn,String tenantId)
	{
		
	String qry=	Sqls.waterRecord_insert;
	qry=qry.replace(":schema",tenantId);
	qry=qry.replace(":erpid", "'"+conn.getId()+"'");
	qry=qry.replace(":erpconn", "'"+conn.getConnectionNo()+"'");
	qry=qry.replace(":erppt", "'"+conn.getPropertyId()+"'");
	qry=qry.replace(":status", "'initiated'");
	qry=qry.replace(":tenantId", "'"+conn.getTenantId()+"'");
	qry=qry.replace(":digitconn", "'null'");
	qry=qry.replace(":digitpt", "'null'");
	qry=qry.replace(":addtionaldetails", "'null'");
	jdbcTemplate.execute(qry); 
		
	}
	@Transactional
	public void updateWaterMigration(WaterConnection conn, String erpId,String tenantId,String uuid)
	{
		
	String qry=	Sqls.waterRecord_update;
	qry=qry.replace(":schema",tenantId);
	qry=qry.replace(":erpid", "'"+erpId+"'");
	qry=qry.replace(":status", "'Saved'");
	qry=qry.replace(":tenantId", "'"+conn.getTenantId()+"'");
	String connNo=conn.getConnectionNo()==null?conn.getApplicationNo():conn.getConnectionNo();
	qry=qry.replace(":digitconn", "'"+connNo+"'");
	qry=qry.replace(":digitpt","'"+conn.getPropertyId()+"'");
	jdbcTemplate.execute(qry);
	
	String sql2=Sqls.ProcessContent;
	sql2=sql2.replaceAll(":id",UUID.randomUUID().toString());
	sql2=sql2.replaceAll(":tenantId", conn.getTenantId());
	sql2=sql2.replaceAll(":businessService", "NewWS1");
	sql2=sql2.replaceAll(":businessId", conn.getApplicationNo());
	
	sql2=sql2.replaceAll(":moduleName", "ws-services");
	sql2=sql2.replaceAll(":userUUID", uuid);
	long epoch=System.currentTimeMillis();
	sql2=sql2.replaceAll(":epocnow", String.valueOf(epoch));
	
	String sql3=Sqls.PROCESSINSERT;
	sql3=sql3.replaceAll(":val",sql2 );
	sql3=sql3.replaceAll(":schema",tenantId ); 
	jdbcTemplate.execute(sql3);
		
	}
	
	@Transactional
	public void updateSewerageMigration(SewerageConnection conn,String erpId,String tenantId,String uuid)
	{
		
	String qry=	Sqls.sewerageRecord_update;
	qry=qry.replace(":schema_tenantId",tenantId );
	qry=qry.replace(":erpid", "'"+erpId+"'");
	qry=qry.replace(":status", "'Saved'");
	qry=qry.replace(":tenantId", "'"+conn.getTenantId()+"'");
	qry=qry.replace(":digitconn", conn.getConnectionNo()==null?"'null'":"'"+conn.getConnectionNo()+"'");
	qry=qry.replace(":digitpt","'"+conn.getPropertyId()+"'");
	jdbcTemplate.execute(qry);
	String sql2=Sqls.ProcessContent;
	sql2=sql2.replaceAll(":id",UUID.randomUUID().toString());
	sql2=sql2.replaceAll(":tenantId", conn.getTenantId());
	sql2=sql2.replaceAll(":businessService", "NewSW1");
	sql2=sql2.replaceAll(":businessId", conn.getApplicationNo());
	sql2=sql2.replaceAll(":moduleName", "sw-services");
	sql2=sql2.replaceAll(":userUUID", uuid);
	long epoch=System.currentTimeMillis();
	sql2=sql2.replaceAll(":epocnow", String.valueOf(epoch));
	
	String sql3=Sqls.PROCESSINSERT;
	sql3=sql3.replaceAll(":schema",tenantId );
	 
	sql3=sql3.replaceAll(":val",sql2 );
	 
	jdbcTemplate.execute(sql3);
	
	}
	
	@Transactional
	public void recordSewerageMigration(SewerageConnection conn,String tenantId)
	{
	 
	String qry=	Sqls.sewerageRecord_insert;
	qry=qry.replace(":schema_tenantId",tenantId );
	qry=qry.replace(":erpid", "'"+conn.getId()+"'");
	qry=qry.replace(":erpconn", "'"+conn.getConnectionNo()+"'");
	qry=qry.replace(":erppt", "'"+conn.getPropertyId()+"'");
	qry=qry.replace(":status", "'initiated'");
	qry=qry.replace(":tenantId", "'"+conn.getTenantId()+"'");
	qry=qry.replace(":digitconn", "'null'");
	qry=qry.replace(":digitpt", "'null'");
	qry=qry.replace(":addtionaldetails", "'null'");
	jdbcTemplate.execute(qry); 
		
	}
	
	@Transactional
	public void recordWtrCollMigration(CollectionPayment conn,String tenantId)
	{
		
	String qry=	Sqls.WATER_COLLECTION_INSERT;
	qry=qry.replace(":schema", tenantId);
	
	qry=qry.replace(":erpid", "'"+conn.getId()+"'");
	qry=qry.replace(":erpconn", "'"+conn.getConsumerCode()+"'");
	qry=qry.replace(":erppt", "'"+conn.getBusinessService()+"'");
	qry=qry.replace(":status", "'initiated'");
	qry=qry.replace(":tenantId", "'"+conn.getTenantId()+"'");
	qry=qry.replace(":digitconn", "'null'");
	qry=qry.replace(":digitpt", "'null'");
	qry=qry.replace(":addtionaldetails", "'null'");
	jdbcTemplate.execute(qry); 
		
	}
	
	@Transactional
	public void recordSwgCollMigration(CollectionPayment conn,String tenantId )
	{
		
	String qry=	Sqls.SEWERAGE_COLLECTION_INSERT;
	qry=qry.replace(":schema", tenantId);
	qry=qry.replace(":erpid", "'"+conn.getId()+"'");
	qry=qry.replace(":erpconn", "'"+conn.getConsumerCode()+"'");
	qry=qry.replace(":erppt", "'"+conn.getBusinessService()+"'");
	qry=qry.replace(":status", "'initiated'");
	qry=qry.replace(":tenantId", "'"+conn.getTenantId()+"'");
	qry=qry.replace(":digitconn", "'null'");
	qry=qry.replace(":digitpt", "'null'");
	qry=qry.replace(":addtionaldetails", "'null'");
	jdbcTemplate.execute(qry); 
		
	}
	@Transactional
	public void updateWtrCollMigration(CollectionPayment conn,String tenantId)
	{
		
	String qry=	Sqls.WATER_COLLECTION_UPDATE;
	
	qry=qry.replace(":schema", tenantId);
	qry=qry.replace(":erpconn", "'"+conn.getConsumerCode()+"'");
	qry=qry.replace(":status", "'Saved'");
	qry=qry.replace(":tenantId", "'"+conn.getTenantId()+"'");
	qry=qry.replace(":digitconn", conn.getConsumerCode()==null?"'null'":conn.getConsumerCode());
	qry=qry.replace(":digitpt","'"+conn.getBusinessService()+"'");
	jdbcTemplate.execute(qry);
		
	}
	
	@Transactional
	public void updateSwgCollMigration(CollectionPayment conn)
	{
		
	String qry=	Sqls.SEWERAGE_COLLECTION_UPDATE;
	qry=qry.replace(":erpconn", "'"+conn.getConsumerCode()+"'");
	qry=qry.replace(":status", "'Saved'");
	qry=qry.replace(":tenantId", "'"+conn.getTenantId()+"'");
	qry=qry.replace(":digitconn", conn.getConsumerCode()==null?"'null'":conn.getConsumerCode());
	qry=qry.replace(":digitpt","'"+conn.getBusinessService()+"'");
	jdbcTemplate.execute(qry);
		
	}
	@Transactional
	public void initiate(String tenantId)
	{
		
	jdbcTemplate.execute("set search_path to " + tenantId);
	
	//jdbcTemplate.execute("drop table if exists egwtr_migration" + tenantId);
	jdbcTemplate.execute(Sqls.waterRecord_table);
	jdbcTemplate.execute(Sqls.PROCESSINSERTTABLE);
	}
	
	
	@Transactional
	public void initiateSewrage(String tenantId)
	{
	jdbcTemplate.execute("set search_path to " + tenantId);
	//jdbcTemplate.execute("drop table if exists egwtr_migration" + tenantId);
	jdbcTemplate.execute(Sqls.SEWERAGE_CONNECTION_TABLE);
	jdbcTemplate.execute(Sqls.PROCESSINSERTTABLE);
	}

	
	@Transactional
	public void initiateCollection(String tenantId)
	{
	jdbcTemplate.execute("set search_path to " + tenantId);
	//jdbcTemplate.execute("drop table if exists egwtr_migration" + tenantId);
	jdbcTemplate.execute(Sqls.WATER_COLLECTION_TABLE);
	}

	
	@Transactional
	public void recordError(String module,String tenantId,String message, String id) {
		String tableName=null;
		if(module.equalsIgnoreCase("water"))
			tableName="egwtr_migration";
		else if(module.equalsIgnoreCase("sewerage"))
			tableName="egswtax_migration";
		else if(module.equalsIgnoreCase("collection"))
			tableName="egwtr_cl_migration";
		String cleanedMessage="";
		if(message!=null)
		 cleanedMessage=message.replace("'", "");
			
		jdbcTemplate.execute("update "+tenantId+"."+tableName+" set errorMessage='" +cleanedMessage+ "' where erpid='"+id+"'"); 
		
	}
	@Transactional
	public void setStatus(String module ,String tenantId , String  status,String id) {
		String tableName=null;
		if(module.equalsIgnoreCase("water"))
			tableName="egwtr_migration";
		else if(module.equalsIgnoreCase("sewerage"))
			tableName="egswtax_migration";
		jdbcTemplate.execute("update "+tenantId+"."+tableName+" set status='" +status+ "' where erpid='"+id+"'"); 
		
	} 

}