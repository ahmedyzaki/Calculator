package Calculator;
import java.util.Stack;

/**
 * @author Ahmed Zaki
 *
 */
public class Calculation
{
	public Calculation()
	{}
	/**
	 * @param expression
	 * @return
	 */
	public static String evaluateExpression(String expression) 
	{
		try
		{
		 // Create operandStack to store operands
		 Stack<Double> operandStack = new Stack<>();
		 // Create operatorStack to store operators
		 Stack<Character> operatorStack = new Stack<>();
		 // Insert blanks around ( , ), + , - , / , and *
		 expression = insertBlanks(expression);
		 // Extract operands and operators
		 String[] tokens = expression.split(" ");
		 //int index=0;
		 // Phase 1: Scan tokens
		 for (String token: tokens)
		 {
			 if (token.length() == 0) // Blank space
			 continue; // Back to the while loop to extract the next token
			 else if (token.charAt(0) == '+' || token.charAt(0) == '-') 
			 {
				  // Process all +, -, *, / in the top of the operator stack
				  while (!operatorStack.isEmpty()     &&
						 (operatorStack.peek() == '+' ||
						  operatorStack.peek() == '-' ||
						  operatorStack.peek() == 'x' ||
						  operatorStack.peek() == '^' ||
						  operatorStack.peek() == '√' ||
						  operatorStack.peek() == '%' ||
						  operatorStack.peek() == '/' ||
						  operatorStack.peek() == '~' ||
						 operatorStack.peek() == '$')
						 ) 
				  {
					  processAnOperator(operandStack, operatorStack);
			      }
	
				  // Push the + or - operator into the operator stack
				  operatorStack.push(token.charAt(0));
			 }
		     
			 else if (token.charAt(0) == 'x' || token.charAt(0) == '/'||
					 token.charAt(0) == '%') 
			 {
				  // Process all *, / in the top of the operator stack
				  while (!operatorStack.isEmpty() 	 &&
						(operatorStack.peek() == 'x' ||
				         operatorStack.peek() == '/' ||
				         operatorStack.peek() == '%' ||
				         operatorStack.peek() == '~' ||
				         operatorStack.peek() == '$' ||
				         operatorStack.peek() == '^' ||
						  operatorStack.peek() == '√')
						) 
				  {
					  processAnOperator(operandStack, operatorStack);
				  }
				// Push the * or / operator into the operator stack
			     operatorStack.push(token.charAt(0));
		     }
			 else if(token.charAt(0) == '~'||token.charAt(0) == '$'||
					 token.charAt(0) == '^'||token.charAt(0) == '√')
			 {
				 while (!operatorStack.isEmpty()&&!operandStack.isEmpty()&&
						 (operatorStack.peek() == '~'||
						  operatorStack.peek() == '$')) 
					  {
						  processAnOperator(operandStack, operatorStack);
					  }
					// Push the * or / operator into the operator stack
				     operatorStack.push(token.charAt(0));
			 }
		     else if (token.trim().charAt(0) == '(') 
			 {
				  operatorStack.push('('); // Push '(' to stack
			 }
			
		     else if (token.trim().charAt(0) == ')') 
		     {
				   // Process all the operators in the stack until seeing '('
				   while (operatorStack.peek() != '(') 
				   {
					   processAnOperator(operandStack, operatorStack);
				   }
	
				   operatorStack.pop(); // Pop the '(' symbol from the stack
			 }
			
		     else 
		     {
		    	 	// An operand scanned
				   // Push an operand to the stack
				   operandStack.push(new Double(token));
			  }
	  }
		// Phase 2: Process all the remaining operators in the stack
		  while (!operatorStack.isEmpty()) 
		  {
			  processAnOperator(operandStack, operatorStack);
		  }

		  // Return the result
		  	return  operandStack.pop().toString();
		}
		catch(Exception ex)
		{
			return "Invalid Input";
		}
	}

		  /** Process one operator: Take an operator from operatorStack and
		  * apply it on the operands in the operandStack */
		  /**
		 * @param operandStack
		 * @param operatorStack
		 */
		public static void processAnOperator(Stack<Double> operandStack, Stack<Character> operatorStack) 
		{
		  char op = operatorStack.pop();
		  double op1 ;
		  double op2;
		  if(op=='√')
		  {
		      op1 = operandStack.pop();
		      op2=0;
		  }
		  else if(op=='~')
		  {
		      op1 = operandStack.pop();
		      op2=0;
		  }
		  else if(op=='$')
		  {
		      op1 = operandStack.pop();
		      op2=0;
		  }
		  else
		  {
			  op1 = operandStack.pop();
			  op2 = operandStack.pop();
		  }
		  if (op == '+')
			  operandStack.push(op2 + op1);
		  else if (op == '-')
			  operandStack.push(op2 - op1);
		  else if (op == '^')
			  operandStack.push(Math.pow(op2, op1));
		  else if (op == 'x')
			  operandStack.push(op2 * op1);
		  else if (op == '√')
			  operandStack.push(Math.sqrt(op1));
		  else if (op == '/')
			  operandStack.push(op2 / op1);
		  else if (op=='%')
		  {
				  	operandStack.push(op2 % op1);
		  }
		  else if (op=='~')
		  {
				  	operandStack.push(op2-op1);
		  }
		  else if (op=='$')
		  {
				  	operandStack.push(op2+op1);
		  }
	}
		  /**
		 * @param s
		 * @return
		 */
		public static String insertBlanks(String s) 
		{
			   String result = "";
			   for (int i = 0; i < s.length(); i++) 
			   {
				   if((i==0)&&(s.charAt(0)=='-'))
					   result +='~';
				   else if((s.charAt(i)=='-')&&(
						   s.charAt(i-1) == '(' || 
						   s.charAt(i-1) == '+' || s.charAt(i-1) == '-' ||
						   s.charAt(i-1) == 'x' || s.charAt(i-1) == '/' ||
						   s.charAt(i-1) == '^'||  s.charAt(i-1) == '√' ||
						   s.charAt(i-1) == '%'))
					   result +='~';
				   else if((i==0)&&(s.charAt(0)=='+'))
					   result +='$';
				   else if((s.charAt(i)=='+')&&(
						   s.charAt(i-1) == '(' || 
						   s.charAt(i-1) == '+' || s.charAt(i-1) == '-' ||
						   s.charAt(i-1) == 'x' || s.charAt(i-1) == '/' ||
						   s.charAt(i-1) == '^'||  s.charAt(i-1) == '√' ||
						   s.charAt(i-1) == '%'))
					   result +='$';
				   else if(((i+1)!=s.length())&&(s.charAt(i+1) == '(' )&&
						   ( s.charAt(i) != '(' &&s.charAt(i) != '+'&& 
						   	 s.charAt(i) != '-' &&s.charAt(i) != 'x'&& 
						   	 s.charAt(i) != '/' &&s.charAt(i) != '^'&&  
						   	 s.charAt(i) != '√' &&s.charAt(i) != '%')
						   )
						   result +=s.charAt(i)+"x";
				   else if(((i+1)!=s.length())&&(s.charAt(i+1) == '√' )&&
						   ( s.charAt(i) != '(' &&s.charAt(i) != '+'&& 
						   	 s.charAt(i) != '-' &&s.charAt(i) != 'x'&& 
						   	 s.charAt(i) != '/' &&s.charAt(i) != '^'&&  
						   	 s.charAt(i) != '%')
						   )
						   result +=s.charAt(i)+"x";
				   else
					   result +=s.charAt(i);
			   }
			   s=result;
			   result="";
			   for (int i = 0; i < s.length(); i++) 
			   {
				   if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
					   s.charAt(i) == '+' || s.charAt(i) == '-' ||
					   s.charAt(i) == 'x' || s.charAt(i) == '/' ||
					   s.charAt(i) == '^' || s.charAt(i) == '√' ||
					   s.charAt(i) == '%' || s.charAt(i) == '~'||
					   s.charAt(i) == '$') 
				   result += " " + s.charAt(i) + " ";
			   else
				   result += s.charAt(i);
			   }
			   System.out.println(result);
			   return result;
	  }
}

