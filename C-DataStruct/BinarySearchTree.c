#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/*
	二叉搜索排序树
	中序遍历为有序输出(小->大)

*/

typedef struct BSTree {
	int data;
	struct BSTree *Lchild;
	struct BSTree *Rchild;
}BSTree;

BSTree* createBSTnode(int data) {

	BSTree *node = (BSTree*)malloc(sizeof(BSTree));
	node->data = data;
	node->Lchild = NULL;
	node->Rchild = NULL;
	return node;
}

//二叉树的左子树小于根节点，右子树大于根节点(log2N)
int insertBSTree(BSTree **node,int data) {

	BSTree *parent = NULL;
	BSTree *cur = *node;

	if (cur == NULL) {
		*node = createBSTnode(data);
		return 1;
	}

	while (cur != NULL){
		if (data < cur->data) {
			parent = cur;
			cur = cur->Lchild;
		}
		else if (data > cur->data) {
			parent = cur;
			cur = cur->Rchild;
		}
		else {
			return 0;
		}
	}

	if (data < parent->data) {
		parent->Lchild = createBSTnode(data);
	}
	else if(data > parent->data) {
		parent->Rchild = createBSTnode(data);
	}

	return 1;
}


BSTree* findData(BSTree *node,int data) {

	while (node != NULL){
		if (data == node->data) {
			printf("\n找到了数据为%d的节点,地址为:%p\n",data,node);
			return node;
		}
		else if (data < node->data) {
			node = node->Lchild;
		}
		else {
			node = node->Rchild;
		}
	}
	if (node == NULL) {
		printf("\n没找到......");
	}
}


BSTree* findMin(BSTree *node) {

	while (node->Lchild != NULL){
		node = node->Lchild;
	}
	return node;
}


BSTree* findMax(BSTree *node) {

	while (node->Rchild != NULL){
		node = node->Rchild;
	}
	return node;
}


BSTree* deleteNode(BSTree *node,int data) {
	
	BSTree *temp;
	
	if(node == NULL){
		printf("要删除的元素未找到\n");
	}
	else if(data < node->data){
		node->Lchild = deleteNode(node->Lchild,data);
	}
	else if(data > node->data){
		node->Rchild = deleteNode(node->Rchild,data);
	}
	else{
		if(node->Lchild != NULL && node->Rchild != NULL){
			temp = findMin(node->Rchild);
			node->data = temp->data;
			node->Rchild = deleteNode(node->Rchild,node->data);
		}
		else{
			temp = node;
			if(node->Lchild == NULL){
				node = node->Rchild;
			}
			else if(node->Rchild == NULL){
				node = node->Lchild;
			}
			free(temp);
		}
	}
	return node;
}


void preorderBST(BSTree *node) {

	if (node != NULL) {
		printf("%d ",node->data);
		preorderBST(node->Lchild);
		preorderBST(node->Rchild);
	}
}


void inorderBST(BSTree *node) {
	
	if (node != NULL) {
		inorderBST(node->Lchild);
		printf("%d ",node->data);
		inorderBST(node->Rchild);
	}
}


void postorderBST(BSTree *node) {

	if (node != NULL) {
		postorderBST(node->Lchild);
		postorderBST(node->Rchild);
		printf("%d ",node->data);
	}
}


int heightOfBSTree(BSTree *node){
	
	int Lheight;
	int Rheight;
	
	if(node != NULL){
		Lheight = heightOfBSTree(node->Lchild);
		Rheight = heightOfBSTree(node->Rchild);
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


//AVL树平衡因子
int balanceFactor(BSTree *node){
	
	return abs(heightOfBSTree(node->Lchild) - heightOfBSTree(node->Rchild));
}

//LL型右旋方法
BSTree* rotateLL(BSTree *root){
	
	BSTree *temp = root->Lchild;
	root->Lchild = NULL;
	temp->Rchild = root;
	return temp;
}

//RR型左旋方法
BSTree* rotateRR(BSTree *root){
	
	BSTree *temp = root->Rchild;
	root->Rchild = NULL;
	temp->Lchild = root;
	return temp;
}

//LR型双旋方法
BSTree* rotateLR(BSTree *root){
	
	BSTree *Ltemp = root->Lchild;
	BSTree *LRtemp = root->Lchild->Rchild;
	root->Lchild->Rchild = NULL;
	root->Lchild = NULL;
	LRtemp->Lchild = Ltemp;
	LRtemp->Rchild = root;
	return LRtemp;
}

//RL型双旋方法
BSTree* rotateRL(BSTree *root){
	
	BSTree *Rtemp = root->Rchild;
	BSTree *RLtemp = root->Rchild->Lchild;
	root->Rchild->Lchild = NULL;
	root->Rchild = NULL;
	RLtemp->Lchild = root;
	RLtemp->Rchild = Rtemp;
	return RLtemp;
}

//BST树==》AVL树
void BSTtoAVLTree(BSTree *node){
	
	if(node != NULL){
		
		BSTtoAVLTree(node->Lchild);
		BSTtoAVLTree(node->Rchild);	
		
		if(balanceFactor(node) > 1){
			if((node)->Lchild->Lchild != NULL && (node)->Lchild->Rchild == NULL){
				node = rotateLL(node);
			}
			else if((node)->Rchild->Rchild != NULL && (node)->Rchild->Lchild == NULL){
				node = rotateRR(node);
			}
			else if((node)->Lchild->Rchild != NULL && (node)->Lchild->Lchild == NULL){
				node = rotateLR(node);
			}
			else if((node)->Rchild->Lchild != NULL && (node)->Rchild->Rchild == NULL){
				node = rotateRL(node);
			}
		}
	}		
}

int main() {

	BSTree *Tnode = NULL;

	int arr[] = {5,6,9,3,4,2,8,1,9,0};
	for (int i = 0; i < sizeof(arr) / sizeof(arr[0]);i++) {
		insertBSTree(&Tnode,arr[i]);
	}
	
	//遍历
	printf("中序遍历(小->大):");
	inorderBST(Tnode);
	printf("\n先序遍历(插入顺序):");
	preorderBST(Tnode);
	printf("\n后序遍历:");
	postorderBST(Tnode);

	//查找
	BSTree *Fnode = findData(Tnode,3);
	BSTree *min = findMin(Tnode);
	BSTree *max = findMax(Tnode);
	printf("最小值:%d\n",min->data);
	printf("最大值:%d\n",max->data);
	//删除
	printf("删除数据为3的节点后,中序遍历:");
	deleteNode(Tnode,3);
	inorderBST(Tnode);
	
	/*
	//二叉排序树转为二叉平衡树(AVL树)
	BSTtoAVLTree(Tnode);
	inorderBST(Tnode);
	*/
}
