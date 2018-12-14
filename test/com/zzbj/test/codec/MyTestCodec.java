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
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.io.compress.DeflateCodec;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import com.sun.org.apache.xpath.internal.FoundIndex;
import com.sun.xml.internal.ws.encoding.fastinfoset.FastInfosetCodec;

import sun.nio.ch.IOUtil;

/**
 * 测试压缩与解压缩
 * 
 * @author zhuhuijun
 *
 */
public class MyTestCodec
{
	/**
	 * deflate compress with
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	@Test
	public void testINDeflate() throws Exception
	{
		String codecClassname="org.apache.hadoop.io.compress.DefaultCodec";
		Class<?> codeClazz = Class.forName(codecClassname);
		Configuration conf = new Configuration();
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeClazz, conf);
		
		FileInputStream fin = new FileInputStream("d:\\dd.pdf");
		FileOutputStream fos = new FileOutputStream("d:\\codec\\dd.deflate");
		//创建压缩流
		CompressionOutputStream out = codec.createOutputStream(fos);
		//流的对copy
		IOUtils.copyBytes(fin, out, 1024);
		out.finish();
		out.close();
		fos.close();
		fin.close();
		System.out.println("over");
	}
	/**
	 * 解压缩
	 * @throws Exception
	 */
	@Test
	public void testOUTDeflate() throws Exception
	{
		Configuration conf = new Configuration();
		CompressionCodecFactory factory = new CompressionCodecFactory(conf);
		String path="d:\\codec\\dd.deflate";
		CompressionCodec codec = factory.getCodec(new Path(path));
		CompressionInputStream fin = codec.createInputStream(new FileInputStream(path));
		FileOutputStream fos = new FileOutputStream("d:/newaaa.pdf");
		IOUtils.copyBytes(fin, fos, 1024);
		fin.close();
		fos.close();
		System.out.println("over");
	}
	/**
	 * DeflateCodec
	 * @throws Exception
	 */
	@Test
	public void testINDeflate2() throws Exception
	{
		Configuration conf = new Configuration();
		Class<?> codeclazz = DeflateCodec.class;
		DeflateCodec codec = (DeflateCodec) ReflectionUtils.newInstance(codeclazz, conf);
		
		FileInputStream fin = new FileInputStream("d:\\dd.pdf");
		FileOutputStream fos = new FileOutputStream("d:\\codec\\dd2.deflate");
		//创建压缩流
		CompressionOutputStream out = codec.createOutputStream(fos);
		//流的对copy
		IOUtils.copyBytes(fin, out, 1024);
		out.finish();
		out.close();
		fos.close();
		fin.close();
		System.out.println("over");
	}
	/**
	 * 第二种解码方式
	 * @throws Exception
	 */
	@Test
	public void testOUTDeflate2() throws Exception
	{
		Configuration conf = new Configuration();
		Class<?> codeclazz = DeflateCodec.class;
		DeflateCodec codec = (DeflateCodec) ReflectionUtils.newInstance(codeclazz, conf);
		String path="d:\\codec\\dd2.deflate";
		CompressionInputStream fin = codec.createInputStream(new FileInputStream(path));
		FileOutputStream fos = new FileOutputStream("d:/newaaa33.pdf");
		IOUtils.copyBytes(fin, fos, 1024);
		fin.close();
		fos.close();
		System.out.println("over");
	}

}
