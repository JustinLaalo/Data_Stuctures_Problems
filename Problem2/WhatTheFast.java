package comp2402a2;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import comp2402a2.FastSparrow;







public class WhatTheFast implements WhatTheDeque{
  
  FastSparrow front;
  FastSparrow back;
  
  
  public WhatTheFast() {
    front = new FastSparrow();
    back = new FastSparrow();
    
  }

  public void addFirst(Integer x) {
    front.push(x);
    balance();
    
    
    
  }

  public void addLast(Integer x) {
    back.push(x);
    balance();
    
    
    
  }

  public Integer removeFirst(){
    if(size() <= 0){
      return null;
    }else{
      if(front.size() == 0){
        Integer var = back.pop();
        balance();
        return var;
      }else{
        Integer var = front.pop();
        balance();
        return var;}
      
    } 
  }
  
  public Integer removeLast() {
    if(size() <= 0)
      return null;
    else{
      if(back.size() == 0){
        Integer var = front.pop();
        balance();
        return var;
      }else{
        Integer var = back.pop();
        balance();
        return var;
      }
    }
  }
  

  public Integer max() {
    if(front.max() == null && back.max() == null){
      return null;
    }else if((front.max() == null)){
      return back.max();
    }else if(back.max() == null){
      return front.max();
    }else if(front.max() >= back.max()){
      return front.max();
    }else if(front.max() < back.max()){
      return back.max();
    }else{
      return null;
    }
    
  }

  public long ksumFirst(int k) {
    if(k == 0 || size() == 0){
      return (long)0;
    }else if(k < front.size()){
      return (long)front.ksum(k);
    }else{
      return (long)front.ksum(front.size()) + back.ksum(back.size()) - back.ksum(back.size() - (k-front.size()));
    }
    
  }

  public long ksumLast(int k){
    if(k == 0 || size() == 0){
      return (long)0;
    }else if(k < back.size()){
      return (long)back.ksum(k);
    }else{
      return (long)back.ksum(back.size()) + front.ksumfirst(k-back.size());
    }
  }

  public int size() {
    return front.size()+back.size();
  }

  public Iterator<Integer> iterator() {
    ArrayList<Integer>iter = new ArrayList<>();
    for(int i = front.size()-1; i >= 0; i-- ){
      iter.add(front.get(i));
    }
    for(int i = 0; i <= back.size()-1; i++){
      iter.add(back.get(i));
    }

    return iter.iterator();
    
    
  }

//took from text book, chapter on dual array deque
void balance(){ 
  int n = size(); 

  if (3*front.size() < back.size()) { 
    
    int s = n/2 - front.size(); 
    FastSparrow l1 = new FastSparrow(); 
    FastSparrow l2 = new FastSparrow(); 
    
    for(int i = s-1; i >= 0; i--){
      l1.push(back.get(i));
    }
    for(int i = 0; i <= front.size()-1; i++){
      l1.push(front.get(i));
    }
    for(int i = s; i <= back.size()-1; i++){
      l2.push(back.get(i));
    }
    front = l1; 
    back = l2; 
} else if (3*back.size() < front.size()) { 
    int s = front.size() - n/2; 
    FastSparrow l1 = new FastSparrow(); 
    FastSparrow l2 = new FastSparrow(); 
    for(int i = s; i <= front.size()-1; i++){
      l1.push(front.get(i));
    }
     
    for(int i = s-1; i >= 0; i--){
      l2.push(front.get(i));
    }
    
    for(int i = 0; i <= back.size()-1; i++){
      l2.push(back.get(i));
    }
    front = l1; 
    back = l2; 

  } 

}

}


