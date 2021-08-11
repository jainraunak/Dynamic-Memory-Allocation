// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree curr = this;
        while(curr.parent != null){
            curr = curr.parent;
        }
        BSTree curr1 = null;
        while(curr != null){
        	curr1 = curr;
        	if(curr.key < key){
        		curr = curr.right;
        	}
        	else if(curr.key > key){
        		curr = curr.left;
        	}
                else{
                        if(curr.address < address){
                              curr = curr.right;
                        }
                        else{
                              curr = curr.left;
                        }
                }
        }
        BSTree x = new BSTree(address,size,key);
        if(curr1.key < key){
        	curr1.right = x;
        }
        else if(curr1.key > key){
        	curr1.left = x;
        }
        else{
             if(curr1.address < address){
                      curr1.right = x;
             }
             else{
                      curr1.left = x;
             }
        }
        x.parent = curr1;
        return x;
    }

    public boolean Delete(Dictionary e)
    {   
        BSTree curr = this;
        while(curr.parent != null){
           curr = curr.parent;
        }
        while(curr != null){
        	if(curr.key == e.key){
        		if(curr.address == e.address && curr.size == e.size){
        			BSTree x = curr.parent;
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
        				BSTree y = curr.right;
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
        				BSTree y = curr.left;
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
        				BSTree y = curr.right;
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
                        if(curr.right != null){
                            curr.right.parent = y;
                        }
                        if(curr.left != null){
                            curr.left.parent = y;
                        }
        			}
                    curr.parent = null;
                    curr.right = null;
                    curr.left = null;
                    curr = null;
                    return true;
        		}
                else{
                    if(curr.address < e.address){
                         curr = curr.right;
                    }
                    else{
                        curr = curr.left;
                    }
                }
        	}
            else if(curr.key < e.key){
                curr = curr.right;
            }
            else{
                curr = curr.left;
            }
        }
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        if(exact == true){
            BSTree curr = this;
            while(curr.parent != null){
               curr = curr.parent;
            }
            curr = curr.right;
            BSTree curr1 = null;
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
            BSTree curr = this;
            while(curr.parent != null){
               curr = curr.parent;
            }
            curr = curr.right;
            BSTree curr1 = null;
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

    public BSTree getFirst()
    { 
        BSTree curr = this;
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

    public BSTree getNext()
    { 
        BSTree curr = this;
        if(curr.parent == null){
          return null;
        }
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

    private boolean check(BSTree x){
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

    private boolean cycle(BSTree x){
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
    public boolean sanity()
    { 
        BSTree curr = this;
        BSTree curr1 = this;
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
        if(cycle(curr) == true){
            curr = curr.getFirst();
            while(curr != null){
              BSTree x = curr.getNext();
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


 


