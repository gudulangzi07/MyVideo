package com.video.app.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: ImageUtil
 * @author: 张京伟
 * @date: 2017/7/25 17:09
 * @Description: 对图片进行压缩
 * @version: 1.0
 */
public class ImageUtil {

    /**
     * 先进行大小压缩压缩到一定比例之后进行质量压缩并处理某些手机拍照角度旋转的问题
     * @param filePath 原图路径
     * @param compressPath 压缩后的图片路径
     * @throws FileNotFoundException
     */
    public static boolean compressImage(String filePath, String compressPath) throws Exception {
        Bitmap bitmap = getBitmapFromFile(filePath, 720, 1280);
        return saveBitmapToLocal(bitmap, compressPath);
    }

    private static Bitmap getBitmapFromFile(String path, int width, int height) throws Exception {
        BitmapFactory.Options opts = null;
        if (path != null) {
            if (width > 0 && height > 0) {
                opts = new BitmapFactory.Options();
                ////设置为true, 加载器不会返回图片, 而是设置Options对象中以out开头的字段.即仅仅解码边缘区域
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(path, opts);
                final int minSideLength = Math.min(width, height);
                //设置位图缩放比例
                opts.inSampleSize = computeSampleSize(opts, minSideLength, width * height);
                // 指定加载可以加载出图片.
                opts.inJustDecodeBounds = false;
                //为位图设置100K的缓存
                opts.inTempStorage = new byte[100 * 1024];
                //设置位图颜色显示优化方式
                opts.inPreferredConfig = Bitmap.Config.RGB_565;
                //设置图片可以被回收，创建Bitmap用于存储Pixel的内存空间在系统内存不足时可以被回收
                opts.inPurgeable = true;
                //设置解码位图的尺寸信息
                opts.inInputShareable = true;
            }
            //解码位图
            Bitmap thbBitmap = BitmapFactory.decodeFile(path, opts);
            //获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
            int degree = readPictureDegree(path);
            Bitmap rotateBitmap = rotateImageView(degree, thbBitmap);
            return rotateBitmap;
        } else return null;
    }

    private static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math
                .floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    private static Bitmap rotateImageView(int angle, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    private static int readPictureDegree(String path) throws Exception {
        int degree = 0;
        ExifInterface exifInterface = new ExifInterface(path);
        int orientation = exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL);
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                degree = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                degree = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                degree = 270;
                break;
        }
        return degree;
    }

    /**
     * 根据bitmap保存图片到本地
     *
     * @param bitmap
     * @return
     */
    public static boolean saveBitmapToLocal(Bitmap bitmap, String filePath) {
        if (null == bitmap) {
            return false;
        }
        FileOutputStream fileOutput = null;
        try {
            File fileDir = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File imgFile = new File(filePath);
            if (!imgFile.exists())
                imgFile.createNewFile();
            fileOutput = new FileOutputStream(imgFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, fileOutput);
            fileOutput.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != fileOutput) {
                try {
                    fileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 写图片文件到SD卡
     * @param filePath 图片保存路径
     * @param bytes 图片流文件
     * @throws IOException
     */
    public static boolean saveImageToSD(String filePath, byte[] bytes) {
        if (null == bytes){
            return false;
        }

        FileOutputStream fos = null;
        try {
            if (bytes != null) {
                File fileDir = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                File imgFile = new File(filePath);
                if (!imgFile.exists()) {
                    imgFile.createNewFile();
                }
                fos = new FileOutputStream(filePath);
                fos.write(bytes);
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    /**
     * @param context
     * @param uri
     * @return the file path or null
     * 根据uri获取图片的绝对路径
     * */
    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 将图片转换成byte[]流文件
     * @param path
     * @return byte[]字符串流
     */
    public static byte[] imageTobytes(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        InputStream is = null;
        byte[] data = new byte[1024];
        try{
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null !=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return data;
    }
}
