import com.google.gson.annotations.SerializedName



data class DataItems (

    @SerializedName("provider") val provider : String,
    @SerializedName("uid") val uid : String,
    @SerializedName("allow_password_change") val allow_password_change : Boolean,
    @SerializedName("name") val name : String,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("image") val image : String,
    @SerializedName("email") val email : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("first_name") val first_name : String,
    @SerializedName("last_name") val last_name : String,
    @SerializedName("id_number") val id_number : String,
    @SerializedName("phone_number") val phone_number : String,
    @SerializedName("date_of_birth") val date_of_birth : String,
    @SerializedName("marital_status") val marital_status : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("employed") val employed : Boolean,
    @SerializedName("musoni_id") val musoni_id : String,
    @SerializedName("customer_standing") val customer_standing : String,
    @SerializedName("status") val status : Int,
    @SerializedName("countries_id") val countries_id : String,
    @SerializedName("deleted_at") val deleted_at : String,
    @SerializedName("id") val id : String,
    @SerializedName("cold_call") val cold_call : Boolean,
    @SerializedName("referral_source") val referral_source : String,
    @SerializedName("assignee") val assignee : String
)
