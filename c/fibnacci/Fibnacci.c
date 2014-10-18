/*
 * Fabnacci.c
 *
 *  Created on: 2014Äê10ÔÂ18ÈÕ
 *      Author: CaoYouXin
 */

#include <stdlib.h>
#include <stdio.h>

int fab(int k, int m) {
	if (k < 1 || m < 0) {
		return -1;
	}
	if (k > m) {
		return 0;
	}
	if (k == m) {
		return 1;
	}
	int *rets = (int *)malloc((m + 1) * sizeof(int));
	int i = 0, sum = 1;
	for (; i < k -1; i++) {
		rets[i] = 0;
	}
	rets[k] = rets[k -1] = 1;
	for (i = k + 1; i <= m; i++) {
		rets[i] = sum - rets[i - k - 1] + rets[i - 1];
	}
	return rets[m];
}

//int main(void) {
//	int k, m, ret;
//	scanf("%d %d", &k, &m);
//
//	ret = fab(k, m);
//	printf("[k=%d,m=%d] and result=%d\n(If result is -1, then input error.)", k, m, ret);
//	return 0;
//}
