package com.zzbj.test.codec;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

/**
 * 测试压缩与解压缩
 * 
 * @author zhuhuijun
 *
 */
public class MyTestCodec2
{
	@SuppressWarnings("resource")
	@Test
	public void testDeflate() throws Exception
	{
		String codecClassname="org.apache.hadoop.io.compress.DefaultCodec";
		Class<?> codeClazz = Class.forName(codecClassname);
		Configuration conf = new Configuration();
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeClazz, conf);
		FileInputStream fin = new FileInputStream("/home/ubuntu/Desktop/dd.pdf");
		FileOutputStream fos = new FileOutputStream("/home/ubuntu/dd.deflate");
		//创建压缩流
		CompressionOutputStream out = codec.createOutputStream(fos);
		//流的对copy
		IOUtils.copyBytes(fin, out, 1024);
		out.finish();
		out.close();
		fos.close();
		fin.close();
		System.err.println("over");
	}
	/**
	 * 解压缩
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@Test
	public void testOUTDeflate() throws Exception
	{
		Configuration conf = new Configuration();
		CompressionCodecFactory factory = new CompressionCodecFactory(conf);
		String path="/home/ubuntu/dd.deflate";
		CompressionCodec codec = factory.getCodec(new Path(path));
		CompressionInputStream fin = codec.createInputStream(new FileInputStream(path));
		FileOutputStream fos = new FileOutputStream("/home/ubuntu/Desktop/newaa.pdf");
		IOUtils.copyBytes(fin, fos, 1024);
		fin.close();
		fos.close();
		System.out.println("over");
	}
}
