#### 1、读写文件的一致性问题
>- 需要数据立即被其它客户端可见

```
fsDataOutputStream.hflush();
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
```

>- scp 不能处理符号链接，但是可以远程复制
>- scp -r  hello.txt ubuntu@s100:/home/ubuntu/xx.txt                     //push 推送
>- scp -r  ubuntu@s100:/home/ubuntu/xx.txt ~/hello.txt                   //pull 
>- scp -r -3  ubuntu@s100:/home/ubuntu/xx.txt ubuntu@s101:/home/ubunut/hello.txt 
>- rsync 处理符号链接 备份和镜像，不能处理两个远程之间的复制 B,C之间的复制不能实现

#### 2、hadoop提供的scp
```
1在集群之间进行文件的复制 discp是两个集群之间进行复制
hadoop discp hdfs://namenode1/foo hafs://namenode2/foo
```

#### 3、归档
>- hadoop archive -archiveName  my.har -p  /user/ubuntu /user/my