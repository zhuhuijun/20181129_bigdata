package com.zzbj.test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

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

}
