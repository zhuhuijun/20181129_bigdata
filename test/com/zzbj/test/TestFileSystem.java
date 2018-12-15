package com.zzbj.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 使用文件系统
 */
public class TestFileSystem
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
		Path f = new Path("hdfs://s100:8020/user/ubuntu/uyyu442.txt");
		FSDataOutputStream fsDataOutputStream = fileSystem.create(f);
		fsDataOutputStream.write("hello,world".getBytes());
		fsDataOutputStream.close();
		System.out.println("over");
	}

	/**
	 * 读取文件
	 */
	@Test
	public void readFile() throws IOException
	{

		Path f = new Path("hdfs://s100:8020/user/ubuntu/uu.txt");
		FSDataInputStream fin = fileSystem.open(f);
		FileOutputStream fout = new FileOutputStream("d:/uuu.txt");
		IOUtils.copyBytes(fin, fout, 1024);
		IOUtils.closeStream(fin);
		IOUtils.closeStream(fout);
		System.out.println("over--------------------------");
	}

	/***
	 * 文件块1
	 * 
	 * @throws IOException
	 */
	@Test
	public void readSeekFile() throws IOException
	{
		Path f = new Path("hdfs://s100:8020/user/ubuntu/hadoop-2.7.2.tar.gz");
		FSDataInputStream fin = fileSystem.open(f);
		FileOutputStream fout = new FileOutputStream("d:/hadoop.tar.gz.part-1");
		// 定位文件偏移
		fin.seek(1024 * 1024 * 128);
		IOUtils.copyBytes(fin, fout, 1024);
		IOUtils.closeStream(fin);
		IOUtils.closeStream(fout);
		System.out.println("over--------------------------");
	}

	/**
	 * 文件块0
	 * 
	 * @throws IOException
	 */
	@Test
	public void readSeekFile2() throws IOException
	{
		Path f = new Path("hdfs://s100:8020/user/ubuntu/hadoop-2.7.2.tar.gz");
		FSDataInputStream fin = fileSystem.open(f);
		FileOutputStream fout = new FileOutputStream("d:/hadoop.tar.gz.part-0");
		byte[] buffer = new byte[1024];
		for (int i = 0; i < 128 * 1024; i++)
		{
			fin.read(buffer);
			fout.write(buffer);
		}
		IOUtils.copyBytes(fin, fout, 1024);
		IOUtils.closeStream(fin);
		IOUtils.closeStream(fout);
		System.out.println("over--------------------------");
	}

	/**
	 * 创建文件夹
	 * 
	 * @throws IOException
	 */
	@Test
	public void mkdir() throws IOException
	{
		Path f = new Path("/user/myfolder");
		// 创建权限对象
		// FsPermission permission = new
		// FsPermission(FsAction.ALL,FsAction.ALL,FsAction.ALL);
		boolean mkdirs = fileSystem.mkdirs(f, FsPermission.getDefault());

		System.out.println("over--------------------------" + mkdirs);
	}

	/**
	 * 创建文件夹
	 * 
	 * @throws IOException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	//目录没有副本，文件才有副本
	@Test
	public void fileStatus() throws Exception
	{
		FileStatus fs0 = fileSystem.getFileStatus(new Path("/"));
		Class clazz = FileStatus.class;
		Method[] ms = clazz.getDeclaredMethods();
		for (Method m : ms)
		{
			String mname = m.getName();
			Class[] parameterTypes = m.getParameterTypes();
			if (mname.startsWith("get") && (parameterTypes == null || parameterTypes.length == 0))
			{
				if (!mname.equals("getSymlink"))
				{
					Object invoke = m.invoke(fs0, null);
					System.out.println(mname + "()=" + invoke);
				}
			}
		}

		System.out.println("over--------------------------");
	}
}
