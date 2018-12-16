#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/*
	简单排序之冒泡排序
	时间复杂度	最差O(n^2)	最好O(n)
*/

void bubbleSort(int arr[],int length){
	
	int temp;
	for(int i = length - 1;i > 0;i--){
		for(int j = 0;j < i;j++){
			if(arr[j] > arr[j + 1]){
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
	}
	
}


int main(){
	
	int N = 50000;
	int temp = N;
	int arr[N];
	
	for(int i = 0;i < N;i++){
		arr[temp--] = i;
	}
	
	int length = sizeof(arr)/sizeof(arr[0]);
	
	int begintimeB = clock();				//计时开始
	bubbleSort(arr,length);					// 排序
	int endtimeB = clock();					//计时结束
	double BubbleSort_time = (double)(endtimeB - begintimeB) / CLOCKS_PER_SEC;
	
	//BubbleSort Running Time:3.733000 seconds		时间复杂度O(50000^2)
	//BubbleSort Running Time:3.702000 seconds
	printf("\nBubbleSort Running Time:%f seconds\n",BubbleSort_time);
	printf("Compiler Edition:gcc-6.3.0\n");
	printf("Running Environment:windows 10 64bits");
	
	for(int j = 0;j < N;j += 5000){
		printf("%d\n",arr[j]);
	}
	
}