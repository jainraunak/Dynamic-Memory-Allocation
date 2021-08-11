public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }
    public int Allocate(int blockSize) {
        Dictionary x = freeBlk.Find(blockSize,false);
        if(x == null || blockSize <= 0){
            return -1;
        }
        else{
            int r = x.size;
            if(r > blockSize){
                allocBlk.Insert(x.address,blockSize,x.address);
                freeBlk.Insert(x.address+blockSize,x.size-blockSize,x.size-blockSize);
                int s = x.address;
                freeBlk.Delete(x);
                return s;
            }
            else{
                allocBlk.Insert(x.address,blockSize,x.address);
                int s = x.address;
                freeBlk.Delete(x);
                return s;
            }
        }
    } 
    
    public int Free(int startAddr) {
         Dictionary l = allocBlk.Find(startAddr,true);
         if(l == null){
             return -1;
         }
         else{
             freeBlk.Insert(l.address,l.size,l.size);
             allocBlk.Delete(l);
             return 0;
         }
    }
}