package com.atlishu.tree;

import java.io.*;
import java.util.*;

/**
 * @author lishustart
 * @create 2021-06-09-21:20
 *
 * 霍夫曼编码
 */
public class HuffmanCode {


    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] contentByte= content.getBytes();
//        System.out.println("压缩前的数据长度"+contentByte.length);//40
//        byte[] zip = huffmanZip(contentByte);
//        System.out.println("压缩后的数据长度"+zip.length);//17
//        byte[] bytes = huffmanUpZip(zip);
//        System.out.println("解压后的数据长度"+bytes.length);//40
//        String string = byteArraysToString(bytes);
//        System.out.println("原字符串为\""+string+"\"");

        //测试压缩各类文件的效果
        String srcFile1 = "C:\\Users\\Administrator\\Desktop\\泛在电力物联网\\1\\太阳.bmp";
        String dstFile1 = "C:\\Users\\Administrator\\Desktop\\泛在电力物联网\\1\\太阳.zip";

        zipFile(srcFile1,dstFile1);
        System.out.println("压缩文件OK~~~");

        //测试解压各类文件的效果
        String srcFile2 = "C:\\Users\\Administrator\\Desktop\\泛在电力物联网\\1\\太阳.zip";
        String dstFile2 = "C:\\Users\\Administrator\\Desktop\\泛在电力物联网\\1\\太阳2.bmp";
        upZipFile(srcFile2,dstFile2);
        System.out.println("解压文件OK~~~");

    }

    //完成对压缩文件的解压
    public static void upZipFile(String src,String dst){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File(src));
            ois = new ObjectInputStream(fis);
            fos = new FileOutputStream(new File(dst));

            byte[] data = (byte[]) ois.readObject();
            huffmanCodes = (Map<Byte,StringBuffer>)ois.readObject();
            System.out.println(huffmanCodes);

            byte[] bytes = huffmanUpZip(data);
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos!=null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ois!=null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * @param src 需要压缩的文件路径
     * @param dst 需要解压的文件路径
     */
    public static void zipFile(String src,String dst){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(new File(src));
            fos = new FileOutputStream(new File(dst));
            oos = new ObjectOutputStream(fos);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            byte[] bytes = huffmanZip(buffer);
            oos.writeObject(bytes);
            oos.writeObject(huffmanCodes);//将Huffman编码也写入压缩文件，便于恢复

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos!=null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(oos!=null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //将数据解压封装为一个函数
    public static byte[] huffmanUpZip(byte[] zip){
        String string = byteArrayToBitString(zip);
        ArrayList<Byte> decode = decode(string);
        //将list变为数组
        byte[] bytes = new byte[decode.size()];
        int i = 0;
        Iterator<Byte> iterator = decode.iterator();
        while (iterator.hasNext()){
            bytes[i] = iterator.next();
            i++;
        }
        return bytes;
    }

    //将byte[]数组变为原来的String
    public static String byteArraysToString(byte[] bytes){
        String str = "";
        for (byte b:bytes) {
            str = str + (char)b;
        }
        return str;
    }

    //根据Map的值得到对应的key
    public static byte getKeyhuffman(String value){
        byte b = 0;
        for (Map.Entry entry:huffmanCodes.entrySet()){
            if(entry.getValue().toString().equals(value)){
                b = (byte) entry.getKey();
                break;
            }
        }
        return b;
    }

    //完成数据的解压
    //根据霍夫曼编码集进行解码
    private static ArrayList<Byte> decode(String string){
        ArrayList<Byte> bytes = new ArrayList<>();
        //定义一个开头指针一个结束指针
        int begin = 0;
        Collection<StringBuffer> values = huffmanCodes.values();//存在哪些编码格式
        ArrayList<String> buffers = new ArrayList<>();
        Iterator<StringBuffer> iterator = values.iterator();
        while (iterator.hasNext()){
            buffers.add(iterator.next().toString());
        }

        while(begin<string.length()){
            String str;
            for (int i = begin+1; i <= string.length(); i++) {
                str = string.substring(begin, i);
                if(buffers.contains(str)){
                    bytes.add(getKeyhuffman(str));
                    begin = i;
                    break;
                }
            }
        }
        return bytes;
    }

    //将byte[]转为二进制字符串
    private static String byteArrayToBitString(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length-1; i++) {
            stringBuffer.append(byteToBitString(false,bytes[i]));
        }
        //最后一个元素不用补0
        stringBuffer.append(byteToBitString(true,bytes[bytes.length-1]));
        return stringBuffer.toString();
    }

    //将一个byte转成一个二进制字符串
    private static String byteToBitString(boolean flag,byte b){
        int temp = b;
        String str = "";
        if(temp>=0 && !flag){//如果为正数，且不是最后一个数，就需要将位数补至8位
            temp |= 256;
        }
        str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if(!flag) {
            str = str.substring(str.length() - 8);
        }
//        System.out.println(str);
        return str;

    }

    //将原来的方法封装起来，构成一个产生Huffman编码的函数
    public static byte[] huffmanZip(byte[] bytes){
        //统计各个字节的次数，生成叶子节点
        List<Node1> nodes = getNodes(bytes);
        //创建霍夫曼树
        Node1 node1 = huffmanTree(nodes);
        //生成对应的Huffman编码集
        getCodes(node1,"",stringBuffer);
        //将byte[]数组变为压缩byte[]数组
        byte[] zip = zip(bytes);
        return zip;
    }

    //将byte[]数组变为压缩byte[]数组
    public static byte[] zip(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b:bytes) {
            stringBuffer.append(huffmanCodes.get(b));
        }
        //将字符串转换为字节数组
        int len;
        if(stringBuffer.length()%8==0){
            len = stringBuffer.length()/8;
        }else{
            len = stringBuffer.length()/8 + 1;
        }
        byte[] huffmanCodeByte = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuffer.length(); i+=8) {//每8位一个字节
            String strByte;
            if(i+8>stringBuffer.length()){
                strByte = stringBuffer.substring(i);
            }else {
                strByte = stringBuffer.substring(i, i + 8);
            }

            //将strByte转为byte
            huffmanCodeByte[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeByte;
    }

    static Map<Byte,StringBuffer> huffmanCodes = new HashMap<>();
    static StringBuffer stringBuffer = new StringBuffer();

    /**
     *
     * @param node 传入节点
     * @param road 路径：左子节点是0，右子节点是1
     * @param stringBuffer 用于拼接路径
     */
    //生成霍夫曼编码集
    public static void getCodes(Node1 node,String road,StringBuffer stringBuffer){
        StringBuffer sb = new StringBuffer(stringBuffer);
        sb.append(road);
        if (node.data==null){//如果是非叶子节点，要继续遍历
            getCodes(node.left,"0",sb);
            getCodes(node.right,"1",sb);
        }else{
            huffmanCodes.put(node.data,sb);
        }
    }

    //创建霍夫曼树
    public static Node1 huffmanTree(List<Node1> nodes){
                //处理的过程是一个循环的过程
        while(nodes.size()>1) {

            //排序
            Collections.sort(nodes);

            //取出根节点权值最小的两个二叉树
            Node1 left = nodes.get(0);
            Node1 right = nodes.get(1);

            Node1 parent = new Node1(left.weight + right.weight);
            parent.left = left;
            parent.right = right;

            //删除已经处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);

            //将parent加入Nodes
            nodes.add(parent);

        }
        return nodes.get(0);
    }

    //统计各个字节的次数，生成叶子节点
    public static List<Node1> getNodes(byte[] bytes){
        //创建一个list
        ArrayList<Node1> nodes = new ArrayList<>();
        //统计byte出现的次数
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b:bytes) {
            Integer count =  counts.get(b);
            if(count == null){
                counts.put(b,1);
            }else{
                counts.put(b,count+1);
            }
        }
        //把每个键值对构成一个节点添加到list中
        for(Map.Entry<Byte,Integer> entry: counts.entrySet()){
            nodes.add(new Node1(entry.getKey(),entry.getValue()));
        }

        return nodes;
    }
}

//创建节点类
class Node1 implements Comparable<Node1>{
    Byte data;//存放数据本身，比如'a'=>97
    int weight;//权值，表示字符的个数
    Node1 left;//左右子节点
    Node1 right;

    public Node1(int weight) {
        this.weight = weight;
    }

    public Node1(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node1 o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

}
