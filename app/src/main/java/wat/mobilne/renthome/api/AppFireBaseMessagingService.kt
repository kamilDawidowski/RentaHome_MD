package wat.mobilne.renthome.api

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class AppFireBaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("TAG", "The token refreshed: $token")
    }

    private fun sendRegistrationToServer(token: String?) {
        Log.d("TAG", "sendRegistrationToServer($token)")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("TAG", "From: ${remoteMessage.from}")
        remoteMessage.notification?.let {
            Log.d("TAG", "Message Notification Title: ${it.title}")
            Log.d("TAG", "Message Notification Body: ${it.body}")
        }
    }
}