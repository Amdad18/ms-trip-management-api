/**
 * 
 */
package org.devp.trip.api.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author amdad
 *
 */
@Slf4j
public final class ConnectionUtil {

	private static Map<String, HikariDataSource> tenantMasterDataSource = new HashMap<>();
	
	private static Map<String, HikariDataSource> tenantReplicaDataSource = new HashMap<>();
	
	private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	
	private static final String CACHE_PREP_STMTS = "cachePrepStmts";
	
	private static final String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
	
	private static final String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
	
	private static int tenantId = 0;
	
	/**
	 * 
	 */
	private ConnectionUtil() {

	}
	 
	public static void main(String[] args) throws SQLException {
		System.out.println("ConnectionUtil : Read Connection " + ConnectionUtil.getReadConnection()); 
		System.out.println("ConnectionUtil : Write Connection " + ConnectionUtil.getWriteConnection()); 
	}
	
	private static String buildTenantDataSourceKey() {
		return new StringBuilder().append("tenant:").append(tenantId).toString();  
	}
	
	public static String buildMasterJdbcUrl() {
		
		StringBuilder masterJdbcUrlBuilder = new StringBuilder(); 
		//masterJdbcUrlBuilder.append("jdbc:mysql:loadbalance://");
		masterJdbcUrlBuilder.append("jdbc:mysql://");
		masterJdbcUrlBuilder.append("localhost" + ":" +"3306");
		masterJdbcUrlBuilder.append("/");
		masterJdbcUrlBuilder.append("trip_management");
		masterJdbcUrlBuilder.append("?zeroDateTimeBehavior=CONVERT_TO_NULL&createDatabaseIfNotExist=true&characterEncoding=UTF-8&useUnicode=yes"
				+ "&character_set_server=utf8mb4&logger=Slf4JLogger&explainSlowQueries=true&profileSQL=true&allowSlavesDownConnections=true"
				+ "&readFromMasterWhenNoSlaves=true&loadBalanceStrategy=roundrobin&loadBalanceBlacklistTimeout=5000"
				+ "&loadBalanceConnectionGroup=first&ha.enableJMX=true");
		
		return masterJdbcUrlBuilder.toString().trim();
	}
	
	private static void buildDataSource() {
		
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(buildMasterJdbcUrl());
		config.setUsername("root");
		config.setPassword("admin");
		config.setPoolName("trip_management_db_master_pool");
		
		config.setConnectionTimeout(300000);
		config.setMaximumPoolSize(50);
		config.setIdleTimeout(120000);
		config.setLeakDetectionThreshold( 300000);
		config.setDriverClassName(DRIVER_CLASS_NAME);
		config.setMinimumIdle(5);
		config.addDataSourceProperty(CACHE_PREP_STMTS, "true");
		config.addDataSourceProperty(PREP_STMT_CACHE_SIZE, "250");
		config.addDataSourceProperty(PREP_STMT_CACHE_SQL_LIMIT, "2048");
		
		HikariDataSource ds = new HikariDataSource(config);
		
		tenantMasterDataSource.put(buildTenantDataSourceKey(), ds); 
		
	}

	public static Connection getReadConnection() throws SQLException { 
		HikariDataSource localDS = tenantReplicaDataSource.get(buildTenantDataSourceKey()); 
		if (localDS == null) {
			synchronized (ConnectionUtil.class) {
				if(null == localDS) {
					log.debug("Initializing HikariDataSource ");
					buildDataSource();
					localDS = tenantReplicaDataSource.get(buildTenantDataSourceKey()); 
					log.debug("Initialization HikariDataSource Complete : "+localDS); 
				}
			}
		}
		return null == localDS ?  null : localDS.getConnection();
	}
	
	public static Connection getWriteConnection() throws SQLException { 
		HikariDataSource localDS = tenantMasterDataSource.get(buildTenantDataSourceKey()); 
		if (localDS == null) {
			synchronized (ConnectionUtil.class) {
				if(null == localDS) {
					log.debug("Initializing HikariDataSource "); 
					buildDataSource();
					localDS = tenantMasterDataSource.get(buildTenantDataSourceKey()); 
					log.debug("Initialization HikariDataSource Complete : "+localDS); 
				}
			}
		}
		return null == localDS ?  null : localDS.getConnection();
	}

}
