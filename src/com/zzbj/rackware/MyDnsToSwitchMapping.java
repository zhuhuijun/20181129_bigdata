package com.zzbj.rackware;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.net.DNSToSwitchMapping;

public class MyDnsToSwitchMapping implements DNSToSwitchMapping
{

	/***
	 * 传的是客户端的ip列表
	 */
	public List<String> resolve(List<String> names)
	{
		List<String> list = new ArrayList<String>();
		if (null != names && names.size() > 0)
		{
			for (String name : names)
			{
				// 主机名开头s100
				Integer ino = 0;
				if (name.startsWith("s"))
				{
					String no = name.substring(1);
					ino = Integer.parseInt(no);
				} else if (name.startsWith("192"))
				{
					String no2 = name.substring(name.lastIndexOf(".") + 1);
					ino = Integer.parseInt(no2);
				}
				if (ino < 103)
				{
					list.add("/rack1/" + ino);
				} else
				{
					list.add("/rack2/" + ino);
				}
			}
		}
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream("/home/ubuntu/dns.txt");
			for (String name : list)
			{
				fileOutputStream.write((name + "\r\n").getBytes());
			}
			fileOutputStream.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	public void reloadCachedMappings()
	{
		// TODO Auto-generated method stub

	}

	public void reloadCachedMappings(List<String> names)
	{
		// TODO Auto-generated method stub

	}

}
