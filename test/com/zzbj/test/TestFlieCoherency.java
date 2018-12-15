<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> ab56c969ce0aab2625d69765006e7aebecb9d886
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
<<<<<<< HEAD
=======
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
>>>>>>> 3cb8174a76dc6d4911d67c674a6f967e7eba775b
>>>>>>> ab56c969ce0aab2625d69765006e7aebecb9d886
