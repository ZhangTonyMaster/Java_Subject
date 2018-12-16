#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/*
	算法导论2.3
	分治归并算法
	时间复杂度	O(nlgn)
*/


/*
	parameter arr[]:数组实例
	parameter p:指向数组第一个元素
	parameter q:指向分离数组的元素
	parameter r:指向数组最后一个元素
	p=0,q=3,r=7
*/

/*
void merge(int arr[],int p,int q,int r){
	
	int n1 = q - p + 1;
	int n2 = r - q;
	
	int *L = (int*)malloc(sizeof(int) * n1);
	int *R = (int*)malloc(sizeof(int) * n2);
	
	for(int i = 0;i < n1;i++){
		L[i] = arr[i];
	}
	//L[n1] = -1;
	
	for(int i = 0;i < n2;i++){
		R[i] = arr[n1 + i];
	}
	//R[n2] = -1;
	
	int ptrL = 0;
	int ptrR = 0;
	
	for(int i = p;i <= r;i++){
		
		if(ptrL == n1 && ptrR != n2){
			arr[i] = R[ptrR];
		}
		else if(ptrR == n2 && ptrR != n1){ 
			arr[i] = L[ptrL];
		}
		
		if(L[ptrL] <= R[ptrR]){
			arr[i] = L[ptrL];
			ptrL++;
		}
		else{
			arr[i] = R[ptrR];
			ptrR++;
		}
		
	}
	
}


void mergeSort(int arr[],int p,int r){
	
	if(p < r){
		int q = (p + r)/2;
		mergeSort(arr,p,q);
		mergeSort(arr,q+1,r);
		merge(arr,p,q,r);
	}
	
}


int main(){
	
	int arr[] = {5,2,1,0,7,4,6,3};
	int length = sizeof(arr)/sizeof(arr[0]);
	int p = 0;
	int r = length - 1;
	
	//int q = (p + r)/2;
	//merge(arr,p,q,r);
	
	mergeSort(arr,p,r);
	
	for(int i = 0;i < length;i++){
		printf("%d ",arr[i]);
	}
	
}
*/


void merge(int arr[], int low, int mid, int high){
	
    int i, k;
    int *tmp = (int*)malloc((high-low+1)*sizeof(int));
    //申请空间，使其大小为两个
    int left_low = low;
    int left_high = mid;
    int right_low = mid + 1;
    int right_high = high;

    for(k=0; left_low<=left_high && right_low<=right_high; k++){  
		//比较两个指针所指向的元素
        if(arr[left_low]<=arr[right_low]){
            tmp[k] = arr[left_low++];
        }else{
            tmp[k] = arr[right_low++];
        }
    }

    if(left_low <= left_high){  
		//若第一个序列有剩余，直接复制出来粘到合并序列尾
		//memcpy(tmp+k, arr+left_low, (left_high-left_low+l)*sizeof(int));
		for(i=left_low;i<=left_high;i++)
			tmp[k++] = arr[i];
    }

    if(right_low <= right_high){
		//若第二个序列有剩余，直接复制出来粘到合并序列尾
		//memcpy(tmp+k, arr+right_low, (right_high-right_low+1)*sizeof(int));
        for(i=right_low; i<=right_high; i++)
            tmp[k++] = arr[i];
    }
	
    for(i=0; i<high-low+1; i++)
        arr[low+i] = tmp[i];
	
	//释放临时数组空间
    free(tmp);

}

void merge_sort(int arr[], unsigned int first, unsigned int last){
    int mid = 0;
    if(first<last){
        mid = (first+last)/2; /* 注意防止溢出 */
        /*mid = first/2 + last/2;*/
        //mid = (first & last) + ((first ^ last) >> 1);
        merge_sort(arr, first, mid);
        merge_sort(arr, mid+1,last);
        merge(arr,first,mid,last);
    }

}

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

int compare (const void * a, const void * b){
	return ( *(int*)a - *(int*)b );
}

int main(){
	
	int N = 50000;
	int temp = N;
	int b[N];
	
	for(int i = 0;i < N;i++){
		b[temp--] = i;
	}
	
	int begintimeM = clock();				//计时开始
	merge_sort(b,0,N-1);  					// 排序
	int endtimeM = clock();					//计时结束
	double MergeSort_time = (double)(endtimeM - begintimeM) / CLOCKS_PER_SEC;
	
	//MergeSort Running Time:0.015000 seconds		时间复杂度O(50000lg50000)
	//MergeSort Running Time:0.000000 seconds		效率大约为冒泡排序的250倍
	printf("\nMergeSort Running Time:%f seconds\n",MergeSort_time);
	printf("Compiler Edition:gcc-6.3.0\n");
	printf("Running Environment:windows 10 64bits");
	
    printf ("\nAfter Sort \n");
    for(int j = 0;j < N;j += 5000){
        printf("%d\n",b[j]);
	}
	
}