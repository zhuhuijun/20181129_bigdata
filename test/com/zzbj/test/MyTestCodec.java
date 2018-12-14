<<<<<<< HEAD
package com.zzbj.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.junit.Test;

import sun.nio.ch.IOUtil;

/**
 * 测试压缩与解压缩
 * 
 * @author zhuhuijun
 *
 */
public class MyTestCodec
{
	@SuppressWarnings("resource")
	@Test
	public void testDeflate() throws Exception
	{
		
		FileInputStream fin = new FileInputStream("d:\\dd.pdf");
		
		
		//创建解码器
		DefaultCodec defaultCodec = new DefaultCodec();
		FileOutputStream fos = new FileOutputStream("d:\\codec\\dd.deflate");
		//创建压缩流
		CompressionOutputStream cos = defaultCodec.createOutputStream(fos);
		//流的对copy
		IOUtils.copyBytes(fin, cos, 1024);
		cos.close();
		fos.close();
		fin.close();
		System.err.println("over");
	}

}
=======
package com.zzbj.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.junit.Test;

import sun.nio.ch.IOUtil;

/**
 * 测试压缩与解压缩
 * 
 * @author zhuhuijun
 *
 */
public class MyTestCodec
{
	@SuppressWarnings("resource")
	@Test
	public void testDeflate() throws Exception
	{
		
		FileInputStream fin = new FileInputStream("d:\\dd.pdf");
		
		
		//创建解码器
		DefaultCodec defaultCodec = new DefaultCodec();
		FileOutputStream fos = new FileOutputStream("d:\\codec\\dd.deflate");
		//创建压缩流
		CompressionOutputStream cos = defaultCodec.createOutputStream(fos);
		//流的对copy
		IOUtils.copyBytes(fin, cos, 1024);
		cos.close();
		fos.close();
		fin.close();
		System.err.println("over");
	}

}
>>>>>>> d3ae47af51fce0521960e12814db3229de56de70
