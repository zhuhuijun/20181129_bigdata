
package com.zzbj.test.codec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CodecPool;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.DeflateCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.io.compress.Lz4Codec;
import org.apache.hadoop.io.compress.SnappyCodec;
import org.apache.hadoop.io.compress.bzip2.Bzip2Compressor;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.log4j.chainsaw.Main;
import org.junit.Test;

public class MyCodecTest4
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void Compress(Class codeclazz) throws Exception
	{
		long start = System.currentTimeMillis();
		Configuration conf = new Configuration();
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeclazz, conf);
		FileInputStream fin = new FileInputStream("/home/ubuntu/Desktop/dd.pdf");
		String ext = codec.getDefaultExtension();
		FileOutputStream fos = new FileOutputStream("/home/ubuntu/Desktop/ddtest2." + ext);
		// 创建压缩流
		CompressionOutputStream out = codec.createOutputStream(fos);
		// 流的对copy
		IOUtils.copyBytes(fin, out, 1024);
		out.finish();
		out.close();
		fos.close();
		fin.close();
		File file = new File("/home/ubuntu/Desktop/ddtest2." + ext);
		long length = file.length();
		System.out.print(codeclazz.getSimpleName() + " compress file length: " + length);
		System.out.print(" " + codeclazz.getSimpleName() + " compress time : " + (System.currentTimeMillis() - start));
		System.out.println(" over");
	}

	/**
	 * 解压的
	 * 
	 * @param codeclazz
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void DeCompress(Class codeclazz) throws Exception
	{
		long start = System.currentTimeMillis();
		Configuration conf = new Configuration();
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeclazz, conf);
		String ext = codec.getDefaultExtension();
		String path = "/home/ubuntu/Desktop/ddtest2." + ext;
		CompressionInputStream fin = codec.createInputStream(new FileInputStream(path));
		FileOutputStream fos = new FileOutputStream("/home/ubuntu/Desktop/newaaa33" + ext + ".pdf");
		IOUtils.copyBytes(fin, fos, 1024);
		fin.close();
		fos.close();
		System.out.print(" decompress time :" + (System.currentTimeMillis() - start));
		System.out.println(" over");
	}

	@Test
	public static void main(String[] args) throws Exception
	{
		Class[] codec = { DeflateCodec.class, GzipCodec.class, BZip2Codec.class, SnappyCodec.class, Lz4Codec.class };
		for (Class codeclazz : codec)
		{
			Compress(codeclazz);
			DeCompress(codeclazz);
		}
	}
	/**
	 * pool
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testCodecPool() throws FileNotFoundException, Exception
	{
		Configuration conf = new Configuration();
		Class codeclazz = DeflateCodec.class;
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeclazz, conf);
		Compressor compressor = CodecPool.getCompressor(codec);
		
		
		CompressionOutputStream cos = codec.createOutputStream(new FileOutputStream("/home/ubuntu/Desktop/ddtest2.deflate"), compressor);
		IOUtils.copyBytes(	new FileInputStream("/home/ubuntu/Desktop/20181212.pdf"), cos, 1024);
		cos.finish();
		System.out.println("over");
	}
}

