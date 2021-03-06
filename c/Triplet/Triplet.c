/*
 * Triplet.c
 *
 *      Author: caolisheng
 */

#include <stdlib.h>
#include <stdio.h>

#define OK 1
#define ERROR 0

typedef int Status;
typedef int ElemType;
typedef struct Triplet {
	ElemType e1;
	ElemType e2;
	ElemType e3;
} *pTriplet;

void swap(int *i1, int *i2, int *tmp) {
	if (*i1 < *i2) {
		*tmp = *i1;
		*i1 = *i2;
		*i2 = *tmp;
	}
}

Status InitTriplet(pTriplet *pT) {
	int v1, v2, v3, tmp;
	scanf("%d %d %d", &v1, &v2, &v3);

	swap(&v1, &v2, &tmp);
	swap(&v1, &v2, &tmp);
	swap(&v2, &v3, &tmp);

	(*pT) = (pTriplet) malloc(sizeof(struct Triplet));
	if (!(*pT)) return ERROR;
	(*pT)->e1 = v1;
	(*pT)->e2 = v2;
	(*pT)->e3 = v3;
	return OK;
}

Status DestroyTriplet(pTriplet *T) {
	free(*T);
	*T = NULL;
	return OK;
}

//int main() {
//	puts("Let's start...");
//	pTriplet T = NULL;
//	Status s = InitTriplet(&T);
//	if (s) {
//		printf(" %d %d %d \n", T->e1, T->e2, T->e3);
//	} else {
//		puts("Initial Error!");
//		return 0;
//	}
//	do {
//		s = DestroyTriplet(&T);
//	} while (!s);
//	puts("That's all!");
//	return 0;
//}
