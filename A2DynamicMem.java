// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    public void Defragment() {
        Dictionary curr = freeBlk;
        curr = freeBlk.getFirst();
        Tree b;
        if(type == 2){ 
            b = new BSTree();
        }
        else{
            b = new AVLTree();
        }
        while(curr != null){
                b.Insert(curr.address,curr.size,curr.address);
                curr = curr.getNext();
        }
        Tree curr1 = b.getFirst();
        curr = freeBlk;
        while(curr1 != null){
        	Tree x = curr1.getNext();
        	if(x != null){
        		int y = curr1.address+curr1.size;
        		int z = x.address;
        		if(y == z){
        			Dictionary curr2 = freeBlk.Insert(curr1.address-1,curr1.size,curr1.size);
                                Dictionary curr3 = curr2.getNext();
                                freeBlk.Delete(curr3);
                                freeBlk.Delete(curr2);
                                curr2 = freeBlk.Insert(x.address-1,x.size,x.size);
                                curr3 = curr2.getNext();
                                freeBlk.Delete(curr3);
                                freeBlk.Delete(curr2); 
                                Tree a = b.Insert(curr1.address,curr1.size+x.size,curr1.address);
                                freeBlk.Insert(curr1.address,curr1.size+x.size,curr1.size+x.size);
                                b.Delete(curr1);
                                b.Delete(x);
        			curr1 = a;
        		}
        		else{
        			curr1 = curr1.getNext();
        		}
        	}
        	else{
        		break;
        	}
        }
        while(b.getFirst() != null){
        	b.Delete(b.getFirst());
        }
     }
}