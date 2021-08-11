// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; 
    private A1List prev;  

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        A1List tailSentinel = new A1List(-1,-1,-1); 
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List l = new A1List(address,size,key);
        A1List a = this.next;
        if(a != null){
            a.prev = l;
        }
        l.next = a;
        this.next = l;
        l.prev = this;
        return l;
    }

    public boolean Delete(Dictionary d) 
    {
        if(d == null){
           return false;
        }
        A1List curr = this;
        while(curr != null){
            if(curr.key == d.key){
                if(curr == d){
                    if(curr.prev != null){
                        curr.prev.next = curr.next;
                    }
                    else{
                        return false;
                    }
                    if(curr.next != null){
                        curr.next.prev = curr.prev;
                    }
                    else{
                        return false;
                    }
                    curr.next = null;
                    curr.prev = null;
                    d = null;
                    curr = null;
                    return true;
                }
            }
            curr = curr.next;
        }
        curr = this;
        while(curr != null){
            if(curr.key == d.key){
                if(curr == d){
                    if(curr.prev != null){
                        curr.prev.next = curr.next;
                    }
                    else{
                        return false;
                    }
                    if(curr.next != null){
                        curr.next.prev = curr.prev;
                    }
                    else{
                       return false;
                    }
                    curr.next = null;
                    curr.prev = null;
                    d = null;
                    curr = null;
                    return true;
                }
            }
            curr = curr.prev;
        }
        curr = this;
        while(curr != null){
            if(curr.key == d.key){
                if(curr.address == d.address && curr.size == d.size){
                    if(curr.prev != null){
                        curr.prev.next = curr.next;
                    }
                    else{
                        return false;
                    }
                    if(curr.next != null){
                        curr.next.prev = curr.prev;
                    }
                    else{
                        return false;
                    }
                    curr.next = null;
                    curr.prev = null;
                    curr = null;
                    return true;
                }
            }
            curr = curr.next;
        }
        curr = this;
        while(curr != null){
            if(curr.key == d.key){
                if(curr.address == d.address && curr.size == d.size){
                    if(curr.prev != null){
                        curr.prev.next = curr.next;
                    }
                    else{
                        return false;
                    }
                    if(curr.next != null){
                        curr.next.prev = curr.prev;
                    }
                    else{
                       return false;
                    }
                    curr.next = null;
                    curr.prev = null;
                    curr = null;
                    return true;
                }
            }
            curr = curr.prev;
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        if(exact == true){
            A1List curr = this;
            while(curr.prev != null){
               curr = curr.prev;
            }
            curr = curr.next;
            while(curr != null){
               if(curr.key == k){
                   return curr;
               }
               curr = curr.next;
            }
        }
        else{
            A1List curr = this;
            while(curr.prev != null){
               curr = curr.prev;
            }
            curr = curr.next;
            while(curr != null){
               if(curr.key >= k){
                   return curr;
               }
               curr = curr.next;
            }
        }
        return null;
    }

    public A1List getFirst()
    {
        A1List curr = this;
        while(curr.prev != null){
            curr = curr.prev;
        }
        curr = curr.next;
        if(curr.next == null){
            curr = null;
        }
        return curr;
    }
    
    public A1List getNext() 
    {
        if(this.next != null && this.next.next == null){
               return null;
        }
        return this.next;
    }

    public boolean sanity()
    {
        A1List curr = this;
        A1List curr1 = this;
        while(curr != null && curr1 != null){
        	curr1 = curr1.next;
        	curr = curr.next;
        	if(curr != null){
        		curr = curr.next;
        	}
        	else{
        		break;
        	}
        	if(curr1 == curr){
        		return false;
        	}
        }
        curr = this;
        curr1 = this;
        while(curr != null && curr1 != null){
        	curr1 = curr1.prev;
        	curr = curr.prev;
        	if(curr != null){
        		curr = curr.prev;
        	}
        	else{
        		break;
        	}
        	if(curr1 == curr){
        		return false;
        	}
        }
        curr = this;
        while(curr.next != null){
        	if(curr.next.prev != curr || curr.next == curr || curr.next == curr.prev || curr.prev == curr){
        		return false;
        	}
        	curr = curr.next;
        }
        curr = this;
        while(curr.prev != null){
        	if(curr.prev.next != curr || curr.next == curr || curr.next == curr.prev || curr.prev == curr){
        		return false;
        	}
        	curr = curr.prev;
        }
        curr = this;
        while(curr.next != null){
            curr = curr.next;
        }
        if(curr.key != -1 || curr.address != -1 || curr.size != -1){
            return false;
        }
        curr = this;
        while(curr.prev != null){
            curr = curr.prev;
        }
        if(curr.key != -1 || curr.address != -1 || curr.size != -1){
            return false;
        }
        return true;
    }
}