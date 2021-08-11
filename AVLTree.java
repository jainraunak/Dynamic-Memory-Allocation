// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    private void leftrotate(){
	AVLTree curr = this;
	AVLTree x = curr.left;
	curr.left = x.right;
	if(x.right != null){
		x.right.parent = curr;
	}
	x.right = curr;
	x.parent = curr.parent;
	curr.parent = x;
	if(x.parent != null){
		if(x.parent.right == curr){
			x.parent.right = x;
		}
		else{
			x.parent.left = x;
		}
	}
	int h1 = -1;
	int h2 = -1;
	if(curr.left != null){
		h1 = curr.left.height;
	}
	if(curr.right != null){
		h2 = curr.right.height;
	}
	if(h2 > h1){
		h1 = h2;
	}
	curr.height = 1+h1;
	int h3 = -1;
	if(x.left != null){
		h3 = x.left.height;
	}
	if(curr.height > h3){
		h3 = curr.height;
	}
	x.height = h3+1;
}
private void rightrotate(){
	AVLTree curr = this;
	AVLTree x = curr.right;
	curr.right = x.left;
	if(x.left != null){
		x.left.parent = curr;
	}
	x.left = curr;
	x.parent = curr.parent;
	curr.parent = x;
	if(x.parent != null){
		if(x.parent.right == curr){
			x.parent.right = x;
		}
		else{
			x.parent.left = x;
		}
	}
	int h1 = -1;
	int h2 = -1;
	if(curr.left != null){
		h1 = curr.left.height;
	}
	if(curr.right != null){
		h2 = curr.right.height;
	}
	if(h2 > h1){
		h1 = h2;
	}
	curr.height = h1+1;
	int h3 = -1;
	if(x.right != null){
		h3 = x.right.height;
	}
	if(curr.height > h3){
		h3 = curr.height;
	}
	x.height = h3+1;
}
public AVLTree Insert(int address,int size,int key){
	AVLTree curr = this;
	AVLTree curr1 = null;
	while(curr.parent != null){
		curr = curr.parent;
	}
        if(curr.right == null){
           AVLTree x = new AVLTree(address,size,key);
           curr.right = x;
           x.parent = curr;
           return x;
        }
        else{
        curr = curr.right;
	while(curr != null){
		curr1 = curr;
		if(curr.key < key){
			curr = curr.right;
		}
		else if(curr.key > key){
			curr = curr.left;
		}
		else{
			if(curr.address > address){
				curr = curr.left;
			}
			else{
				curr = curr.right;
			}
		}
	}
	AVLTree x = new AVLTree(address,size,key);
	if(curr1.key > key){
		curr1.left = x;
	}
	else if(curr1.key < key){
		curr1.right = x;
	}
    else{
    	if(curr1.address > address){
    		curr1.left = x;
    	}
    	else{
    		curr1.right = x;
    	}
    }
    x.parent = curr1;
    curr = x;
    while(curr.parent != null){
    	int h1 = -1;
    	int h2 = -1;
    	if(curr.left != null){
    		h1 = curr.left.height;
    	}
    	if(curr.right != null){
    		h2 = curr.right.height;
    	}
    	if(h1-h2 == 0 || h1-h2 == 1 || h1-h2 == -1){
             if(h2 > h1){
             	h1 = h2;
             }
             curr.height = h1+1;
             curr = curr.parent;
    	}
    	else{
    		break;
    	}
    }
    if(curr.parent !=  null){
    	int h1 = -1;
    	int h2 = -1;
    	if(curr.left != null){
    		h1 = curr.left.height;
    	}
    	if(curr.right != null){
    		h2 = curr.right.height;
    	}
    	if(h1 > h2){
    		int h3 = -1;
    		int h4 = -1;
    		if(curr.left.left != null){
    			h3 = curr.left.left.height;
    		}
    		if(curr.left.right != null){
    			h4 = curr.left.right.height;
    		}
    		if(h3 >= h4){
    			curr.leftrotate();
    		}
    		else{
    			curr.left.rightrotate();
    			curr.leftrotate();
    		}
    	}
    	else{
    		int h3 = -1;
    		int h4 = -1;
    		if(curr.right.left != null){
    			h3 = curr.right.left.height;
    		}
    		if(curr.right.right != null){
    			h4 = curr.right.right.height;
    		}
    		if(h3 > h4){
    			curr.right.leftrotate();
    			curr.rightrotate();
    		}
    		else{
    			curr.rightrotate();
    		}
    	}
    }
    return x;
   }
}
public boolean Delete(Dictionary e){
	if(e == null){
		return false;
	}
	AVLTree curr = this;
	AVLTree curr1 = null;
	while(curr.parent != null){
		curr = curr.parent;
	}
        curr = curr.right;
	while(curr != null){
		if(curr.key == e.key){
			if(curr.address == e.address && curr.size == e.size){
				break;
			}
			else if(curr.address < e.address){
				curr = curr.right;
			}
			else{
				curr = curr.left;
			}
		}
		else if(curr.key < e.key){
			curr = curr.right;
		}
		else{
			curr = curr.left;
		}
	}
	if(curr == null){
		return false;
	}
	AVLTree x = curr.parent;
	if(x == null){
		return false;
	}
	            if(curr.left == null && curr.right == null){
                        if(x.right == curr){
                            x.right = null;
                        }
                        else if(x.left == curr){
                            x.left = null;
                        }
                    }
                    else if(curr.left == null){
                        AVLTree y = curr.right;
                        if(x.right == curr){
                            x.right = y;
                            y.parent = x;
                        }
                        else{
                            x.left = y;
                            y.parent = x;
                        }
                    }
                    else if(curr.right == null){
                        AVLTree y = curr.left;
                        if(x.right == curr){
                            x.right = y;
                            y.parent = x;
                        }
                        else{
                            x.left = y;
                            y.parent = x;
                        }
                        
                    }
                    else{
                        AVLTree y = curr.right;
                        while(y.left != null){
                            y = y.left;
                        }
                        x = y.parent;
                        if(y.right == null){
                            if(x.left == y){
                                x.left = null;
                            }
                            else{
                                x.right = null;
                            }
                        }
                        else{
                            if(x.left == y){
                                x.left = y.right;
                                y.right.parent = x;
                            }
                            else{
                                x.right = y.right;
                                y.right.parent = x;
                            }
                        }
                        y.parent = curr.parent;
                        if(curr.parent.right == curr){
                            curr.parent.right = y;
                        }
                        else{
                            curr.parent.left = y;
                        }
                        y.right = curr.right;
                        y.left = curr.left;
                        y.height = curr.height;
                        if(curr.right != null){
                            curr.right.parent = y;
                        }
                        if(curr.left != null){
                            curr.left.parent = y;
                        }
                        if(x == curr){
                            x = y;
                        }
	           }
   curr.left = null;
   curr.right = null;
   curr.parent = null;
   curr.height = 0;
   curr = x;
   while(curr.parent != null){
   	  int h1 = -1;
   	  int h2 = -1;
   	  if(curr.left != null){
   	  	h1 = curr.left.height;
   	  }
   	  if(curr.right != null){
   	  	h2 = curr.right.height;
   	  }
   	  if(h1-h2 == -1 || h1-h2 == 0 || h1-h2 == 1){
   	  	if(h2 > h1){
   	  		h1 = h2;
   	  	}
   	  	curr.height = h1+1;
   	  	curr = curr.parent;
   	  }
   	  else{
   	  	if(h1 > h2){
   	  		int h3 = -1;
   	  		int h4 = -1;
   	  		if(curr.left.left != null){
   	  			h3 = curr.left.left.height;
   	  		}
   	  		if(curr.left.right != null){
   	  			h4 = curr.left.right.height;
   	  		}
   	  		if(h3 >= h4){
   	  			curr.leftrotate();
   	  		}
   	  		else{
   	  			curr.left.rightrotate();
   	  			curr.leftrotate();
   	  		}
   	  	}
   	  	else{
   	  		int h3 = -1;
   	  		int h4 = -1;
   	  		if(curr.right.left != null){
   	  			h3 = curr.right.left.height;
   	  		}
   	  		if(curr.right.right != null){
   	  			h4 = curr.right.right.height;
   	  		}
   	  		if(h3 > h4){
   	  			curr.right.leftrotate();
   	  			curr.rightrotate();
   	  		}
   	  		else{
   	  			curr.rightrotate();
   	  		}
   	  	}
   	  }
   }
   return true;       
}
    
    public AVLTree Find(int key, boolean exact)
    { 
        if(exact == true){
            AVLTree curr = this;
            while(curr.parent != null){
               curr = curr.parent;
            }
            curr = curr.right;
            AVLTree curr1 = null;
            while(curr != null){
                if(curr.key == key){
                    curr1 = curr;
                    curr = curr.left;
                }
                else if(curr.key < key){
                    curr = curr.right;
                }
                else{
                    curr = curr.left;
                }
            }
            return curr1;
        }
        else{
            AVLTree curr = this;
            while(curr.parent != null){
               curr = curr.parent;
            }
            curr = curr.right;
            AVLTree curr1 = null;
            while(curr != null){
                if(curr.key < key){
                    curr = curr.right;
                }
                else{
                    curr1 = curr;
                    curr = curr.left;
                }
            }
            return curr1;
        }
    }
    
    public AVLTree getFirst()
    { 
        AVLTree curr = this;
        while(curr.parent != null){
            curr = curr.parent;
        }
        curr = curr.right;
        while(curr != null){
            if(curr.left == null){
                break;
            }
            curr = curr.left;
        }
        return curr;
    }

    public AVLTree getNext()
    {
        AVLTree curr = this;
        if(curr.right != null){
            curr = curr.right;
            while(curr.left != null){
                curr = curr.left;
            }
            return curr;
        }
        else{
            while(curr.parent != null){
               if(curr.parent.left == curr){
                     break;
               }
               curr = curr.parent;
            }
            return curr.parent;
        }
    }

    private boolean check(AVLTree x){
	if(x == null){
		return true;
	}
	if(x == x.left || x == x.right || (x.left == x.right && x.left != null) || x == x.parent || (x.left == x.parent && x.left != null) || (x.right == x.parent && x.right != null)){
		return false;
	}
		if(x.left != null){
			if(x.left.parent != x || x.left.key > x.key){
				return false;
			}
			if(x.key == x.left.key){
				if(x.address < x.left.address){
					return false;
				}
			}
		}
		if(x.right != null){
			if(x.right.parent != x || x.right.key < x.key){
				return false;
			}
			if(x.key == x.right.key){
				if(x.address > x.right.address){
					return false;
				}
			}
		}
                if(x.parent != null){
                     if(x.parent.key < x.key){
                          if(x.parent.right != x){
                              return false;
                          }
                     }
                     else if(x.parent.key > x.key){
                          if(x.parent.left != x){
                              return false;
                          }
                     }
                     else{
                         if(x.address < x.parent.address){
                            if(x.parent.left != x){
                               return false;
                            }
                         }
                         else{
                            if(x.parent.right != x){
                               return false;
                            }
                         }
                     }
                }
		return true;
     }

    private boolean cycle(AVLTree x){
       if(x == null){
          return true;
       }
       if(check(x) == false){
         return false;
       }
       if(cycle(x.left) == true && cycle(x.right) == true){
           return true;
       }
       return false;
    }
  
    private int height(AVLTree x){
         if(x == null){
             return -1;
         }
         int h1 = height(x.left);
         int h2 = height(x.right);
         if(h2 > h1){
             h1 = h2;
         }
         return h1+1;
    }

    private boolean checkheight(AVLTree x){
        if(x == null){
          return true;
        }
        int h = x.height;
        if(x.height != height(x)){
           return false;
        }
        if(checkheight(x.left) == true && checkheight(x.right) == true){
             int h2 = height(x.left);
             int h3 = height(x.right);
             if(h2-h3 == 0 || h2-h3 == 1 || h2-h3 == -1){
               return true;
             }
             return false;
        }
        return false;
    }

    public boolean sanity()
    { 
        AVLTree curr = this;
        AVLTree curr1 = this;
        while(curr.parent != null){
          if(check(curr) == true && check(curr1) == true){
               curr1 = curr1.parent;
               curr = curr.parent;
               if(curr.parent == null){
                   break;
               }
               if(check(curr) == true){
                  curr = curr.parent;
               }
               else{
                  return false;
               }
          }
          else{
             return false;
          }
          if(curr == curr1){
             return false;
          }
        }
        curr = curr.right;
        if(curr == null){
           return true;
        }
        if(cycle(curr) == true && checkheight(curr) == true){
            curr = curr.getFirst();
            while(curr != null){
              AVLTree x = curr.getNext();
              if(x == null){
                break;
              }
              if(curr.key > x.key){
                 return false;
              }
              if(curr.key == x.key && curr.address > x.address){
                   return false;
              }
              curr = curr.getNext();
            }
            return true;
        }
        return false;
    }
}

