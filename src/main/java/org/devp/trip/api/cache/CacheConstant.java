/**
 * 
 */
package org.devp.trip.api.cache;

import lombok.extern.slf4j.Slf4j;

/**
 * @author amdad
 *
 */
@Slf4j
public class CacheConstant {
	
	public enum CAMPAIGN_CONTACT_PROCESS {
		CONTACT_UNSUB, CONTACT_PAUSE, CONTACT_RESUME, CONTACT_STOP //, CONTACT_REMOVE, CONTACT_ADD, SCHEDULE_FOR_NEXT_PRIORITY, SCHEDULE_NEW_CONTACT we will add those in future
	}
	
    public static final int CAMPAIGN_CONTACT_PROCESS_MAX_PERIOD_SECONDS = Integer.parseInt(getEnvVar("CAMPAIGN_CONTACT_PROCESS_MAX_PERIOD_SECONDS", "600"));
    
    public static final int CAMPAIGN_SETTING_PROCESS_MAX_PERIOD_SECONDS = Integer.parseInt(getEnvVar("CAMPAIGN_SETTING_PROCESS_MAX_PERIOD_SECONDS", "3600"));
    
    public static final int API_RATE_LIMIT_MAX_PERIOD_SECONDS = Integer.parseInt(getEnvVar("API_RATE_LIMIT_MAX_PERIOD_SECONDS", "60"));
    
    public static final int API_RATE_LIMIT_MAX_REQUEST = Integer.parseInt(getEnvVar("API_RATE_LIMIT_MAX_REQUEST", "10"));
    
    public static final boolean API_RATE_LIMIT_ONLY_AUTH_KEY = Boolean.parseBoolean(getEnvVar("API_RATE_LIMIT_ONLY_AUTH_KEY", "true"));
    
    public static final int API_RATE_LIMIT_MAX_REQUEST_PUBLIC = Integer.parseInt(getEnvVar("API_RATE_LIMIT_MAX_REQUEST_PUBLIC", "15"));
    
    public static final boolean API_RATE_LIMIT_ONLY_CLIENT_IP = Boolean.parseBoolean(getEnvVar("API_RATE_LIMIT_ONLY_CLIENT_IP", "false"));

	
	public enum CAMPAIGN_PROCESS {
		START_DRIP, STOP_DRIP, PAUSE_DRIP, RESUME_DRIP
	}
	
	/**
	 * 
	 */
	private CacheConstant() {
		log.info("CacheConstant"); 
	}
	
    private static String getEnvVar(String key, String defaultValue) {
    	
    	
     	if (System.getenv(key) != null 
           		&& !System.getenv(key).isEmpty()) {
               return System.getenv(key);
	   	}
     	
		return defaultValue;
 	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("CacheConstant"); 
	}
	
	public static final String AGENCY_THIRDA_PARTY_CREDENTIAL_KEY = "thirdpartycredential:agnency:servicetype:";
	public static final String DEFAULT_THIRDA_PARTY_CREDENTIAL_KEY = "thirdpartycredential:servicetype:";
	
	public static final String USER_PUB_API_KEY_HASH = "users:pubapikeys:";
	public static final String USER_AUTHTOKEN_HASH = "users:authtokens:";
	public static final String USER_ID_HASH = "users:ids:";
	public static final String BATCH_APIKEY_HASH = "batchs:apikeys:";
	public static final String BATCH_ID_HASH = "batchs:ids:";
	
	public static final String CAMPAIGN_APIKEY_HASH = "campaigns:apikeys:";
	public static final String CAMPAIGN_ID_HASH = "campaigns:ids:";
	public static final String CAMPAIGN_NUMBER_ID_HASH = "campaigns:number:ids:";
	
	public static final String CAMPAIGN_SETTING_ID_HASH = "campaignsettings:ids:";
	
	public static final String AGENCY_ID_KEY_HASH = "agency:ids:keys:";
	
	public static final String AGENCY_ID_HASH = "agency:ids:";
	
	public static final String AGENCY_ADMIN_ID_HASH = "agency:ids:admin:";
	
	public static final String AGENCY_ADMIN_KEY_HASH = "agency:keys:admin:";
	
	public static final String SUPER_ADMIN_API_KEY_HASH = "super:admin:apikeys:";
	
	public static final String AGENCY_ID_CREDITKEY_HASH = "agency:ids:creditkeys:";
	
	public static final String USER_TWILIO_SUBACCOUNT_KEY_HASH = "user_twilio_subaccount:ids:";
	
	public static final String USERSETTING_KEY_HASH = "usersettings:ids:keys:";
	
	public static final String FORM_AUTHTOKEN_HASH = "forms:authtokens:";
	
	public static final String FORM_USER_ID_HASH = "forms:users:ids:";
	
	public static final String CAMPAIGN_ID_PROCESS_HASH = "campaigns:ids:process:";
	
	public static final String CAMPAIGN_CONTACT_PROCESS_HASH = "campaigns:contacts:process:";
	
	public static final String CAMPAIGN_CONTACT_RUNNING_PROCESS_HASH = "campaigns:contacts:running:process:";
	
	public static final String ELASTIC_HARD_SYNC_PROCESS_HASH = "elastichardsync:process";
	
	public static final String ELASTIC_MODEL_SYNC_PROCESS_HASH = "elasticmodelsync:process:";
	
	public static final String ELASTIC_MODEL_ACTION_PROCESS_HASH = "elasticmodelaction:process:";
	
	public static final String CAMPAIGN_SETTING_PROCESS_HASH = "campaigns:settings:process:";
	
	public static final String CAMPAIGN_SETTING_RUNNING_PROCESS_HASH = "campaigns:settings:running:process:";
	
	public static final String CACHE_TRIGGER_QUEUE_LIST_KEY = "cache:trigger:queue:list:key";
	
	public static final String CACHE_BATCH_CONTACT_LIST_KEY = "cache:batch:contact:list:key";
	
	public static final String CACHE_CAMPAIGN_FLOW_REQUEST_LIST_KEY = "cache:campaign:flow:request:list:key";
	
	public static final String CACHE_CONTACT_FLOW_LIST_KEY = "cache:contact:flow:list:key";
	
	public static final String CACHE_ELASTIC_PROCESS_QUEUE_LIST_KEY = "cache:elastic:process:queue:list:key";
	
	public static final String CACHE_FORM_DATA_FLOW_REQUEST_LIST_KEY = "cache:form:data:flow:request:list:key";
	
	public static final String CACHE_GATEWAY_LOG_LIST_KEY = "cache:gateway:log:list:key";
	
	public static final String CACHE_LEAD_FLOW_REQUEST_LIST_KEY = "cache:lead:flow:request:list:key";
	
	public static final String CACHE_LEAD_NOTIFY_LIST_KEY = "cache:lead:notify:list:key";
	
	public static final String CACHE_MODEL_DELETE_QUEUE_LIST_KEY = "cache:model:delete:queue:list:key";
	
	public static final String CACHE_TRIGGER_ACTION_QUEUE_LIST_KEY = "cache:trigger:action:queue:list:key";
	
	public static final String CONTACT_CAMPAIGN_HISOTRY_LIST_KEY = "contact:campaign:history:list:key";
	
	public static final String CONTACT_TRIGGER_HISOTRY_LIST_KEY = "contact:trigger:history:list:key";
	
	public static final String 	ERROR_MESSAGE_ID_HASH = "errormessages:ids:";
	
	public static final String 	ERROR_MESSAGE_WITH_CODE_PROCESS_KEY = "errormessage:errorcode:contenttype:gatewaytype";
	
	public static final String 	GATEWAY_ERROR_MESSAGE_PROCESS_KEY = "errormessage:contenttype:gatewaytype:message";
	
	public static final String TENANT_AGENCY_ID_HASH = "tenant:agency:ids:";
	
	public static final String TENANT_ID_HASH = "tenant:ids:";
	
	public static final String MULTI_TENANT_AGENCY_ID_HASH = "multi:tenant:agency:ids:";
	
	public static final String FORM_API_KEY_HASH = "form:api:keys";
	
	public static final String AGENCY_SUBUSER_CATEGORY_SPECIFIC_LOG_HAS_KEY = "agency:subuser:category:specificlog:";
	
	public static final String BILLING_SID_TYPE_HAS_KEY = "billing:sid:type:";
	
	public static final String BILLING_DEACTIVATION_REQUEST_HASH_KEY = "billing:deactivation:agency:subscriberid:";
	
	public static final String BILLING_REACTIVATION_REQUEST_HASH_KEY = "billing:reactivation:agency:subscriberid:";
	
	public static final String BILLING_TOPUP_REQUEST_HASH_KEY = "billing:topup:agency:subscriberid:";
	
	public static final String BILLING_DEDUCTION_REQUEST_HASH_KEY = "billing:deduction:agency:user:subscriberid:";
	
	public static final String BILLING_DEDUCTION_REQUEST_QUEUE_HASH_KEY = "billing:deduction:subscriberid:";
	
	public static final String APPINSTANCE_KEY_HASH = "app:instance:";
	
	public static final String APPINSTANCES_KEY_HASH = "app:instances:";
	
	public static final String BILLING_USAGE_AGENCY_USER_HAS_KEY = "billing:usage:agency:user:";
	
	public static final boolean ENABLE_CACHE = getEnvVar("ENABLE_CACHE", Boolean.toString(true)).equalsIgnoreCase("true");

	public static final String AGENCY_CREDIT_SETTING_CATEGORY_HASH = "agencycreditsettings:id:categoryname:";
	
	public static final String AGENCY_CARRIER_LOOKUP_INFO_AGENCYKEY_HASH = "agency:carrier:lookupinfo:agencykey:";

	public static final String PARTITION_CONFIGURATIONS_PARTITION_ID_HASH = "partitionconfiguration:paritionid:";

	public static final String CONTACT_FORWARD_QUEUE_HASH_KEY = "contactforwardqueue:campaignid:contactid:";
	
	public static final String A2P_USER_ID_KEY_HASH = "a2p:user-id:";

	public static final String AGENCY_SERVICE_INFORMAION_AGENCYKEY_HASH = "agency:service:information:agencykey:";
	
	public static final String MESSAGE_TEMPLATE_MESSAGE_HASH = "messagetype:module:action:";
	
	public static final String MESSAGE_TEMPLATE_SUBJECT_HASH = "messagetype:module:action:subject:";
	
	public static final String POSTMARK_RATE_LIMIT = "postmark:rate-limit:agency-id:";
}
