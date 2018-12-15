package com.zzbj.test.codec;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CodecPool;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.Decompressor;
import org.apache.hadoop.io.compress.DeflateCodec;
import org.apache.hadoop.util.ReflectionUtils;

public class TestCodecPool
{
	public void main0(String[] args) throws FileNotFoundException, Exception
	{
		Configuration conf = new Configuration();
		Class codeclazz = DeflateCodec.class;
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeclazz, conf);
		Compressor compressor = CodecPool.getCompressor(codec);

		CompressionOutputStream cos = codec
				.createOutputStream(new FileOutputStream("/home/ubuntu/Desktop/ddtest2.deflate"), compressor);
		IOUtils.copyBytes(new FileInputStream("/home/ubuntu/Desktop/20181212.pdf"), cos, 1024);
		cos.finish();
		CodecPool.returnCompressor(compressor);
		System.out.println("over");
	}
	/**
	 * decompression
	 * @param args
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public void main(String[] args) throws FileNotFoundException, Exception
	{
		Configuration conf = new Configuration();
		Class codeclazz = DeflateCodec.class;
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeclazz, conf);
		Decompressor dcomp = CodecPool.getDecompressor(codec);

		CompressionInputStream cin = codec
				.createInputStream(new FileInputStream("/home/ubuntu/Desktop/ddtest2.deflate"), dcomp);
		IOUtils.copyBytes(cin, new FileOutputStream("/home/ubuntu/Desktop/fsfsfsfsf1212.pdf"), 1024);
		cin.close();
		CodecPool.returnDecompressor(dcomp);
		System.out.println("over");
	}
}
