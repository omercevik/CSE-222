import java.util.EmptyStackException;

/**
 *
 * @param <E> Stack type.
 */
public class Stack<E>
{
    private int Capacity = 10;
    private E[] stack;
    private int topOfStack = -1;

    /**
     * Constructor.
     */
    public Stack()
    {
        stack = (E[]) new Object[Capacity];
    }

    /**
     * Push newEntry to stack.
     * @param newEntry Pushing entry.
     */
    public void push(E newEntry)
    {
        if (topOfStack == stack.length - 1)
        {
            reallocate();
        }
        stack[++topOfStack] = newEntry;
    }

    /**
     * Reallocates stack.
     */
    private void reallocate()
    {
        E[] tempStack = (E[]) new Object[stack.length];
        for (int i = 0; i < stack.length; ++i)
        {
            tempStack[i] = stack[i];
        }
        stack = (E[]) new Object[tempStack.length*2];
        for (int i = 0; i < tempStack.length; ++i)
        {
            stack[i] = tempStack[i];
        }
    }

    /**
     * Checks empty.
     * @return Returns if empty.
     */
    public boolean isEmpty()
    {
        return topOfStack < 0;
    }

    /**
     * Removes top of stack.
     * @return Returns removed object.
     */
    public E pop()
    {
        if (topOfStack < 0)
        {
            System.out.println("Stack Underflow");
            throw new EmptyStackException();
        }
        else
        {
            E x = stack[topOfStack--];
            return x;
        }
    }

    /**
     * Returns top of stack.
     * @return Returns top of stack.
     */
    public E peek()
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }
        return stack[topOfStack];
    }
}
