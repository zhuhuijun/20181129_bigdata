package com.zzbj.hadoop;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;

public class HadoopApi
{
	static
	{
		// 让java程序识别hdfs的协议
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}

	public static void main(String[] args) throws Exception
	{
		String url = "hdfs://s100:8020/user/ubuntu/hadoop-2.7.2.tar.gz";
		URL url2 = new URL(url);
		URLConnection connection = url2.openConnection();
		InputStream istream = connection.getInputStream();
		FileOutputStream outputStream = new FileOutputStream("d:/hadoop.tar.gz");
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = istream.read(buf)) != -1)
		{
			outputStream.write(buf, 0, len);
		}
		istream.close();
		outputStream.close();
		System.out.println("over");
	}
}
