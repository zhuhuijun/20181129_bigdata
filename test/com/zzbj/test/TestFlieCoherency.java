<<<<<<< HEAD
package com.zzbj.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试文件一致性
 * @author zhuhuijun
 *
 */
public class TestFlieCoherency
{
	private FileSystem fileSystem;

	@Before
	public void initConf()
	{
		Configuration conf = new Configuration();
		try
		{
			fileSystem = FileSystem.get(conf);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 写操作
	 */
	@Test
	public void writeFile() throws Exception
	{
		// hdfs dfs -chmod 777 /user/ubuntu
		Path f = new Path("hdfs://s100:8020/user/how4334.txt");
		FSDataOutputStream fsDataOutputStream = fileSystem.create(f);
		fsDataOutputStream.write("hello,world".getBytes());
		fsDataOutputStream.hflush();
		//fsDataOutputStream.hsync();
		fsDataOutputStream.close();
		System.out.println("over");
	}
}
=======
package com.zzbj.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试文件一致性
 * @author zhuhuijun
 *
 */
public class TestFlieCoherency
{
	private FileSystem fileSystem;

	@Before
	public void initConf()
	{
		Configuration conf = new Configuration();
		try
		{
			fileSystem = FileSystem.get(conf);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 写操作
	 */
	@Test
	public void writeFile() throws Exception
	{
		// hdfs dfs -chmod 777 /user/ubuntu
		Path f = new Path("hdfs://s100:8020/user/how4334.txt");
		FSDataOutputStream fsDataOutputStream = fileSystem.create(f);
		fsDataOutputStream.write("hello,world".getBytes());
		fsDataOutputStream.hflush();
		//fsDataOutputStream.hsync();
		fsDataOutputStream.close();
		System.out.println("over");
	}
}
>>>>>>> ed7132ccdc99bbdcb45ea85dc8a75fafe51861b6
