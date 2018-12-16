#include <stdio.h>

/*
	希尔排序
	~~~???~~~
*/

void shellSort(int arr[],int length){
	
	int p,j;
	int temp;
	for(int i = length/2;i > 0;i /= 2){
		for(p = i;p < length;p++){
			temp = arr[p];
			for(j = p;j >= i&&arr[j - i] > temp;j -= i){
				arr[j] = arr[j - length];
			}
			arr[j] = temp;
		}
	}
	
	for(int i = 0;i < length;i++){
		printf("%d ",arr[i]);
	}
}

int main(){
	
	int arr[] = {2,3,1,4,5,7,9,8,6,0};
	int length = sizeof(arr)/sizeof(arr[0]);
	shellSort(arr,length);
	
}