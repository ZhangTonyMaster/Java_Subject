#include <stdio.h>

/*
	简单排序之插入排序
	时间复杂度 最差O(n^2)	最好O(n)
*/

void insertSort(int arr[],int length){
	
	int temp;
	int p,j;
	for(p = 1;p < length;p++){
		temp = arr[p];
		for(j = p; j > 0 && arr[j - 1] > temp;j--){
			arr[j] = arr[j - 1];
		}
		arr[j] = temp;
	}
	
	for(int i = 0;i < length;i++){
		printf("%d ",arr[i]);
	}
}

int main(){
	
	int arr[] = {4,6,7,9,0,3,1,2,5,8};
	int length = sizeof(arr)/sizeof(arr[0]);
	insertSort(arr,length);
}