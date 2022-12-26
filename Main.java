package com.highpeak;
	import java.util.*;
	public class Main
	{
		public static void main(String[] args) {
		    Scanner sc=new Scanner(System.in);
		    System.out.println("Enter the number of jobs");
		    int n=sc.nextInt();
		    System.out.println("Enter job start time, end time, and earnings");
		    int[] st=new int[n];
		    int[] et=new int[n];
		    int[] ern=new int[n];
		    for(int i=0;i<n;i++)
		    {
		        int temp;
		        temp=sc.nextInt();
		        if(temp<2359)
		        {
		            st[i]=temp;
		        }
		        else{
		            st[i]=0000;
		        }
		        int temp1;
		        temp1=sc.nextInt();
		        if(temp<2359)
		        {
		            et[i]=temp1;
		        }
		        else{
		            et[i]=0000;
		        }
		        ern[i]=sc.nextInt();
		    }
		    System.out.println("The number of tasks and earnings available for others");
		    Solution sol=new Solution();
		    sol.jobScheduling(st,et,ern);
		   Arrays.sort(ern);
		   ern[n-1]=0;
		   int sum=0;
		   for(int i=0;i<n-1;i++)
	        {
	            
	            if(ern[i]!=0)
	            {
	                sum+=ern[i];
	            }
	        }
		   System.out.println("Earnings: "+sum);
		}
	}
	class Solution {
	    public void jobScheduling(int[] startTime, int[] endTime, int[] profit) {
	        int n = startTime.length;
	        Time[] times = new Time[n];
	        for (int i = 0;i < n;i++) {
	            times[i] = new Time(startTime[i], endTime[i], profit[i]);
	        }
	        Arrays.sort(times);
	        int[] dp = new int[n];
	        dp[0] = times[0].profit;
	        for (int i = 1;i < n;i++) {
	            int j = binarySearch(times, 0, i - 1, times[i]);
	            if (j == -1) dp[i] = Math.max(dp[i - 1], times[i].profit);
	            else dp[i] = Math.max(dp[i - 1], dp[j] + times[i].profit);
	        }
	        System.out.println("Tasks: ");
	        Arrays.sort(dp);
	        dp[n-1]=0;
	        int count=0;
	        for(int i=0;i<n-1;i++)
	        { 
	            if(dp[i]!=0)
	            {
	                count++;
	            }
	        }
	        System.out.println(count);
	    }
	    private int binarySearch(Time[] times, int l, int r, Time t) {
	        while (l <= r) {
	            int m = (l + r) / 2;
	            if (times[m].end <= t.start) {
	                l = m + 1;
	            } else { // end > start
	                r = m - 1;
	                
	            }
	        }
	        
	        return r;
	    }
	}
	class Time implements Comparable<Time> {
	    int start;
	    int end;
	    int profit;
	    
	    public Time(int start, int end, int profit) {
	        this.start = start;
	        this.end = end;
	        this.profit = profit;
	    }
	    
	    public int compareTo(Time t) {
	        return Integer.valueOf(end).compareTo(Integer.valueOf(t.end));
	    }
	}


