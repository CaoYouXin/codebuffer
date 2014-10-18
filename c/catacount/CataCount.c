/*
 * CataCount.c
 *
 *  Created on: 2014Äê10ÔÂ18ÈÕ
 *      Author: CaoYouXin
 */


#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define BOY 1
#define GIRL 0
#define OK 1
#define ERROR 0
#define SCHOOL_NUM 5

typedef struct {
	int boy;
	int girl;
	int all;
} Catas[5];
typedef int Status;

Status cata(Catas catas, int projectNum) {
	int allNum = SCHOOL_NUM * 2 * projectNum;
	if (allNum % 8) {
		allNum = allNum / 8 + 1;
	} else {
		allNum /= 8;
	}
	char *flags = (char *)malloc(allNum);
	char *input = (char *)malloc(10);

	int project_id, gender, school_id, score, num, off;
	for (;;) {
		gets(input);
		if (!strcmp(input, "ok")) {
			break;
		}
		sscanf(input, "%d %d %d %d", &project_id, &gender, &school_id, &score);

		if (project_id < 0 || project_id >= projectNum || school_id < 0 || school_id >= SCHOOL_NUM) {
			printf("wrong project or school, continue another input.\n");
			fflush(stdout);
			continue;
		}

		num = school_id * (2 * projectNum) + gender * projectNum + projectNum;
		off = num % 8;
		num /= 8;
		if (flags[num] & (1 << off)) {
			printf("duplicated input, continue another input.\n");
			fflush(stdout);
			continue;
		}
		if (gender == BOY) {
			catas[school_id].boy += score;
			catas[school_id].all += score;
		} else if (gender == GIRL) {
			catas[school_id].girl += score;
			catas[school_id].all += score;
		} else {
			printf("wrong gender code, continue another input.\n");
			fflush(stdout);
			continue;
		}
	}

	return OK;
}

int main(void) {
	int projectNum;
	printf("how many projects? ");
	fflush(stdout);
	scanf("%d", &projectNum);

	printf("input spec: project_id boy/girl school_id score\n");
	printf("project_id bounds: 0 <= project_id < %d\n", projectNum);
	printf("school_id bounds: 0 <= school_id < %d\n", SCHOOL_NUM);
	printf("1 for boy, 0 for girl\n");
	printf("NOW, start your inputs(type 'ok' for end):\n");
	fflush(stdout);
	Catas catas;
	int i = 0;
	for (; i < SCHOOL_NUM; i++) {
		catas[i].boy = 0;
		catas[i].girl = 0;
		catas[i].all = 0;
	}
	cata(catas, projectNum);
	for (i = 0; i < SCHOOL_NUM; i++) {
		printf("\nSchool[%d] Status:\n", i);
		printf("Boy Total: %d, Girl Total: %d, All Total: %d", catas[i].boy, catas[i].girl, catas[i].all);
	}
	return 0;
}
