#### 1、文件写入解析和机架感知

>- 网络拓扑
>- netstat -ano | more

#### 2、机架感知的配置
```
	<property>
		<name>net.topology.node.switch.mapping.impl</name>
		<value>com.zzbj.rackware.MyDnsToSwitchMapping</value>
	</property>
```
#### 3、自己的jar包存放的位置
>- /apps/hadoop/share/hadoop/common/lib
>- 运行在
>- 容错机制 master-slave 主从架构 1个名称节点多个数据节点(n>=3) namenode
#### 4、hadoop IO
>- 客户端命令
>- FileSystem
>- NameNode [目录，权限，block ,replaction(副本数)，Dataname]


```
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
	@Test
	public void fileStatus() throws Exception
	{
		FileStatus fs0 = fileSystem.getFileStatus(new Path("/"));
		printFile(fs0);
		System.out.println("over--------------------------");
	}
```
#### 4、查看nn镜像文件

```
	hdfs oiv -p XML -i fsimage -o ~/fxxxx.xml //offline image viewer
	正在使用./name/current/edits_inprogress_0000000000000000026
	//导出镜像文件
	hdfs oev -p XML -i edits_0000000000000003699-0000000000000003713 -o aaa.xml
```

### 5、hdfs dfsadmin
>- hadoop-daemon.sh start namenode
>- hdfs dfsadmin -help rollEdits (需要在namenode 文件夹下的current中执行)
>-  hdfs oev -p XML -i edits_0000000000000003699-0000000000000003713 -o aaa.xml
>- 镜像文件是在 hdfs dfsadmin -saveNamespace 集群启动时进入安全模式 在安全模式下是没法进行文件写的操作等重要操作，启动完成后退出安全模式 
>- hdfs dfsadmin -safemode get 
>- hdfs dfsadmin -safemode enter
>- hdfs dfsadmin -safemode leave
>- hdfs dfsadmin -safemode  wait
>- hdfs dfsadmin -safemode enter & hdfs dfsadmin -saveNamespace
>- 以上是进入安全模式 保存镜像
```
autoexe.sh
#!/bin/bash
hdfs dfsadmin -safemode wait
hdfs dfs -put ~/hello.txt  /user/ubuntu/how.txt

```