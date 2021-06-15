package wat.mobilne.renthome.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


object ImageProcesser {
    fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String, context: Context): File? { // File name like "image.png"
        //create a file to write bitmap data
        var file: File? = null
        return try {

            file = File( context.getExternalFilesDir(null).toString() + File.separator + fileNameToSave)
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitmapdata = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }
}