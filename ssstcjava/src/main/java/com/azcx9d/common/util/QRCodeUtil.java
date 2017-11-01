package com.azcx9d.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by chenxl on 2017/4/5 0005.
 */
public class QRCodeUtil {

    /**
     * 获取二维码流
     * @param content 二维码表示内容
     * @param stream 输出流
     * @param height 生成二维码高度
     * @param width 生成二维码宽度
     * */
    public static void getQRCode(String content, OutputStream stream, int height, int width) throws WriterException, IOException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix m = writer.encode(content, BarcodeFormat.QR_CODE, height, width);
        MatrixToImageWriter.writeToStream(m, "png", stream);
    }
}
