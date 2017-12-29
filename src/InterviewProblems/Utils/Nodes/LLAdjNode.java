package InterviewProblems.Utils.Nodes;

/* Idea:
 *
 *   node  -->  node --> node
 *    |
 *    |
 *   node  -->  node --> node
 *    |
 *    |
 *   node  -->  node --> node
 *    |
 *    |
 *   node
 *
 */
public class LLAdjNode
{
    public int data;
    public LLAdjNode next;
    // Could be a LLNode.
    public LLAdjNode adj;

    public LLAdjNode(int data)
    {
        this.data = data;
    }

    public void printNode()
    {
        LLAdjNode current = this;
        LLAdjNode current_adj = current;
        while(current != null)
        {
            while(current_adj != null)
            {
                System.out.print(current_adj.data + " -- ");
                current_adj = current_adj.next;
            }
            System.out.println();
            System.out.println("|");
            System.out.println("|");
            current = current.next;
            current_adj = current;
        }
        System.out.println();
    }
}

