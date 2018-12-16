#include <stdio.h>
#include <stdlib.h>

/*
	AVL树是二叉平衡树，要求左右子树高度差(平衡因子)小于2，同时具有二叉排序树的有序性
	可以实现O(log2N)的查询复杂度
	优点是有效提高了大量数据下的查询效率，缺点是在插入时需要控制左右子树的平衡因子
*/

typedef struct AVLNode {
	int data;
	struct AVLNode *Lchild;
	struct AVLNode *Rchild;
	int height;
}AVLNode;

//树高度
int getHeight(AVLNode *node){
	
	int Lheight;
	int Rheight;
	
	if(node != NULL){
		Lheight = getHeight(node->Lchild);
		Rheight = getHeight(node->Rchild);
	}
	else{
		return 0;
	}
	
	if(Lheight > Rheight){
		return Lheight + 1;
	}
	else{
		return Rheight + 1;
	}
}

//左单旋(LL型)
AVLNode* singleLeftRotation(AVLNode *root){
	
	AVLNode *left = root->Lchild;
	root->Lchild = left->Rchild;		//难点
	left->Rchild = root;
	
	left->height = getHeight(left);
	
	return left;
}

//右单旋(RR型)
AVLNode* singleRightRotation(AVLNode *root){
	
	AVLNode *right = root->Rchild;
	root->Rchild = right->Lchild;
	right->Lchild = root;
	
	right->height = getHeight(right);
	
	return right;
}  

//右左双旋(LR型)
AVLNode* doubleRightLeftRotation(AVLNode *root){
	
	root->Lchild = singleRightRotation(root->Lchild);
	
	return singleLeftRotation(root);
} 

//左右双旋(RL型)
AVLNode* doubleLeftRightRotation(AVLNode *root){
	
	root->Rchild = singleLeftRotation(root->Rchild);
	
	return singleRightRotation(root);
}

//插入
AVLNode* insert(AVLNode *node,int data) {
	
	//如果插入节点为空，则创建一个新节点
	if(node == NULL){
		node = (AVLNode*)malloc(sizeof(AVLNode));
		node->Lchild = node->Rchild = NULL;
		node->height = 0;
		node->data = data;
	}
	else if(data < node->data){
		//小于根节点，递归插入在左子树左节点
		node->Lchild = insert(node->Lchild,data);
		//左右子树高度差为2时，平衡被破环，需要通过旋转达到平衡
		if(getHeight(node->Lchild) - getHeight(node->Rchild) == 2){
			//如果小于左子树，插入在左子树左节点，形成LL型单链表，需要左旋
			if(data < node->Lchild->data){
				node = singleLeftRotation(node);
			}
			//如果大于左子树，插入在左子树右节点，形成LR型单链表，需要右左双旋
			else{
				node = doubleRightLeftRotation(node);
			}
		}
	}
	else if(data > node->data){
		node->Rchild = insert(node->Rchild,data);
		if(getHeight(node->Lchild) - getHeight(node->Rchild) == -2){
			if(data > node->Rchild->data){
				node = singleRightRotation(node);
			}
			else{
				node = doubleLeftRightRotation(node);
			}
		}	
	}
	//更新树高
	node->height = getHeight(node);
	return node;
}

//中序遍历
void inOrder(AVLNode *node) {
	
	if (node != NULL) {
		inOrder(node->Lchild);
		printf("%d ",node->data);
		inOrder(node->Rchild);
	}
}

//返回最小值
AVLNode* findMin(AVLNode *root){
	
	while(root->Lchild != NULL){
		root = root->Lchild;
	}
	return root;
}

//返回最大值
AVLNode* findMax(AVLNode *root){
	
	while(root->Rchild != NULL){
		root = root->Rchild;
	}
	return root;
}

//查询
AVLNode* find(AVLNode *root,int data){
	
	while(root != NULL){
		if(data < root->data){
			root = root->Lchild;
		}
		else if(data > root->data){
			root = root->Rchild;
		}
		else{
			return root;
		}
	}
}

//删除
AVLNode* delete(AVLNode *root,int data){
	
	AVLNode *temp;
	
	if(root == NULL){
		printf("the data that you want to delete is not exist...\n");
	}
	else{	
		if(data < root->data){
			root->Lchild = delete(root->Lchild,data);
			//在回溯过程中判断子树高度差（平衡因子），如不平衡则根据正负符号判断左旋或右旋
			if(getHeight(root->Lchild) - getHeight(root->Rchild) == 2){
				root = singleLeftRotation(root);
			}
			else if(getHeight(root->Lchild) - getHeight(root->Rchild) == -2){
				root = singleRightRotation(root);
			}
		}
		else if(data > root->data){
			root->Rchild = delete(root->Rchild,data);
			if(getHeight(root->Lchild) - getHeight(root->Rchild) == 2){
				root = singleLeftRotation(root);
			}
			else if(getHeight(root->Lchild) - getHeight(root->Rchild) == -2){
				root = singleRightRotation(root);
			}
		}
		else{
			if(root->Lchild != NULL && root->Rchild != NULL){
				temp = findMax(root->Lchild);
				root->data = temp->data;
				root->Lchild = delete(root->Lchild,data);
			}
			else{
				temp = root;
				if(root->Lchild == NULL){
					root = root->Rchild;
				}
				else if(root->Rchild == NULL){
					root = root->Lchild;
				}
				free(temp);
			}
		}
	}
	return root;
}


int main(){
	
	AVLNode *root = NULL;
	int arr[] = {5,3,6,1,4,0,2};
	
	printf("1.execute insert nodes of value(5,3,6,1,4,0,2)\n");
	for(int i = 0;i < sizeof(arr)/sizeof(arr[0]);i++){
		root = insert(root,arr[i]);
	}
	
	printf("print by inorder (sort):\n");
	inOrder(root);
	printf("\nroot->data=%d\n",root->data);
	printf("root->Lchild->data=%d\n",root->Lchild->data);
	printf("root->Rchild->data=%d\n",root->Rchild->data);
	printf("height of AVLTree=%d\n",root->height);
	printf("min of AVLTree=%d\n",findMin(root)->data);
	printf("max of AVLTree=%d\n",findMax(root)->data);
	printf("be searched value = 4, be searched node addr=%p\n",find(root,4));
	
	printf("-----------------------------------------------\n");
	
	printf("2.execute delete nodes of value(0,1,2) (left child tree of AVLTree)\n");
	root = delete(root,0);
	root = delete(root,1);
	root = delete(root,2);
	
	printf("print by inorder (sort):\n");
	inOrder(root);
	printf("\nroot->data=%d\n",root->data);
	printf("root->Lchild->data=%d\n",root->Lchild->data);
	printf("root->Rchild->data=%d\n",root->Rchild->data);
	printf("height of AVLTree=%d\n",root->height);
	printf("min of AVLTree=%d\n",findMin(root)->data);
	printf("max of AVLTree=%d\n",findMax(root)->data);
	printf("be searched value = 4, be searched node addr=%p\n",find(root,4));
}