package pl.ostrowskiartur.szkolatoledo.model

class User {

    var email: String? = null
    var fullName: String? = null
    @field:JvmField
    var isMember: Boolean = false
    
    override fun toString(): String {
        return "User(email=$email, fullName=$fullName, isMember=$isMember)"
    }

}