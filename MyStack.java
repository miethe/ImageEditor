package a8;

public class MyStack {
	   private int maxSize;
	   private Frame[] stackArray;
	   private int top;
	   public MyStack(int s) {
	      maxSize = s;
	      stackArray = new Frame[maxSize];
	      top = -1;
	   }
	   public void push(Frame j) {
	      stackArray[++top] = j;
	   }
	   public Frame pop() {
	      return stackArray[top--];
	   }
	   public Frame peek() {
	      return stackArray[top];
	   }
	   public boolean isEmpty() {
	      return (top == -1);
	   }
	   public boolean isFull() {
	      return (top == maxSize - 1);
	   }
}
