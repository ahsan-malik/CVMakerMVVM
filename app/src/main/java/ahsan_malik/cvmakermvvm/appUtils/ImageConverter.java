package ahsan_malik.cvmakermvvm.appUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class ImageConverter {

    @TypeConverter
    public static byte[] bitMapToBlob(Bitmap bitmap){
        if (bitmap == null)
            return null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    @TypeConverter
    public static Bitmap blobToBitmap(byte[] byteArray){
        return byteArray == null ? null : BitmapFactory.decodeByteArray(byteArray , 0, byteArray.length);
    }
}
