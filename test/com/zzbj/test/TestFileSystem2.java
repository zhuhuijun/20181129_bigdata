package com.zzbj.test;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import jdk.nashorn.internal.ir.BlockStatement;

public class TestFileSystem2
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

	/***
	 * 遍历目录
	 * 
	 * @throws Exception
	 */
	@Test
	public void fileStatus() throws Exception
	{
		FileStatus fs0 = fileSystem.getFileStatus(new Path("/"));
		printFile(fs0);
		System.out.println("over--------------------------");
	}

	/**
	 * 递归拿到文件的路径
	 * 
	 * @param fs0
	 */
	private void printFile(FileStatus fs0)
	{
		try
		{
			Path path = fs0.getPath();
//			System.out.println(path.getName());
			System.out.println(path.toUri().getPath());
			if (fs0.isDirectory())
			{
				FileStatus[] listStatus = fileSystem.listStatus(path);
				if (null != listStatus && listStatus.length > 0)
				{
					for (FileStatus fsss : listStatus)
					{
						printFile(fsss);
					}
				}
			}
		} catch (Exception e)
		{
			// TODO: handle exception
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void deleteDir() throws Exception
	{
		Path path = new Path("/user/myfolder");
		fileSystem.delete(path, true);
		System.out.println("over--------------------------");
	}

	/**
	 * 获得文件块的列表
	 * 
	 * @throws Exception
	 */
	@Test
	public void getBlockLoctions() throws Exception
	{
		// 创建路径
		Path path = new Path("hdfs://s100:8020/user/ubuntu/uyyu442.txt");
		// 得到文件的状态
		FileStatus fileStatus = fileSystem.getFileStatus(path);
		// 得到文件的长度
		long len = fileStatus.getLen();
		// 得到文件块
		BlockLocation[] fileBlockLocations = fileSystem.getFileBlockLocations(fileStatus, 0, len);
		for (BlockLocation blockLocation : fileBlockLocations)
		{
			System.out.println(blockLocation.getHosts());
			System.out.println(blockLocation.getLength());
			System.out.println(blockLocation.getOffset());
		}
		System.out.println("over--------------------------");
	}

}
