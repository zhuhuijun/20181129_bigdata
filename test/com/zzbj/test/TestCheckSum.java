
package com.zzbj.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.junit.Test;

public class TestCheckSum
{

	@Test
	public void testLocalFileSystem() throws Exception
	{
		// 创建配置对象加载本地的coredefault.xml core-site.xml;
		Configuration config = new Configuration();
		config.set("fs.defaultFS", "file:///");
		// 本地文件系统
		FileSystem fs = FileSystem.get(config);
		Path path = new Path("d:\\aaa\\hello.txt");
		FSDataOutputStream fos = fs.create(path,true);
		fos.write("hello".getBytes());
		fos.close();
		fs.close();
		System.out.println("over");

	}

}

