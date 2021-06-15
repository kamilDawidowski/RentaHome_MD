package wat.mobilne.renthome.models

data class Registration(
    private val username: String,
    private val name: String,
    private val surname: String,
    private val email: String,
    private val password: String
)
