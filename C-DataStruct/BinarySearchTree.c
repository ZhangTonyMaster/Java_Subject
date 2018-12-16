#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/*
	��������������
	�������Ϊ�������(С->��)

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

//��������������С�ڸ��ڵ㣬���������ڸ��ڵ�(log2N)
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
			printf("\n�ҵ�������Ϊ%d�Ľڵ�,��ַΪ:%p\n",data,node);
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
		printf("\nû�ҵ�......");
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
		printf("Ҫɾ����Ԫ��δ�ҵ�\n");
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


//AVL��ƽ������
int balanceFactor(BSTree *node){
	
	return abs(heightOfBSTree(node->Lchild) - heightOfBSTree(node->Rchild));
}

//LL����������
BSTree* rotateLL(BSTree *root){
	
	BSTree *temp = root->Lchild;
	root->Lchild = NULL;
	temp->Rchild = root;
	return temp;
}

//RR����������
BSTree* rotateRR(BSTree *root){
	
	BSTree *temp = root->Rchild;
	root->Rchild = NULL;
	temp->Lchild = root;
	return temp;
}

//LR��˫������
BSTree* rotateLR(BSTree *root){
	
	BSTree *Ltemp = root->Lchild;
	BSTree *LRtemp = root->Lchild->Rchild;
	root->Lchild->Rchild = NULL;
	root->Lchild = NULL;
	LRtemp->Lchild = Ltemp;
	LRtemp->Rchild = root;
	return LRtemp;
}

//RL��˫������
BSTree* rotateRL(BSTree *root){
	
	BSTree *Rtemp = root->Rchild;
	BSTree *RLtemp = root->Rchild->Lchild;
	root->Rchild->Lchild = NULL;
	root->Rchild = NULL;
	RLtemp->Lchild = root;
	RLtemp->Rchild = Rtemp;
	return RLtemp;
}

//BST��==��AVL��
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
	
	//����
	printf("�������(С->��):");
	inorderBST(Tnode);
	printf("\n�������(����˳��):");
	preorderBST(Tnode);
	printf("\n�������:");
	postorderBST(Tnode);

	//����
	BSTree *Fnode = findData(Tnode,3);
	BSTree *min = findMin(Tnode);
	BSTree *max = findMax(Tnode);
	printf("��Сֵ:%d\n",min->data);
	printf("���ֵ:%d\n",max->data);
	//ɾ��
	printf("ɾ������Ϊ3�Ľڵ��,�������:");
	deleteNode(Tnode,3);
	inorderBST(Tnode);
	
	/*
	//����������תΪ����ƽ����(AVL��)
	BSTtoAVLTree(Tnode);
	inorderBST(Tnode);
	*/
}
