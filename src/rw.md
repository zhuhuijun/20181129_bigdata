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
>- hadoop archive -archiveName  my.har -p  /user/ubuntu /user/my --没有压缩，存了双份这是个问题啊
>- hdfs dfs -lsr har:///user/my/my.har
>- hdfs dfs -cp har:///user/my/my.har /user/your --接档
>- hdfs dfs -rmr /user/your

#### 4、数据完整性
>- 数据被损坏的可能性
>- checksum 校验和进行校验确认文件是否损坏，判断字节数组
>- 一般校验没有纠错机制
>- CRC-32 循环冗余校验 得到一个32位的整数校验和
>- 校验和对指定的字节数进行校验 io.bytes.per.checksum=512字节 属性不能 大于 io.file.buffer.size=4096 缓冲器的大小
>- 数据写入时由最后一个节点负责验证校验
>- datanode 在后台开启线程 datablockscanner 进行监控
#### 5、压缩解压缩
>- ZipInputStream 解压缩
>- ZipOutputStream 压缩
>- ZipEntry 压缩条目

>- /lib目录存放共享库  /lib64 
>- /lib下的lib是各个厂商的共享库
>- /usr/lib 是个个人的
>- cd /etc    ls | grep so
>- ld.so.conf
>- cd ld.so.conf
>- cat *.conf
>- sudo rsync -l  * /usr/local/bin/
>-  sudo rsync -l  * /lib 才好使