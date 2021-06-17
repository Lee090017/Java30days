package com.atlishu.stack;

/**
 * @author lishustart
 * @create 2021-05-18-21:40
 *
 * 用栈实现计算器
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "7*2*2-5+1-5+3-4";
        //创建两个栈，数栈和符号栈
        CStatck numStack = new CStatck(10);
        CStatck operStack = new CStatck(10);
        //扫描索引
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//用于保存每次扫描得到的操作符
        String str = "";//用于储存多位数
        int no = 0;//用于保存每次扫描得到的数字

        while(true){
            //处理多位数的问题
            ch = expression.substring(index, index + 1).charAt(0);

            if(operStack.isOper(ch)){//如果该符号为运算符
                if(!operStack.isEmpty()){
                    //如果不为空
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //把运算结果入数栈
                        numStack.push(res);
                        //把当前操作符入符号栈
                        operStack.push(ch);
                    }else{
                        //如果当前的操作符的优先级大于栈中操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else{
                    //如果为空，直接入符号栈
                    operStack.push(ch);
                }
            }else {//如果为数
                str = str + ch;
                //下一个字符为字符
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(str));
                    str = "";
                }else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(str));
                        str = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到expression最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }

        //如果表达式扫描完毕，就顺序从数栈和符号栈中pop出相应的数和符号，并运算
        while(true){
            //如果符号栈为空，则计算到最后的结果，数栈只有一个数就是结果
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            //把运算结果入数栈
            numStack.push(res);
        }
        //打印结果
        System.out.printf("表达式%s = %d",expression,numStack.pop());
    }
}

//定义一个数字栈
class CStatck{
    private int maxSize;//栈的大小
    private int[] stack;
    private int top = -1;

    public CStatck(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isNull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈-push
    public void push(int value){
        if(isNull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈-pop
    public int pop(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //查看栈顶的值
    public int peek(){
        return stack[top];
    }

    //遍历时需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回运算符的优先级，优先级用数字表示，数字越大，优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else return -1;
    }

    //判断是否是运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;//用于存放计算结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}