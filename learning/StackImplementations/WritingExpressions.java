import java.util.Stack;
public class WritingExpressions{

    public static String infixToPostfix(String sequence){
        Stack<Character> conv = new Stack<>();
        String postfix = "";
        if(sequence.length()==0){
            return "";
        } else{
            for (int i=0;i<sequence.length();i++){
                if (Character.isLetterOrDigit(sequence.charAt(i))){
                    postfix+=sequence.charAt(i);
                } else if (sequence.charAt(i)=='('){
                    conv.push('(');
                } else if (sequence.charAt(i)=='+' || sequence.charAt(i)=='*' || sequence.charAt(i)=='-' || sequence.charAt(i)=='/' || sequence.charAt(i)=='^'){
                    int priority = operatorPriority(sequence.charAt(i));
                    while(!conv.empty() && operatorPriority(conv.peek()) <= priority){
                        postfix+=conv.pop();
                        
                    }
                    conv.push(sequence.charAt(i));
                } else if (sequence.charAt(i) == ')' ){
                    while(!conv.empty() && conv.peek()!='('){
                        postfix+=conv.pop();
                    }
                    conv.pop();
                } else {
                    postfix+="";
                }
            }
        } 

        while(!conv.empty()){
            postfix+=conv.pop();
        }
        return postfix;
    }

    public static int operatorPriority(int operator){
        switch(operator){
            case '^':
                return 1;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 3;
        }
        return 4;
            
    }

    public static String infixToPrefix(String sequence){
        StringBuilder revSeq = new StringBuilder(sequence);
        revSeq.reverse();
        for(int i=0;i<revSeq.length();i++){
            if(revSeq.charAt(i) == '('){
                revSeq.setCharAt(i, ')');
            } else if(revSeq.charAt(i) == ')'){
                revSeq.setCharAt(i, '(');
            }
        }

        System.out.println(revSeq.toString());

        Stack<Character> conv = new Stack<>();
        StringBuilder prefix = new StringBuilder();
        

        if(revSeq.length()==0){
            return "";
        } else{
            for (int i=0;i<revSeq.length();i++){
                if (Character.isLetterOrDigit(revSeq.charAt(i))){
                    prefix.append(revSeq.charAt(i));
                } else if (revSeq.charAt(i)=='('){
                    conv.push('(');
                } else if (revSeq.charAt(i)=='+' || revSeq.charAt(i)=='*' || revSeq.charAt(i)=='-' || revSeq.charAt(i)=='/' || revSeq.charAt(i)=='^'){
                    int priority = operatorPriority(revSeq.charAt(i));
                    while(!conv.empty() && operatorPriority(conv.peek()) < priority){
                        prefix.append(conv.pop());
                        
                    }
                    conv.push(revSeq.charAt(i));
                } else if (revSeq.charAt(i) == ')' ){
                    while(!conv.empty() && conv.peek()!='('){
                        prefix.append(conv.pop());
                    }
                    conv.pop();
                } else {
                    prefix.append("");
                }
            }
        } 

        while(!conv.empty()){
            prefix.append(conv.pop());
        }

        return prefix.reverse().toString();
        


    }

    public static void main(String[]args){
        //System.out.println(infixToPostfix("(2-3+4)*(5+6*7)"));
        System.out.println(infixToPrefix("(A + B) * (C + D)"));
    }
}