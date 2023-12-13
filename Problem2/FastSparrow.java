package comp2402a2;

import java.util.Iterator;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FastSparrow implements CapnStackSparrow {
  ArrayList<Integer> num;
  ArrayList<Integer> bing;
  ArrayList<Long> sums;
  int max = 0;
  long sum = 0;
  long newSum = 0;
  public FastSparrow() {
    num = new ArrayList<>();
    bing = new ArrayList<>();
    sums = new ArrayList<>();
  }



  public Integer get(int x){
    if (num.size() > 0){
      return num.get(x);
    }
    return 0;
  }
  public void push(Integer x) {
    if(x > max){
      bing.add(x);
      max = x;
    }else{
      bing.add(max);
    }

    sum += x;
    sums.add(sum);

    num.add(x);
  }

  public Integer pop(){
    if(size() <= 0)
      return null;
    else{
      bing.remove(bing.size()-1);
      if(bing.size() <= 0){
        max = 0;
      }else{
        max = bing.get(bing.size()-1);
      }
      sum -= num.get(size()-1);
      sums.remove(sums.size()-1);
      return num.remove(size()-1);
    }
  }
  public Integer max(){
    if(bing.isEmpty()){
      return null;
    }else{
      return max;
    }
  }


  public long ksum(int k){
    if(k == 0){
      return (long)0;
    }else if(sums.size() == 0){
      return (long)0;
    }
    else if(k >= sums.size()){
      return (long)(sums.get(sums.size()-1));
    }
    
    else{
      
      return (long)(sums.get(sums.size()-1) - sums.get((sums.size()-1)-k));

    }
    
  }
  //added
  public long ksumfirst(int k){
    if(k == 0){
      return (long)0;
    }else if(sums.size() == 0){
      return (long)0;
    }else{
      if(k >= sums.size()){
        return (long)(sums.get(sums.size()-1));
      }else{
        return (long)(sums.get(k-1));
      }
    }
  }

  public int size() {
    return num.size();
  }

  public Iterator<Integer> iterator(){
    return num.iterator();
  }
}

