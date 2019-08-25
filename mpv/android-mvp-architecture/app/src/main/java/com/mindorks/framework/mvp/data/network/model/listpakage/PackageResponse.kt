package com.mindorks.framework.mvp.data.network.model.listpakage

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class PackageResponse(

	@field:SerializedName("rc")
	val rc: Int? = null,

	@field:SerializedName("rs")
	val rs: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("cmd")
	val cmd: String? = null,

	@field:SerializedName("oID")
	val oID: String? = null
){
	data class DataItem(

			@field:SerializedName("C_TERM_DAY")
			val cTERMDAY: Int? = null,

			@field:SerializedName("C_REGIS_DATE")
			val cREGISDATE: String? = null,

			@field:SerializedName("C_CARD_LIMIT")
			val cCARDLIMIT: Int? = null,

			@field:SerializedName("C_CURRENT_DATE")
			val cCURRENTDATE: String? = null,

			@field:SerializedName("ROW_NUM")
			val rOWNUM: Int? = null,

			@field:SerializedName("C_EXPIRE_RENEW_DATE")
			val cEXPIRERENEWDATE: String? = null,

			@field:SerializedName("C_CARD_STATUS")
			val cCARDSTATUS: String? = null,

			@field:SerializedName("C_SERIAL_NUMBER")
			val cSERIALNUMBER: String? = null,

			@field:SerializedName("C_ACCOUNT_CODE")
			val cACCOUNTCODE: String? = null,

			@field:SerializedName("C_REMAIN_DAY")
			val cREMAINDAY: Int? = null,

			@field:SerializedName("C_CARD_VALUE")
			val cCARDVALUE: Int? = null,

			@field:SerializedName("C_RENEWER_RATE")
			val cRENEWERRATE: Int? = null,

			@field:SerializedName("C_TOTAL_RECORD")
			val cTOTALRECORD: Int? = null,

			@field:SerializedName("C_TRANSFER_DATE")
			val cTRANSFERDATE: String? = null,

			@field:SerializedName("C_CARD_STATUS_NAME")
			val cCARDSTATUSNAME: String? = null,

			@field:SerializedName("C_CARD_CODE")
			val cCARDCODE: String? = null,

			@field:SerializedName("C_TRANSFER_FEE")
			val cTRANSFERFEE: Int? = null,

			@field:SerializedName("C_ACCOUNT_APPLY")
			val cACCOUNTAPPLY: String? = null,

			@field:SerializedName("C_EXPIRE_DATE")
			val cEXPIREDATE: String? = null,

			@field:SerializedName("C_ALLOW_TRANFER")
			val cALLOWTRANFER: Int? = null,

			@field:SerializedName("C_ALLOW_RENEW")
			val cALLOWRENEW: Int? = null,

			@field:SerializedName("C_CARD_BALANCE")
			val cCARDBALANCE: Int? = null,

			@field:SerializedName("C_CUSTOMER_CODE")
			val cCUSTOMERCODE: String? = null,

			@field:SerializedName("C_RENEWER_FEE")
			val cRENEWERFEE: Int? = null
	)
}