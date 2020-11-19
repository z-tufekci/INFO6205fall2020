package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 1000;
    public static int threadCount = 2;
	public static ForkJoinPool myPool = new ForkJoinPool(threadCount);


	
    public static void sort(int[] array, int from, int to) {
        if (to - from < cutoff) Arrays.sort(array, from, to); 
        //if ( to - from <= 1 ) return; 
        else {
        	
        	
            CompletableFuture<int[]> parsort1 = parsort(array, from, from + (to - from) / 2); 
            CompletableFuture<int[]> parsort2 = parsort(array, from + (to - from) / 2, to); 
            //System.out.println("# Running threads: "+ myPool.getRunningThreadCount());
            //System.out.println(myPool.getPoolSize());
            //System.out.println("# Active threads:"+ myPool.getActiveThreadCount()); 
    		

            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> { 
         
            	int[] result = new int[xs1.length + xs2.length];

                int index1 = 0, index2 = 0;
                for(int i=0; i < result.length; i++) {
                	    if(index2 >= xs2.length)
                	    	result[i] = xs1[index1++];
                	    else if( index1 >= xs1.length)
                	    	result[i] = xs2[index2++];
                	    else if(xs1[index1] <= xs2[index2])
                	       	result[i] = xs1[index1++];
                		else if(xs1[index1] > xs2[index2])
                			result[i] = xs2[index2++];
                }

                return result;
            });
                  
            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));

            
            parsort.join();
        }
    }
    
    
    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                	
                    int[] result = new int[to - from];

                    for (int k = 0, t = from ; t < to; k++, t++)
            			result[k] = array[t];
            	    int mid = (to - from) / 2;
                    int i = 0, j = mid + 1;
            		
            		for (int k = from; k < to; k++) {   

            			if (i > mid)							 
            				array[k] = result[ j++];
            			else if (j > (to - from))
            				array[k] = result[ i++];
            			else if (j < (to - from) && result[j] < result[i])
            				array[k] = result[ j++];
            			else
            				array[k] = result[ i++];
            		}
            		
                    System.arraycopy(array, from, result, 0, result.length);
                    
                    //System.out.println("# threads: "+ myPool.getRunningThreadCount());
            		//System.out.println("Active thread count:"+ myPool.getActiveThreadCount()); 
            		
                    sort(result, 0, to - from);
                    
                    return result;
                }, myPool
        );
    }
}