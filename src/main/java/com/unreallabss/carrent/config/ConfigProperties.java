package com.unreallabss.carrent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("app")
@Configuration
public class ConfigProperties {

	private Auth auth;

	private Crypto crypto;
	
	private PlatformUserActivation platformUserActivation;

	private Endpoint endpoint;

	private PlatformUserPasswordReset platformUserPasswordReset;
	
	private ExternalEndpoint externalEndpoint;

	@Data
	public static class Auth {

		private Integer defaultAccessTokenTimeout;

		private Integer defaultRefreshTokenTimeout;
		
		private String kfName;
		
		private String ksPass;
		
		private String resourceId;

		private String corsAllowedOrigins;
		
		private Integer maxFailedLoginAttemptsForAccountLock;
		
		private Long failedLoginAttemptAccountLockTimeout;
		
	}

	@Data
	public static class Crypto {

		private String password;

		private String salt;
	}
	
	@Data
	public static class PlatformUserActivation {
		
		private String baseLink;
		
		private Long linkValidityTime;
	}

	@Data
	public static class Endpoint {


		private String context;

		private String api;
		
		private String authToken;
		
		private String logout;

		private String authoritiesSearch;

		private String userRolesCreate;

		private String userRolesSearch;

		private String userRolesView;

		private String userRolesUpdate;

		private String userRolesDelete;

		private String userRolesSuggestion;

		private String usersCreate;

		private String usersSearch;
		
		private String usersView;

		private String usersUpdate;

		private String usersDelete;

		private String countriesSuggestion;
		
		private String usersActivationLinkValidation;
		
		private String usersActivation;
		
		private String userProfileView;
		
		private String userProfileUpdate;
		
		private String changePassword;
		
		private String resetPasswordRequest;
		
		private String resetPassword;
		
		private String userListDelete;
		
		private String userRoleListDelete;

		private String userByIds;

		private String customersCreate;

		private String customersSearch;

		private String customersSearchExactMatch;

		private String customersView;

		private String customersUpdate;

		private String customersDelete;

		private String customersSuggestion;

		private String companiesCreate;

		private String companiesSearch;

		private String companiesView;

		private String companiesUpdate;

		private String companiesDelete;

		private String companiesSuggestion;

		private String branchesCreate;

		private String branchesSearch;

		private String branchesView;

		private String branchesUpdate;

		private String branchesDelete;

		private String branchesSuggestion;

		private String suppliersCreate;

		private String suppliersSearch;

		private String suppliersView;

		private String suppliersUpdate;

		private String suppliersDelete;

		private String suppliersSuggestion;

		private String measurementsCreate;

		private String measurementsSearch;

		private String measurementsView;

		private String measurementsUpdate;

		private String measurementsDelete;

		private String measurementsSuggestion;

		private String stockItemUpdate;
		private String stockItemSearch;

		private String stocksCreate;
		private String stocksSearch;
		private String stocksView;
		private String stocksUpdate;
		private String stocksBulkUpdate;
		private String stocksDelete;
		private String stocksSplit;
		private String stocksTransfer;
		private String stocksSuggestion;
		private String stocksSummary;
		private String stocksViewByBarcode;
		private String stocksMaterialTypeTransfer;
		private String stocksLotTransfer;
		private String availableStocksSearch;
		private String stocksReportByBin;
		private String stocksSummaryByPackingList;
		private String stocksReportBySupplier;
		private String stocksReportBySupplierPdf;
		private String stocksReportBySupplierSummaryPdf;
		private String stocksReportByBinPdf;
		private String stocksReportByBinSummaryPdf;
		private String stockInOutSummaryReport;

		private String itemsCreate;

		private String itemsSearch;

		private String itemsView;

		private String itemsUpdate;

		private String itemsDelete;

		private String imagesCreate;

		private String imagesSearch;

		private String imagesView;

		private String imagesUpdate;

		private String imagesDelete;

		private String itemsSuggestion;

		private String itemsUnitPrice;

		private String rolesCreate;
		private String rolesSearch;
		private String rolesView;
		private String rolesUpdate;
		private String rolesDelete;
		private String rolesSuggestion;

		private String packingTypesCreate;
		private String packingTypesSearch;
		private String packingTypesView;
		private String packingTypesUpdate;
		private String packingTypesDelete;
		private String packingTypesSuggestion;

		private String lotNumbersCreate;
		private String lotNumbersSearch;
		private String lotNumbersView;
		private String lotNumbersUpdate;
		private String lotNumbersDelete;
		private String lotNumbersSuggestion;

		private String addressesCreate;
		private String addressesSearch;
		private String addressesView;
		private String addressesUpdate;
		private String addressesDelete;
		private String addressesLink;

		private String binsSubCreate;
		private String binsCreate;
		private String binsAssign;
		private String binsSearch;
		private String binsView;
		private String binsUpdate;
		private String binsAllUpdate;
		private String binsDelete;
		private String binsSuggestion;
		private String binsViewParent;
		private String bulkBinsCreate;
		private String binsAddToParent;
		private String binsRemoveFromParent;
		private String binsBarcodePrintPdf;
		private String binsExportExcel;
		private String binsItemsSearch;
		private String binsSearchWithPaging;

		private String stockPreReceiptCreate;
		private String stockPreReceiptSearch;
		private String stockPreReceiptView;
		private String stockPreReceiptUpdate;
		private String stockPreReceiptLineUpdate;
		private String stockPreReceiptDelete;
		private String stockPreReceiptLineDelete;
		private String stockPreReceiptLineItemDelete;
		private String stockPreReceiptPrintPdf;
		private String stockPreReceiptBarcodePrintPdf;
		private String stockPreReceiptExcelStocksInfo;
		private String stockPreReceiptCsvStocksInfo;
		private String stockPreReceiptExportExcel;

		private String stockReceiptCreate;
		private String stockReceiptView;

		private String stockTypesCreate;
		private String stockTypesSearch;
		private String stockTypesView;
		private String stockTypesUpdate;
		private String stockTypesDelete;

		private String stockIssueRequestCreate;
		private String stockIssueRequestView;
		private String stockIssueRequestSearch;
		private String stockIssueRequestLineSearch;
		private String stockIssueRequestSummarySearch;
		private String stockIssueRequestUpdate;
		private String stockIssueRequestLineUpdate;
		private String stockIssueRequestUpdateStatus;
		private String stockIssueRequestDelete;
		private String stockIssueRequestLineDelete;
		private String stockIssueRequestLineItemDelete;
		private String stockIssueRequestSuggestion;
		private String stockIssueRequestPrintPdf;
		private String stockIssueAvailableStockPdf;
		private String stockIssueRequestConfirm;
		private String stockIssueConfirm;
		private String stockIssueExportExcel;

		private String stockIssueCreate;
		private String stockIssueView;
		private String stockIssueSearch;
		private String stockIssueUpdate;
		private String stockIssueUpdateVehicle;
		private String stockIssueDeliveryUpdate;
		private String stockIssueDelete;
		private String stockIssueLineDelete;
		private String stockIssueLineItemDelete;
		private String stockIssuePrintPdf;
		private String stockIssuePrintExcel;
		private String stockIssuePrintMudPdf;

		private String stockClassificationCreate;
		private String stockClassificationView;
		private String stockClassificationSearch;
		private String stockClassificationUpdate;
		private String stockClassificationDelete;
		private String stockClassificationSuggestion;

		private String availableStocksReport;
		private String availableStocksReportExportExcel;
		private String stockInOutSummaryReportExportExcel;
		private String availableStocksReportExportBarcodeExcel;

		private String vehicleTypesCreate;
		private String vehicleTypesSuggestion;

		private String departmentsCreate;
		private String departmentsSearch;
		private String departmentsView;
		private String departmentsUpdate;
		private String departmentsDelete;
		private String departmentsSuggestion;

		private String machinesCreate;
		private String machinesSearch;
		private String machinesView;
		private String machinesUpdate;
		private String machinesDelete;
		private String machinesSuggestion;

		private String vehiclesCreate;
		private String vehiclesSearch;
		private String vehiclesView;
		private String vehiclesUpdate;
		private String vehiclesDelete;
		private String vehiclesSuggestion;

		private String sbuCreate;
		private String sbuSearch;
		private String sbuView;
		private String sbuUpdate;
		private String sbuDelete;
		private String sbuAging;

		private String zonesCreate;
		private String zonesSearch;
		private String zonesView;
		private String zonesUpdate;
		private String zonesDelete;
		private String zonesAging;

		private String subLocationsCreate;
		private String subLocationsSearch;
		private String subLocationsView;
		private String subLocationsUpdate;
		private String subLocationsDelete;
		private String subLocationsBinUpdate;
		private String subLocationsBinsDelete;
		private String subLocationsBinDelete;

		/*private String subLocationsBinsSearch;
		private String subLocationsBinsCreate;
		private String subLocationsBinsDelete;*/

		private String stockCheckCreate;
		private String stockItemCheck;
		private String stockCheckVerifyPallet;
		private String stockCheckVerifyBin;
		private String stockCheckLocationsCreate;
		private String stockCheckSearch;
		private String stockCheckView;
		private String stockCheckDelete;
		private String stockCheckUpdate;
		private String stockCheckPdf;
		private String stockCheckSummaryPdf;
		private String stockCheckExcel;
		private String stockCheckPalletView;
		private String stockCheckItemView;
		private String stockCheckItemSearch;
		private String stockCheckLocationView;
		private String stockCheckLocationUpdate;
		private String stockCheckLocationCountCreate;
		private String stockCheckLocationCountUpdate;
		private String stockCheckLocationPdf;
		private String stockCheckAdjustStocks;
		private String stockCheckSignOff;
		private String stockCheckLocation;
		private String stockCheckLocationCountSave;

	}

	@Data
	public static class PlatformUserPasswordReset {

		private String baseLink;
	}
	
	@Data
	public static class ExternalEndpoint {
		
		private String supportingLanguages;

		private String languageById;

		private String languageByTag;
		
        private String preferredLanguageByUserId;

	}
}
