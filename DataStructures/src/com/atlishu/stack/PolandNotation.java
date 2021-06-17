package com.atlishu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lishustart
 * @create 2021-05-19-10:25
 *
 * 逆波兰计算器
 *
 */
public class PolandNotation {
    public static void main(String[] args) {
        //定义一个逆波兰表达式
        //(30+4)*5-6 => 30 4 + 5 * 6 -
//        String suffixExpression = "30 4 + 5 * 6 -";
//        List<String> listString = getListString(suffixExpression);
//        int res = caculate(listString);
//        System.out.println(suffixExpression+" = "+res);
        //中缀表达式
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        //后缀表达式
        List<String> list = parseSuffixExpressionList(infixExpressionList);
        System.out.println(list);
        //计算结果
        int res = caculate(list);
        System.out.println(expression+"="+res);
    }

    //将表达式放入集合
    public static List<String> getListString(String str){
        String[] split = str.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele:split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int caculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        for (String item:ls) {
            //这里使用正则表达式来取出数
            if(item.matches("\\d+")){//匹配的是多位数
                stack.push(item);
            }else{
                //pop出两个数进行运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else  if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }else{
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res+"");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式转为对应的List
    public static List<String> toInfixExpressionList(String s){
        List<String> list = new ArrayList<>();
        int i = 0;
        String str="";
        char ch;
        do{
            if((ch = s.charAt(i)) < 48 ||(ch = s.charAt(i)) > 57){//非数字
                list.add(""+ch);
                i++;
            }else{//如果是数要考虑多位数的问题
                while(i < s.length() && (ch = s.charAt(i)) >= 48 &&(ch = s.charAt(i)) <= 57) {
                    str = str + ch;
                    i++;
                }
                list.add(str);
                str = "";
            }
        }while(i < s.length());
        return list;
    }

    //将中缀表达式转为后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> list){
        //1.初始化两个栈
        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();

        //2.从左到右扫描表达式
        int index = 0;

        while(true){
            //3.如果是数，push进栈stack2
            if(!isOper(list.get(index))){
                stack2.push(list.get(index));
            }else{//4.遇到运算符
                //不为括号的运算符
                if (list.get(index).charAt(0) != '(' && list.get(index).charAt(0) != ')') {
                    while(true) {
                        //4.1.s1为空或栈顶运算符为'('，则直接入栈
                        if (stack1.size() == 0 || stack1.peek().equals("(")) {
                            stack1.push(list.get(index));
                            break;
                        } else if (priority(list.get(index).charAt(0)) > priority(stack1.peek().charAt(0))) {
                            //4.2若优先级比栈顶高，也将运算符压入s1
                            stack1.push(list.get(index));
                            break;
                        } else {
                            //4.3.将s1栈顶的运算符压入s2中，再继续转到s1中新的栈顶运算符
                            String t = stack1.pop();
                            stack2.push(t);
                        }
                    }
                }else{//5.遇到括号
                    //5.1如果是"("，则直接压入s1
                    if(list.get(index).charAt(0) == '('){
                        stack1.push(list.get(index));
                    }else{
                        //5.2如果是")"，依次弹出s1栈顶的运算符，并压入s2，直到遇到"("，将该左括号丢弃
                        while(!stack1.peek().equals("(")){
                            String s = stack1.pop();
                            stack2.push(s);
                        }
                        stack1.pop();
                    }
                }
            }
            index++;
//            System.out.println(index);
            if(index == list.size()){
                //7.将s1中剩余的运算符依次弹出并压入s2
                while (stack1.size()!=0){
                    String s = stack1.pop();
                    stack2.push(s);
                }
                break;
            }
        }
        //8.将s2的元素依次弹出，其逆序为最终的逆序表达式
        Stack<String> reverseStack = new Stack<>();
        while (stack2.size()!=0){
            String s = stack2.pop();
            reverseStack.push(s);
        }
        List<String> res = new ArrayList<>();
        while (reverseStack.size()!=0){
            String s = reverseStack.pop();
            res.add(s);
        }
        return res;
    }

    //判断是否为运算符
    public static boolean isOper(String str){
        return str.charAt(0)=='+' || str.charAt(0)=='-' || str.charAt(0)=='*' || str.charAt(0)=='/'|| str.charAt(0)=='('|| str.charAt(0)==')';
    }

    //返回运算符的优先级，优先级用数字表示，数字越大，优先级越高
    public static int priority(char oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else return -1;
    }
}
