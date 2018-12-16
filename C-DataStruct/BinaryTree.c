#include <stdio.h>
#include <stdlib.h>

typedef struct BiTree {
	char data;
	struct BiTree *Lchild;
	struct BiTree *Rchild;
}BiTree;

typedef struct BTStack {
	BiTree **node;
	int top;
}BTStack;
	
BTStack* initStackBT(BTStack *stack, int maxsize);
void pushS(BTStack *stack, BiTree *node);
BiTree* popS(BTStack *stack);

BTStack* initStackBT(BTStack *stack, int maxsize) {
	stack->node = (BiTree**)malloc(maxsize * sizeof(BiTree*));
	stack->top = -1;
	return stack;
}

void pushS(BTStack *stack, BiTree *node) {
	stack->top++;
	stack->node[stack->top] = node;
}

BiTree* popS(BTStack *stack) {
	BiTree *node = stack->node[stack->top];
	stack->top--;
	return node;
}

//123##4##56##7##
BiTree* createBiTree() {
	BiTree *T;
	char c;
	scanf("%c", &c);
	if (c != '#') {
		T = (BiTree*)malloc(sizeof(BiTree));
		T->data = c;
		T->Lchild = createBiTree();
		T->Rchild = createBiTree();
	}
	else {
		T = NULL;
	}
	return T;
}

//�������
void perOrder(BiTree *node) {
	if (node != NULL) {
		printf("%c", node->data);
		perOrder(node->Lchild);
		perOrder(node->Rchild);
	}
}

//�������
void inOrder(BiTree *node) {
	if (node != NULL) {
		inOrder(node->Lchild);
		printf("%c", node->data);
		inOrder(node->Rchild);
	}
}

//��������
void postOrder(BiTree *node) {
	if (node != NULL) {
		postOrder(node->Lchild);
		postOrder(node->Rchild);
		printf("%c", node->data);
	}
}

//����ջ����()
void disBiTreeByStack(BiTree *node, BTStack *Tstack) {
	BTStack *stack = initStackBT(Tstack, 10);
	while (node != NULL || stack->top != -1) {
		while (node != NULL) {
			pushS(stack, node);
			node = node->Lchild;
		}
		if (stack->top != -1) {
			node = popS(stack);
			printf("%c", node->data);
			node = node->Rchild;
		}
	}
}

int degree = 0;
int degreeOfBiTree(BiTree *node) {
	if (node != NULL) {
		degree++;
		degreeOfBiTree(node->Lchild);
		degreeOfBiTree(node->Rchild);
	}
	return degree;
}

int leafOfBiTree(BiTree *node) {
	if (node == NULL) {
		return 0;
	}
	if (node->Lchild == NULL && node->Rchild == NULL) {
		return 1;
	}
	return leafOfBiTree(node->Lchild) + leafOfBiTree(node->Rchild);
}

//�������
int depthOfBiTree(BiTree *node) {

	int Lheight,Rheight;

	if (node != NULL) {
		Lheight = depthOfBiTree(node->Lchild);
		Rheight = depthOfBiTree(node->Rchild);
	}
	else{
		return 0;
	}

	if (Lheight > Rheight) {
		return Lheight + 1;
	}
	else{
		return Rheight + 1;
	}
}

void swapBiTreeNode(BiTree *root) {

	BiTree *temp;

	if (root != NULL) {
		temp = root->Lchild;
		root->Lchild = root->Rchild;
		root->Rchild = temp; 
		swapBiTreeNode(root->Lchild);
		swapBiTreeNode(root->Rchild);
	}
}

//LL����������
BiTree* rotateLL(BiTree *root){
	
	BiTree *temp = root->Lchild;
	root->Lchild = NULL;
	temp->Rchild = root;
	return temp;
}

//RR����������
BiTree* rotateRR(BiTree *root){
	
	BiTree *temp = root->Rchild;
	root->Rchild = NULL;
	temp->Lchild = root;
	return temp;
}

//LR����ת����
BiTree* rotateLR(BiTree *root){
	
	BiTree *Ltemp = root->Lchild;
	BiTree *LRtemp = root->Lchild->Rchild;
	root->Lchild->Rchild = NULL;
	root->Lchild = NULL;
	LRtemp->Lchild = Ltemp;
	LRtemp->Rchild = root;
	return LRtemp;
}

//RL����ת����
BiTree* rotateRL(BiTree *root){
	
	BiTree *Rtemp = root->Rchild;
	BiTree *RLtemp = root->Rchild->Lchild;
	root->Rchild->Lchild = NULL;
	root->Rchild = NULL;
	RLtemp->Lchild = root;
	RLtemp->Rchild = Rtemp;
	return RLtemp;
}

//����ƽ������(�������߶ȼ��������߶ȵľ���ֵ)
int balanceFactor(BiTree *node){
	
	return abs(depthOfBiTree(node->Lchild) - depthOfBiTree(node->Rchild));
}

//���ֲ�ƽ������ж�
BiTree* BSTtoAVLTree(BiTree *node){
	
	if(balanceFactor(node) > 1){
		if(node->Lchild->Lchild != NULL){
			node = rotateLL(node);
		}
		else if(node->Rchild->Rchild != NULL){
			node = rotateRR(node);
		}
		else if(node->Lchild->Rchild != NULL){
			node = rotateLR(node);
		}
		else if(node->Rchild->Lchild != NULL){
			node = rotateRL(node);
		}
	}
	return node;
}

int main() {

	BiTree *Tnode = createBiTree();
	printf("--------------------\n");
	printf("�������:");
	perOrder(Tnode);
	printf("\n");
	printf("�������:");
	inOrder(Tnode);
	printf("\n");
	printf("��������:");
	postOrder(Tnode);
	printf("\n");
	printf("���ö�ջ����:");
	BTStack stack;
	disBiTreeByStack(Tnode, &stack);
	printf("\n-------------------\n");
	int degree = degreeOfBiTree(Tnode);
	printf("�������Ľ������:%d\n", degree);
	printf("--------------------\n");
	int leaf = leafOfBiTree(Tnode);
	printf("Ҷ��������:%d\n", leaf);
	printf("--------------------\n");
	int depth = depthOfBiTree(Tnode);
	printf("�����������:%d\n", depth);
	printf("--------------------\n");
	//swapBiTreeNode(Tnode);
	//printf("���������нڵ㽻�����������:");
	//perOrder(Tnode);
	printf("%d\n",balanceFactor(Tnode));
	BiTree *AVLroot = BSTtoAVLTree(Tnode);
	printf("%c\n",AVLroot->data);
	printf("%d\n",balanceFactor(AVLroot));
}
