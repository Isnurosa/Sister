/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compres20Okt;

import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author Rosa
 */
public class DataCompressionExample {
    public static byte[] compressData(byte[] data) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)) {
            gzipOutputStream.write(data);
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressData(byte[] compressedData) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = gzipInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return outputStream.toByteArray();
    }

    public static void main(String[] args) {
        String originalData = "This is a sample data that needs to be compressed.";
        byte[] dataBytes = originalData.getBytes();
        
        try {
            byte[] compressedData = compressData(dataBytes);
            System.out.println("Original Data: " + originalData);
            System.out.println("Compressed Data: " + new String(compressedData));

            byte[] decompressedData = decompressData(compressedData);
            System.out.println("Decompressed Data: " + new String(decompressedData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
