/*
 ============================================================================
 Name        : StaticLinkedList.c
 Author      : caolisheng
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#define MAXSIZE 100

typedef int ElemType;
typedef struct {
	ElemType data;
	int cur;
} SLinkList[MAXSIZE];

void InitSpace(SLinkList space) {
	int loop;
	for (loop = 0; loop < MAXSIZE - 1; loop++) {
		space[loop].cur = loop + 1;
	}
	space[MAXSIZE - 1].cur = 0;
}

int Malloc(SLinkList space) {
	int i = space[0].cur;
	if (i) {
		space[0].cur = space[i].cur;
	}
	return i;
}

void Free(SLinkList space, int k) {
	space[k].cur = space[0].cur;
	space[0].cur = k;
}

int diff(SLinkList space, ElemType *source, int a, int b) {
	int loop;
	InitSpace(space);
	int head = Malloc(space);
	int tail = head;
	for (loop = 0; loop < a; loop++) {
		int pos = Malloc(space);
		space[pos].data = source[loop];
		space[tail].cur = pos;
		tail = pos;
	}
	space[tail].cur = 0;
	for (loop = a; loop < b; loop++) {
		int iter = head, pos = space[iter].cur;
		while (pos && space[pos].data != source[loop]) {
			iter = pos;
			pos = space[iter].cur;
		}
		if (!pos) {
			int newpos = Malloc(space);
			space[newpos].data = source[loop];
			space[newpos].cur = 0;
			space[tail].cur = newpos;
			tail = newpos;
		} else {
			space[iter].cur = space[pos].cur;
			Free(space, pos);
			if (pos == tail) {
				tail = iter;
			}
		}
	}
	return head;
}

//int main(void) {
//	printf("!!!Hello World!!!"); /* prints !!!Hello World!!! */
//
//	SLinkList space;
//	int source[10] = { 1, 2, 3, 4, 5, 1, 5, 6, 4, 7};
//	int iter = diff(space, source, 5, 10);
//	for (iter = space[iter].cur; iter; iter = space[iter].cur) {
//		printf("%d ", space[iter].data);
//	}
//
//	printf("\n!!!That's all!!!");
//	return EXIT_SUCCESS;
//}
